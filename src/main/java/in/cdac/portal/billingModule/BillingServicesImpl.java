
package in.cdac.portal.billingModule;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import in.cdac.portal.dao.UserDao;
import in.cdac.portal.modal.Billing;
import in.cdac.portal.modal.BillingDetails;
import in.cdac.portal.modal.Summary;
import in.cdac.portal.services.BillingServices;
import in.cdac.portal.services.SummaryServices;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class BillingServicesImpl implements BillingServices {
//	private final static Logger logger = Logger.getLogger(BillingServicesImpl.class);
	 private final  static Logger logger = LogManager.getLogger( BillingServicesImpl.class );

	@Autowired
	UserDao userDao;

	@Autowired
	Environment env;
	
	@Autowired
	SummaryServices summServ;

	static Map<String, String> apkCode_apk_name;
	public static Map<String, String> dept_code_dept_name;

	public static ArrayList<String> resAl;
	public static Map<String, HashSet<String>> dept_code_apk_code;
	public static JasperBill jasp;

	public static void pullData() {
		dept_code_apk_code = new HashMap<String, HashSet<String>>();
		apkCode_apk_name = new HashMap<String, String>();
		dept_code_dept_name = new HashMap<String, String>();
		for (String line : resAl) {

			String[] scData = line.split(",");
//			System.out.println(" pullData"+line+" pullData");
			apkCode_apk_name.put(scData[2], scData[1]);
			dept_code_dept_name.put(scData[10], scData[0]);
			dept_code_apk_code.put(scData[10], new HashSet<String>());

		}
		for (String line : resAl) {

			String[] scData = line.split(",");

			dept_code_apk_code.get(scData[10]).add(scData[2]);

		}
	}

	public ArrayList<Summary> getOprSummaryForJasper(String dept) {
		try {
			pullData();
			GetFileData.resAl = resAl;
			GetFileData.dept_code_apk_code = dept_code_apk_code;
			ArrayList<Summary> dataforsummary = new ArrayList<>();
			ArrayList<Report> finalRepo = GetFileData.getAllOprSummaryForJasper(dept);

			jasp = new JasperBill();
			jasp.setTotalApkCount(0);
			jasp.setBreakup(new HashMap<>());
			int checkTotal = 0;
			for (Report rep : finalRepo) {
				Summary summaryobj = new Summary();
				summaryobj.setActivate_Incorrectattempt("0");
				summaryobj.setActivate_Retrievereferencenumber("0");
				summaryobj.setDeActivate_Incorrectattempt("0");
				summaryobj.setDeActivate_Retrievereferencenumber("0");
				summaryobj.setGetRef_Incorrectattempt("0");
				summaryobj.setGetRef_Retrievereferencenumber("0");
				summaryobj.setGetUid_Incorrectattempt("0");
				summaryobj.setGetUid_Retrievereferencenumber("0");
				summaryobj.setStrUid_Aadhaarduplicatecheck("0");
				summaryobj.setStrUid_Getexistingreferencenumber("0");
				summaryobj.setStrUid_StoreAadhaarNumber("0");
				summaryobj.setTotalCount("0");
				jasp.setDeptName(dept_code_dept_name.get(dept));
				jasp.setDc(dept);
				jasp.setTotalApkCount(jasp.getTotalApkCount() + rep.getApkCount());
				jasp.getBreakup().put(apkCode_apk_name.get(rep.getApkName()), String.valueOf(rep.getApkCount()));
				rep.setApkName(apkCode_apk_name.get(rep.getApkName()));

				summaryobj.setApplicationName(rep.getApkName());
				if (rep.getSummary().size() == 0) {
					return null;
				}
				for (Map.Entry<String, HashMap<String, Integer>> sums : rep.getSummary().entrySet()) {
					Entry<String, HashMap<String, Integer>> entry = sums;
					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
						Entry<String, Integer> en = sum;
						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
							if (en.getKey().trim().equalsIgnoreCase("false")) {
								summaryobj.setStrUid_Aadhaarduplicatecheck(Integer.toString(en.getValue()));

							} else if (en.getKey().trim().equalsIgnoreCase("true")) {
								summaryobj.setStrUid_Getexistingreferencenumber(Integer.toString(en.getValue()));

							} else if (en.getKey().trim().equalsIgnoreCase("null")) {
								summaryobj.setStrUid_StoreAadhaarNumber(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("getrefnum")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setGetRef_Retrievereferencenumber(Integer.toString(en.getValue()));

							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setGetRef_Incorrectattempt(Integer.toString(en.getValue()));

							}

						} else if (entry.getKey().trim().equalsIgnoreCase("getuid")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setGetUid_Retrievereferencenumber(Integer.toString(en.getValue()));

							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setGetUid_Incorrectattempt(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("activate")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setActivate_Incorrectattempt(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("deactivate")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setDeActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setDeActivate_Incorrectattempt(Integer.toString(en.getValue()));
							}
						}
					}
				}
				
				
				
//				-------------------Ameys logic for reporting data----------------------------------------
//				for (Map.Entry<String, HashMap<String, Integer>> sums : rep.getSummary().entrySet()) {
//
//					Entry<String, HashMap<String, Integer>> entry = sums;
//					int sumAll = 0;
//					;
//
//					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
//
//						Entry<String, Integer> en = sum;
//						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
//							if (en.getKey().trim().equalsIgnoreCase("n") || en.getKey().trim().equalsIgnoreCase("y")) {
//								sumAll = sumAll + en.getValue();
//							}
//						}
//
//					}
//
//					int GER = 0;
//					int ADC = 0;
//					int SAN = 0;
//					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
//						Entry<String, Integer> en = sum;
//						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
//							if (en.getKey().trim().equalsIgnoreCase("true")) {
//								GER = en.getValue();
//							}
//							if (en.getKey().trim().equalsIgnoreCase("n")) {
//								ADC = en.getValue();
//								SAN = sumAll - ADC - GER;
//							}
//						}
//						
//						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
//							summaryobj.setStrUid_Getexistingreferencenumber(Integer.toString(GER));
//							summaryobj.setStrUid_Aadhaarduplicatecheck(Integer.toString(ADC));
//							summaryobj.setStrUid_StoreAadhaarNumber(Integer.toString(SAN));
//	
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getrefnum")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setGetRef_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetRef_Incorrectattempt(Integer.toString(en.getValue()));
//
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getuid")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//
//								summaryobj.setGetUid_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetUid_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("activate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("deactivate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setDeActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setDeActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//						}
//					}
//				}

				checkTotal += rep.getApkCount();
				summaryobj.setTotalCount(Integer.toString(rep.getApkCount()));

				if (checkTotal != 0) {
					dataforsummary.add(summaryobj);
				}
				
			}
			
			return dataforsummary;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	

	public ArrayList<Summary> getOprSummaryForJasperApkWise(String apk) {
		ArrayList<Summary> dataforsummary = new ArrayList<>();
		try {
			int checkTotal = 0;
			pullData();
			GetFileData.resAl = resAl;
			GetFileData.dept_code_apk_code = dept_code_apk_code;
			ArrayList<Report> finalRepo = GetFileData.getAllOprSummaryForJasperApkWise(apk);
			jasp = new JasperBill();
			jasp.setTotalApkCount(0);
			jasp.setBreakup(new HashMap<>());
			for (Report rep : finalRepo) {
				Summary summaryobj = new Summary();
				summaryobj.setActivate_Incorrectattempt("0");
				summaryobj.setActivate_Retrievereferencenumber("0");
				summaryobj.setDeActivate_Incorrectattempt("0");
				summaryobj.setDeActivate_Retrievereferencenumber("0");
				summaryobj.setGetRef_Incorrectattempt("0");
				summaryobj.setGetRef_Retrievereferencenumber("0");
				summaryobj.setGetUid_Incorrectattempt("0");
				summaryobj.setGetUid_Retrievereferencenumber("0");
				summaryobj.setStrUid_Aadhaarduplicatecheck("0");
				summaryobj.setStrUid_Getexistingreferencenumber("0");
				summaryobj.setStrUid_StoreAadhaarNumber("0");
				summaryobj.setTotalCount("0");
				jasp.setDeptName(apk);
				jasp.setDc(apk);
				jasp.setTotalApkCount(jasp.getTotalApkCount() + rep.getApkCount());
				jasp.getBreakup().put(apkCode_apk_name.get(rep.getApkName()), String.valueOf(rep.getApkCount()));
				rep.setApkName(apkCode_apk_name.get(rep.getApkName()));
				summaryobj.setApplicationName(rep.getApkName());
				if (rep.getSummary().size() == 0) {
					return null;
				}

				for (Map.Entry<String, HashMap<String, Integer>> sums : rep.getSummary().entrySet()) {
					Entry<String, HashMap<String, Integer>> entry = sums;
					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
						Entry<String, Integer> en = sum;
						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
							if (en.getKey().trim().equalsIgnoreCase("false")) {
								summaryobj.setStrUid_Aadhaarduplicatecheck(Integer.toString(en.getValue()));

							} else if (en.getKey().trim().equalsIgnoreCase("true")) {
								summaryobj.setStrUid_Getexistingreferencenumber(Integer.toString(en.getValue()));

							} else if (en.getKey().trim().equalsIgnoreCase("null")) {
								summaryobj.setStrUid_StoreAadhaarNumber(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("getrefnum")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setGetRef_Retrievereferencenumber(Integer.toString(en.getValue()));

							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setGetRef_Incorrectattempt(Integer.toString(en.getValue()));

							}

						} else if (entry.getKey().trim().equalsIgnoreCase("getuid")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setGetUid_Retrievereferencenumber(Integer.toString(en.getValue()));

							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setGetUid_Incorrectattempt(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("activate")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setActivate_Incorrectattempt(Integer.toString(en.getValue()));
							}

						} else if (entry.getKey().trim().equalsIgnoreCase("deactivate")) {
							if (en.getKey().trim().equalsIgnoreCase("y")) {
								summaryobj.setDeActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
							} if (en.getKey().trim().equalsIgnoreCase("n")) {
								summaryobj.setDeActivate_Incorrectattempt(Integer.toString(en.getValue()));
							}
						}
					}
				}
				checkTotal += rep.getApkCount();
				summaryobj.setTotalCount(Integer.toString(rep.getApkCount()));
				dataforsummary.add(summaryobj);

				if (checkTotal == 0) {

					return null;
				}
			}
			return dataforsummary;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return dataforsummary;
		}

	}

	private String[] getStartandLastDate(String[] datedata) {
		String[] StartandLastDates = { "", "" };
		try {
			if (datedata[0].contentEquals("month")) {
				YearMonth yearMonth = YearMonth.of(Integer.parseInt(datedata[2]), Integer.parseInt(datedata[1]));
				LocalDate firstOfMonth = yearMonth.atDay(1);
				LocalDate lastOfMonth = yearMonth.atEndOfMonth();
				String first = firstOfMonth.toString();
				String last = lastOfMonth.toString();
				StartandLastDates[0] = first;
				StartandLastDates[1] = checkDateRangeOfEndDate(last);
				return StartandLastDates;
			} else if (datedata[0].contentEquals("qaurter")) {
				YearMonth quaterfirstyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
						Integer.parseInt(datedata[1]));
				LocalDate firstOfMonth = quaterfirstyearMonth.atDay(1);
				YearMonth quaterlastyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
						Integer.parseInt(datedata[1]) + 2);
				LocalDate lastOfMonth = quaterlastyearMonth.atEndOfMonth();
				String first = firstOfMonth.toString();
				String last = lastOfMonth.toString();
				StartandLastDates[0] = first;
				StartandLastDates[1] = checkDateRangeOfEndDate(last);
				return StartandLastDates;
			}
			if (datedata[0].contentEquals("year")) {
				String first = datedata[1] + "-" + "01" + "-" + "01";
				String last = datedata[1] + "-" + "12" + "-" + "31";
				StartandLastDates[0] = first;
				StartandLastDates[1] = checkDateRangeOfEndDate(last);
				return StartandLastDates;

			}
			if (datedata[0].contentEquals("custom")) {
				StartandLastDates[0] = datedata[1];
				StartandLastDates[1] = datedata[2];
				return StartandLastDates;
			} else if (datedata[0].contentEquals("bill")) {
				YearMonth quaterfirstyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
						Integer.parseInt(datedata[1]));
				LocalDate firstOfMonth = quaterfirstyearMonth.atDay(1);
				YearMonth quaterlastyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
						Integer.parseInt(datedata[1]) + 2);
				LocalDate lastOfMonth = quaterlastyearMonth.atEndOfMonth();
				String first = firstOfMonth.toString();
				String last = lastOfMonth.toString();
				StartandLastDates[0] = first;
				StartandLastDates[1] = checkDateRangeOfEndDate(last);
				return StartandLastDates;
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	private String checkDateRangeOfEndDate(String last) {
		LocalDate today = LocalDate.now();
		LocalDate pastDate = LocalDate.parse(last);
		int compareValue = today.compareTo(pastDate);

		if (compareValue < 0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(new Date());
		}
		return last;
	}

	@Override
	public ResponseEntity<byte[]> getBillingDataForPdf(String[] datedata) {
		String username = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String first = dates[0];
		String last = dates[1];
		int totalcounts = 0;
		double value = 0;
		double totalamt = 0;
		String Quarter = "";
		int quarterNo;
		LocalDate localDate = LocalDate.parse(first);
		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dtfstr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime strnDate = LocalDateTime.now().minusDays(1);
		String strnD = dtfstr.format(strnDate);
		String finalAmt = env.getProperty("minAmount");
		String string = "\u20B9";
	    byte[] utf8;
		try {
			utf8 = string.getBytes("UTF-8");
			 string = new String(utf8, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.info(e1.getMessage());
		}

	   

		try {
			String nameandcode = summServ.getDeptcodeFromUsernameforreportIgnite(username);
			String[] deptnamenadcode = nameandcode.split(",");

			ResponseEntity<byte[]> response = summServ.getSummaryForJasperIgnite(datedata);
			if (response == null) {
//				logger.info("response  "+response);
//				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}

			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
			if (js.getTotalApkCount() == 0) {
				logger.info("js.getTotalApkCount()  "+js.getTotalApkCount());
//				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}
			Integer count = js.getTotalApkCount();
			String deptcode = js.getDc();
			String url = env.getProperty("igniteapiforbiling");
			RestTemplate rt = new RestTemplate();

			String[] arr = { String.valueOf(count), deptcode };
			// post for bill API
			String[] billingDetails = rt.postForObject(url, arr, String[].class);

			js.setSlab(billingDetails[0]);
			js.setQuarter(Integer.toString(quarterNo));
			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));

			List<Billing> billData = new ArrayList<Billing>();
			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
				value = Double.parseDouble(new DecimalFormat("##.##")
						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));
				if(Integer.parseInt(entry.getValue())!=0)
				{
				billData.add(new Billing(entry.getKey(), Integer.parseInt(entry.getValue()), value));
				}
			}
			Quarter = getQuarterForReport(js.getQuarter());

			/*-------------------------check-------------------------------------------------*/
			url = env.getProperty("cumulativeBasis");
			// post for bill CumulativeCheck
			String checkNo = rt.postForObject(url, deptcode, String.class);
//			System.out.println("Check No :  " + checkNo);
			logger.info("Check No :   "+checkNo);

			if (checkNo.contentEquals("0")) {
//				System.out.println("amt for 0: " + totalamt);
				logger.info("Amt for Check 0 : " + totalamt);

			} else {
				/*-------------------------Bill-Amount-------------------------------------------------*/
				url = env.getProperty("cumulativeBilling");
//				System.out.println("cummulativeBilling:  " + url);
				String arrDataForCumulativBill = deptcode + "," + checkNo;
				// post for bill CumulativeBilling
				String defAmount = rt.postForObject(url, arrDataForCumulativBill, String.class);
				String[] strnData = defAmount.split(",");
				String amountOnStrn=strnData[0];
				String storeAadhaarNumbers=strnData[1];
//				System.out.println("new amt:  " + strnData[0]);
				logger.info("New amount:  " + amountOnStrn);
				if (totalamt > Integer.parseInt(amountOnStrn)) {
					finalAmt = Double.toString(totalamt);
				} else {

					finalAmt = amountOnStrn;
					ArrayList<String> reportList = new ArrayList<>();
					// API call for Bill
					// report------------------------------------------------------------
					url = env.getProperty("cumulativeReport");
					reportList = rt.postForObject(url, deptcode, ArrayList.class);
//					System.out.println(reportList.toString());
					List<Billing> billDataCumulativeReport = new ArrayList<Billing>();
					for (String reportData : reportList) {
//						System.out.println(reportData);
						String[] reportArr = reportData.split(",");
						billDataCumulativeReport.add(new Billing(reportArr[2], Integer.parseInt(reportArr[1]), 0));
					}

					List<BillingDetails> BilldataCumulative = new ArrayList<>();
					BilldataCumulative.add(new BillingDetails("Department Name ", js.getDeptName()));
					BilldataCumulative.add(new BillingDetails("Bill To", billingDetails[2]));
					BilldataCumulative.add(new BillingDetails("Address", billingDetails[3]));
					BilldataCumulative.add(new BillingDetails("GST", billingDetails[1]));
//					Billdataforbelow2500.add(new BillingDetails("Slab Rate", billingDetails[0]));
					BilldataCumulative.add(new BillingDetails("Stored Aadhaar Number", storeAadhaarNumbers));
					BilldataCumulative.add(new BillingDetails("Total Amount", finalAmt + ".00"));
//					Billdataforbelow2500.add(new BillingDetails("Total Amount", "2500.00"));
					Map<String, Object> BillParamCumulative = new HashMap<String, Object>();
					BillParamCumulative.put("CollectionForDetails", new JRBeanCollectionDataSource(BilldataCumulative));
					BillParamCumulative.put("CollectionBeanParam",
							new JRBeanCollectionDataSource(billDataCumulativeReport));
					BillParamCumulative.put("Quarter", Quarter);
					BillParamCumulative.put("startDate", first);
					BillParamCumulative.put("lastDate", last);
					BillParamCumulative.put("dateAndTimeStamp", dtf.format(now));
					BillParamCumulative.put("TotalCount", finalAmt);
//					BillParamCumulative.put("TotalAmount", totalAmtForReport);
					BillParamCumulative.put("note", "The bill provided is in accordance with Annexure 1. \r\n"
							+ "Your total number of stored  Aadhaar numbers falls under the slab rate of Table 2 of Annexure 1.\r\n"
							+ "Please Note: Aadhaar numbers storage in ADV will be considered on a cumulative basis");

					JasperPrint ReportBelowCumulative = JasperFillManager.fillReport(JasperCompileManager.compileReport(
							ResourceUtils.getFile("classpath:" + env.getProperty("jasper.bill.dept.cumulative"))
									.getAbsolutePath()),
							BillParamCumulative, new JREmptyDataSource());
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_PDF);

					return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(ReportBelowCumulative),
							headers, HttpStatus.OK);
				}
			}

			if (totalcounts == 0) {
				logger.info("totalcounts  "+totalcounts);
//				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}

			List<BillingDetails> summaryReport = new ArrayList<>();
			summaryReport.add(new BillingDetails("Department Name ", js.getDeptName()));
			summaryReport.add(new BillingDetails("Bill To", billingDetails[2]));
			summaryReport.add(new BillingDetails("Address", billingDetails[3]));
			summaryReport.add(new BillingDetails("GST", billingDetails[1]));
			summaryReport.add(new BillingDetails("Total Transactions", Integer.toString(js.getTotalApkCount())));
			summaryReport.add(new BillingDetails("Slab Rate", billingDetails[0]));
			summaryReport.add(new BillingDetails("Total Amount", Double.toString(totalamt)));

			List<BillingDetails> Billdataforbelow2500 = new ArrayList<>();
			Billdataforbelow2500.add(new BillingDetails("Department Name ", js.getDeptName()));
			Billdataforbelow2500.add(new BillingDetails("Bill To", billingDetails[2]));
			Billdataforbelow2500.add(new BillingDetails("Address", billingDetails[3]));
			Billdataforbelow2500.add(new BillingDetails("GST", billingDetails[1]));
//			Billdataforbelow2500.add(new BillingDetails("Slab Rate", billingDetails[0]));
			Billdataforbelow2500.add(new BillingDetails("Total Counts", Integer.toString(totalcounts)));
			Billdataforbelow2500.add(new BillingDetails("Total Amount", finalAmt + ".00"));
//			Billdataforbelow2500.add(new BillingDetails("Total Amount", "2500.00"));
			Map<String, Object> BillParamBelow2500 = new HashMap<String, Object>();
			BillParamBelow2500.put("CollectionForDetails", new JRBeanCollectionDataSource(Billdataforbelow2500));
			BillParamBelow2500.put("Quarter", Quarter);
			BillParamBelow2500.put("startDate", first);
			BillParamBelow2500.put("lastDate", last);
			BillParamBelow2500.put("dateAndTimeStamp", dtf.format(now));
//			BillParamBelow2500.put("note", "note to be coming.....");

			Map<String, Object> BillParam = new HashMap<String, Object>();
			BillParam.put("CollectionBeanParam", new JRBeanCollectionDataSource(billData));
			BillParam.put("CollectionForDetails", new JRBeanCollectionDataSource(summaryReport));
			BillParam.put("Quarter", Quarter);
			BillParam.put("TotalCount", Integer.toString(totalcounts));
			BillParam.put("TotalAmount", Double.toString(totalamt));
			BillParam.put("startDate", first);
			BillParam.put("lastDate", last);
			BillParam.put("dateAndTimeStamp", dtf.format(now));
			JasperPrint NormalReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(
					ResourceUtils.getFile("classpath:" + env.getProperty("jasper.bill.dept")).getAbsolutePath()),
					BillParam // dynamic
					// parameters
					, new JREmptyDataSource());

			JasperPrint ReportBelow2500 = JasperFillManager.fillReport(
					JasperCompileManager.compileReport(ResourceUtils
							.getFile("classpath:" + env.getProperty("jasper.bill.dept.2500")).getAbsolutePath()),
					BillParamBelow2500, new JREmptyDataSource());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);

//			if (totalamt <= 2500 || !checkNo.contentEquals("0")) {
			if (totalamt <= 2500) {
				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(ReportBelow2500), headers,
						HttpStatus.OK);

			} else {
				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(NormalReport), headers,
						HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Total count zero " + e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<String[]>> getBillingDataForDeptCsv(String[] datedata) {
		String username = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String start = dates[0];
		String end = dates[1];
		int totalcounts = 0;
		double value = 0;
		double totalamt = 0;
		int quarterNo;
		LocalDate localDate = LocalDate.parse(start);
		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
		String finalAmt = env.getProperty("minAmount");
		try {
			ResponseEntity<List<String[]>> response = summServ.getSummaryForcsv(datedata, username);
			if (response == null) {
//				return new ResponseEntity<List<String[]>>(HttpStatus.NO_CONTENT);
			}
			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
			if (js.getTotalApkCount() == 0) {
//				return null;
			}

			Integer count = js.getTotalApkCount();
			String deptcode = js.getDc();
			String url = env.getProperty("igniteapiforbiling");
			RestTemplate rt = new RestTemplate();
			String[] arr = { String.valueOf(count), deptcode };

			String[] billingDetails = rt.postForObject(url, arr, String[].class);
			js.setSlab(billingDetails[0]);
			js.setQuarter(Integer.toString(quarterNo));
			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));
			List<String[]> billData = new ArrayList<>();
			List<String[]> billData2500 = new ArrayList<>();
			billData2500.add(new String[] { "Department Name", js.getDeptName() });
			billData2500.add(new String[] { "From ", start, "To", end });

			billData.add(new String[] { "Department Name", js.getDeptName() });
			billData.add(new String[] { "From ", start, "To", end });
			billData.add(new String[] { "Application Name", "Transaction Count", "Amount" });
			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
				value = Double.parseDouble(new DecimalFormat("##.##")
						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));

				billData.add(new String[] { entry.getKey(), entry.getValue(), Double.toString(value) });

			}

			/*-------------------------check-------------------------------------------------*/
			url = env.getProperty("cumulativeBasis");
			// post for bill CumulativeCheck
			String checkNo = rt.postForObject(url, deptcode, String.class);
//			System.out.println("Check No :  " + checkNo);
			logger.info("Check No :  " + checkNo);

			if (checkNo.contentEquals("0")) {
//				System.out.println("amt for 0: " + totalamt);
				logger.info("Amount for 0 : " + totalamt);

			} else {
				/*-------------------------Bill-Amount-------------------------------------------------*/
				url = env.getProperty("cumulativeBilling");
//				System.out.println("cummulativeBilling:  " + url);
				String arrDataForCumulativBill = deptcode + "," + checkNo;
				// post for bill CumulativeBilling
				String defAmount = rt.postForObject(url, arrDataForCumulativBill, String.class);
				String[] strnData = defAmount.split(",");
				String amountOnStrn=strnData[0];
				String storeAadhaarNumbers=strnData[1];
//				System.out.println("new amt:  " + amountOnStrn);
				logger.info("New amount : " + amountOnStrn);
				List<String[]> billDataCumulativeReport = new ArrayList<String[]>();
				billDataCumulativeReport.add(new String[] { "Department Name", js.getDeptName() });
				billDataCumulativeReport.add(new String[] { "From ", start, "To", end });
				billDataCumulativeReport.add(new String[] { "Stored Aadhaar Numbers", storeAadhaarNumbers });
				billDataCumulativeReport.add(new String[] { "Application Name", "Stored Aadhaar Numbers" });
//				System.out.println("new amt:  " + strnData[0] + " " + "totalamt :  " + totalamt);
				logger.info("New amount:  " + strnData[0] + " " + "Total amount :  " + totalamt);
				if (totalamt > Integer.parseInt(strnData[0])) {
					finalAmt = Double.toString(totalamt);
				} else {

					finalAmt = strnData[0];
					ArrayList<String> reportList = new ArrayList<>();
					// API call for Bill
					// report------------------------------------------------------------
					url = env.getProperty("cumulativeReport");
					reportList = rt.postForObject(url, deptcode, ArrayList.class);
//					System.out.println(reportList.toString());

					for (String reportData : reportList) {
//						System.out.println(reportData);
						String[] reportArr = reportData.split(",");
						billDataCumulativeReport.add(new String[] { reportArr[2], reportArr[1] });
					}

					billDataCumulativeReport.add(new String[] { "Amount", finalAmt });
					billDataCumulativeReport
							.add(new String[] { "Note", "The bill provided is in accordance with Annexure 1. \r\n"
									+ "Your total number of stored  Aadhaar numbers falls under the slab rate of Table 2 of Annexure 1." });
					billDataCumulativeReport.add(new String[] { "",
							"Please note Aadhaar numbers storage in ADV will be considered on a cumulative basis." });
					return new ResponseEntity<List<String[]>>(billDataCumulativeReport, HttpStatus.OK);
				}
			}
			/*-------------------------check-End------------------------------------------------*/

			if (totalamt > 2500) {
				billData.add(new String[] { "Total", Integer.toString(totalcounts), Double.toString(totalamt) });
				return new ResponseEntity<List<String[]>>(billData, HttpStatus.OK);
			} else {
				billData2500.add(new String[] { "Department Name", "Transaction Count", "Amount" });
				billData2500.add(new String[] { js.getDeptName(), Integer.toString(totalcounts), "2500" });
				billData2500.add(new String[] { "Note",
						"As total transactions are less than that of the committed quarter value" });
				billData2500.add(new String[] { "",
						"The data mentioned above is based on the specified period, please note that the data may vary depending\r\n"
								+ "				upon the chosen period." });
				return new ResponseEntity<List<String[]>>(billData2500, HttpStatus.OK);

			}

		} catch (Exception e) {

			logger.info("getBillingDataForPdf data not found  total count zero " + e.getMessage());
			return new ResponseEntity<List<String[]>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getQuarterForReport(String quarter) {
		if (quarter.trim().equalsIgnoreCase("1")) {

			quarter = env.getProperty("billing.quarter.4");
		} else if (quarter.trim().equalsIgnoreCase("2")) {

			quarter = env.getProperty("billing.quarter.1");
		} else if (quarter.trim().equalsIgnoreCase("3")) {

			quarter = env.getProperty("billing.quarter.2");

		} else if (quarter.trim().equalsIgnoreCase("4")) {

			quarter = env.getProperty("billing.quarter.3");
		}
		return quarter;
	}

	@Override
	public ResponseEntity<byte[]> getBillingDataForPdfapp(String[] datedata) {
		String appcode = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String first = dates[0];
		String last = dates[1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		int totalcounts = 0;
		double value = 0;
		double totalamt = 0;
		String Quarter = "";
		int quarterNo;
		LocalDate localDate = LocalDate.parse(first);
		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
		summServ.getSummaryForJasperIgniteapp(datedata);
		try {
			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
			if (js.getTotalApkCount() == 0) {
				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}
			String url = env.getProperty("igniteapiforappwisebill");
			RestTemplate rt = new RestTemplate();
//			String[] arr = {String.valueOf(js.getTotalApkCount()), "A100006" };
			String[] arr = { appcode, first, last };
			String[] billingDetails = rt.postForObject(url, arr, String[].class);
			if (billingDetails == null) {
				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}
			js.setSlab(billingDetails[0]);
			js.setQuarter(Integer.toString(quarterNo));
			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));

			List<Billing> billData = new ArrayList<Billing>();
			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {

				entry.getKey();
				entry.getValue();
				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
				value = Double.parseDouble(new DecimalFormat("##.##")
						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));
				billData.add(new Billing(entry.getKey(), Integer.parseInt(entry.getValue()), value));
			}

			Quarter = getQuarterForReport(js.getQuarter());

			List<BillingDetails> Billdataforbelow2500 = new ArrayList<>();

			if (!billData.isEmpty()) {
				Billdataforbelow2500
						.add(new BillingDetails("Application Name ", billData.get(0).getApplication_name()));
			}
			Billdataforbelow2500.add(new BillingDetails("Bill To", billingDetails[2]));
			Billdataforbelow2500.add(new BillingDetails("Address", billingDetails[3]));
			Billdataforbelow2500.add(new BillingDetails("GST", billingDetails[1]));
			Billdataforbelow2500.add(new BillingDetails("Slab Rate", billingDetails[0]));
			Billdataforbelow2500.add(new BillingDetails("Total Counts", Integer.toString(totalcounts)));
			Billdataforbelow2500.add(new BillingDetails("Total Amount", "2500.00"));
			Map<String, Object> BillParamBelow2500 = new HashMap<String, Object>();
			BillParamBelow2500.put("CollectionForDetails", new JRBeanCollectionDataSource(Billdataforbelow2500));
			BillParamBelow2500.put("Quarter", Quarter);
			BillParamBelow2500.put("startDate", first);
			BillParamBelow2500.put("lastDate", last);
			BillParamBelow2500.put("dateAndTimeStamp", dtf.format(now));

			List<BillingDetails> summaryReport = new ArrayList<>();
//			summaryReport.add(new BillingDetails("Department Name ",js.getDeptName()));			
			summaryReport.add(new BillingDetails("Bill To", billingDetails[2]));
			summaryReport.add(new BillingDetails("Address", billingDetails[3]));
			summaryReport.add(new BillingDetails("GST", billingDetails[1]));
			summaryReport.add(new BillingDetails("Total Transaction", Integer.toString(js.getTotalApkCount())));
			summaryReport.add(new BillingDetails("Slab Rate", billingDetails[0]));
			summaryReport.add(new BillingDetails("Total Amount", Double.toString(totalamt)));

			Map<String, Object> BillingParam = new HashMap<String, Object>();
			BillingParam.put("CollectionBeanParam", new JRBeanCollectionDataSource(billData));
			BillingParam.put("CollectionForDetails", new JRBeanCollectionDataSource(summaryReport));
			BillingParam.put("Quarter", Quarter);
			BillingParam.put("TotalCount", Integer.toString(totalcounts));
			BillingParam.put("TotalAmount", Double.toString(totalamt));
			BillingParam.put("startDate", first);
			BillingParam.put("lastDate", last);
			BillingParam.put("dateAndTimeStamp", dtf.format(now));
			JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(
					ResourceUtils.getFile("classpath:" + env.getProperty("jasper.bill.app")).getAbsolutePath())

					, BillingParam // dynamic parameters
					, new JREmptyDataSource());

			JasperPrint ReportBelow2500 = JasperFillManager.fillReport(
					JasperCompileManager.compileReport(ResourceUtils
							.getFile("classpath:" + env.getProperty("jasper.bill.dept.2500")).getAbsolutePath()),
					BillParamBelow2500, new JREmptyDataSource());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);

			if (totalamt <= 2500) {
				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(ReportBelow2500), headers,
						HttpStatus.OK);

			} else {
				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers,
						HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<String[]>> getBillingDataForAppCsv(String[] datedata) {
		String appcode = datedata[3];
		String appName = "";
		String[] dates = getStartandLastDate(datedata);
		String first = dates[0];
		String last = dates[1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		int totalcounts = 0;
		double value = 0;
		double totalamt = 0;
		int quarterNo;
		LocalDate localDate = LocalDate.parse(first);
		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
		try {
			ResponseEntity<byte[]> response = summServ.getSummaryForJasperIgniteapp(datedata);
			if (response == null) {
				return new ResponseEntity<List<String[]>>(HttpStatus.NO_CONTENT);
			}

			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
			if (js.getTotalApkCount() == 0) {
				return new ResponseEntity<List<String[]>>(HttpStatus.NO_CONTENT);
			}
			String url = env.getProperty("igniteapiforappwisebill");
			RestTemplate rt = new RestTemplate();
//			String[] arr = {String.valueOf(js.getTotalApkCount()), "A100006" };
			String[] arr = { appcode, first, last };
			String[] billingDetails = rt.postForObject(url, arr, String[].class);
			if (billingDetails == null) {
				return new ResponseEntity<List<String[]>>(HttpStatus.NO_CONTENT);
			}
			js.setSlab(billingDetails[0]);
			js.setQuarter(Integer.toString(quarterNo));
			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));

			List<String[]> billData = new ArrayList<>();
			List<String[]> billData2500 = new ArrayList<>();
			billData.add(new String[] { "Application Name", "Transaction Count", "Amount" });
			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
				value = Double.parseDouble(new DecimalFormat("##.##")
						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));

				billData.add(new String[] { entry.getKey(), entry.getValue(), Double.toString(value) });
				appName = entry.getKey();
			}
			if (totalamt > 2500) {

				billData.add(new String[] { "Total", Integer.toString(totalcounts), Double.toString(totalamt) });
				return new ResponseEntity<List<String[]>>(billData, HttpStatus.OK);
			} else {
				for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
					appName = entry.getKey();
				}
				billData2500.add(new String[] { "Application Name", "Transaction Count", "Amount" });
				billData2500.add(new String[] { appName, Integer.toString(js.getTotalApkCount()), "2500" });
				billData2500.add(new String[] { "Note",
						"As total transactions are less than that of the committed quarter value" });
				billData2500.add(new String[] { "",
						"The data mentioned above is based on the specified period, please note that the data may vary depending\r\n"
								+ "				upon the chosen period." });
				return new ResponseEntity<List<String[]>>(billData2500, HttpStatus.OK);
			}

		} catch (Exception e) {

			logger.info("getBillingDataForAppCsv data not found  total count zero " + e.getMessage());
			return new ResponseEntity<List<String[]>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

////////////////old class
//package in.cdac.portal.billingModule;
//
//import java.text.DecimalFormat;
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.temporal.IsoFields;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.client.RestTemplate;
//
//import in.cdac.portal.dao.UserDao;
//import in.cdac.portal.modal.Billing;
//import in.cdac.portal.modal.BillingDetails;
//import in.cdac.portal.modal.Summary;
//import in.cdac.portal.services.BillingServices;
//import in.cdac.portal.services.UserServiceImpl;
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//@Service
//public class BillingServicesImpl implements BillingServices {
//
//	private final static Logger logger = Logger.getLogger(BillingServicesImpl.class);
//
//	@Autowired
//	UserDao userDao;
//	@Autowired
//	Environment env;
//	static Map<String, String> apkCode_apk_name;
//	public static Map<String, String> dept_code_dept_name;
//
//	public static ArrayList<String> resAl;
//	public static Map<String, HashSet<String>> dept_code_apk_code;
//	public static JasperBill jasp;
//
//	public static void pullData() {
//		dept_code_apk_code = new HashMap<String, HashSet<String>>();
//		apkCode_apk_name = new HashMap<String, String>();
//		dept_code_dept_name = new HashMap<String, String>();
//		for (String line : resAl) {
//
//			String[] scData = line.split(",");
//			apkCode_apk_name.put(scData[2], scData[1]);
//			dept_code_dept_name.put(scData[10], scData[0]);
//			dept_code_apk_code.put(scData[10], new HashSet<String>());
//
//		}
//		for (String line : resAl) {
//
//			String[] scData = line.split(",");
//
//			dept_code_apk_code.get(scData[10]).add(scData[2]);
//
//		}
//	}
//
//	public ArrayList<Summary> getOprSummaryForJasper(String dept) {
//		try {
//
//			pullData();
//			GetFileData.resAl = resAl;
//			GetFileData.dept_code_apk_code = dept_code_apk_code;
//			ArrayList<Summary> dataforsummary = new ArrayList<>();
//			ArrayList<Report> finalRepo = GetFileData.getAllOprSummaryForJasper(dept);
//			jasp = new JasperBill();
//			jasp.setTotalApkCount(0);
//			jasp.setBreakup(new HashMap<>());
//			int checkTotal =0;
//			for (Report rep : finalRepo) {
//				Summary summaryobj = new Summary();
//				summaryobj.setActivate_Incorrectattempt("0");
//				summaryobj.setActivate_Retrievereferencenumber("0");
//				summaryobj.setDeActivate_Incorrectattempt("0");
//				summaryobj.setDeActivate_Retrievereferencenumber("0");
//				summaryobj.setGetRef_Incorrectattempt("0");
//				summaryobj.setGetRef_Retrievereferencenumber("0");
//				summaryobj.setGetUid_Incorrectattempt("0");
//				summaryobj.setGetUid_Retrievereferencenumber("0");
//				summaryobj.setStrUid_Aadhaarduplicatecheck("0");
//				summaryobj.setStrUid_Getexistingreferencenumber("0");
//				summaryobj.setStrUid_StoreAadhaarNumber("0");
//				summaryobj.setTotalCount("0");
//				jasp.setDeptName(dept_code_dept_name.get(dept));
//				jasp.setDc(dept);
//				jasp.setTotalApkCount(jasp.getTotalApkCount() + rep.getApkCount());
//				jasp.getBreakup().put(apkCode_apk_name.get(rep.getApkName()), String.valueOf(rep.getApkCount()));
//				rep.setApkName(apkCode_apk_name.get(rep.getApkName()));
//
//				summaryobj.setApplicationName(rep.getApkName());
//				if (rep.getSummary().size() == 0) {
//					throw new Exception("Data not found for request");
//				}
//				for (Map.Entry<String, HashMap<String, Integer>> sums : rep.getSummary().entrySet()) {
//					Entry<String, HashMap<String, Integer>> entry = sums;
//					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
//						Entry<String, Integer> en = sum;
//						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
//							if (en.getKey().trim().equalsIgnoreCase("false")) {
//								summaryobj.setStrUid_Aadhaarduplicatecheck(Integer.toString(en.getValue()));
//
//							} else if (en.getKey().trim().equalsIgnoreCase("true")) {
//								summaryobj.setStrUid_Getexistingreferencenumber(Integer.toString(en.getValue()));
//
//							} else if (en.getKey().trim().equalsIgnoreCase("null")) {
//								summaryobj.setStrUid_StoreAadhaarNumber(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getrefnum")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setGetRef_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetRef_Incorrectattempt(Integer.toString(en.getValue()));
//
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getuid")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setGetUid_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetUid_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("activate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("deactivate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setDeActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setDeActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//						}
//					}
//				}
//
//				checkTotal  += rep.getApkCount();
//				summaryobj.setTotalCount(Integer.toString(rep.getApkCount()));
//				dataforsummary.add(summaryobj);
//			}
//			if (checkTotal == 0) {
//				
//				return null;
//			}
//			return dataforsummary;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//
//	}
//
//	public ArrayList<Summary> getOprSummaryForJasperApkWise(String apk) {
//		ArrayList<Summary> dataforsummary = new ArrayList<>();
//		try {
//			pullData();
//			GetFileData.resAl = resAl;
//			GetFileData.dept_code_apk_code = dept_code_apk_code;
//			ArrayList<Report> finalRepo = GetFileData.getAllOprSummaryForJasperApkWise(apk);
//			jasp = new JasperBill();
//			jasp.setTotalApkCount(0);
//			jasp.setBreakup(new HashMap<>());
//			for (Report rep : finalRepo) {
//				Summary summaryobj = new Summary();
//				summaryobj.setActivate_Incorrectattempt("0");
//				summaryobj.setActivate_Retrievereferencenumber("0");
//				summaryobj.setDeActivate_Incorrectattempt("0");
//				summaryobj.setDeActivate_Retrievereferencenumber("0");
//				summaryobj.setGetRef_Incorrectattempt("0");
//				summaryobj.setGetRef_Retrievereferencenumber("0");
//				summaryobj.setGetUid_Incorrectattempt("0");
//				summaryobj.setGetUid_Retrievereferencenumber("0");
//				summaryobj.setStrUid_Aadhaarduplicatecheck("0");
//				summaryobj.setStrUid_Getexistingreferencenumber("0");
//				summaryobj.setStrUid_StoreAadhaarNumber("0");
//				summaryobj.setTotalCount("0");
//				jasp.setDeptName(apk);
//				jasp.setDc(apk);
//				jasp.setTotalApkCount(jasp.getTotalApkCount() + rep.getApkCount());
//				jasp.getBreakup().put(apkCode_apk_name.get(rep.getApkName()), String.valueOf(rep.getApkCount()));
//				rep.setApkName(apkCode_apk_name.get(rep.getApkName()));
//				summaryobj.setApplicationName(rep.getApkName());
//				if (rep.getSummary().size() == 0) {
//					throw new Exception("Data not found for request");
//				}
//
//				for (Map.Entry<String, HashMap<String, Integer>> sums : rep.getSummary().entrySet()) {
//					Entry<String, HashMap<String, Integer>> entry = sums;
//					for (Map.Entry<String, Integer> sum : rep.getSummary().get(entry.getKey()).entrySet()) {
//						Entry<String, Integer> en = sum;
//						if (entry.getKey().trim().equalsIgnoreCase("struid")) {
//							if (en.getKey().trim().equalsIgnoreCase("false")) {
//								summaryobj.setStrUid_Aadhaarduplicatecheck(Integer.toString(en.getValue()));
//
//							} else if (en.getKey().trim().equalsIgnoreCase("true")) {
//								summaryobj.setStrUid_Getexistingreferencenumber(Integer.toString(en.getValue()));
//
//							} else if (en.getKey().trim().equalsIgnoreCase("null")) {
//								summaryobj.setStrUid_StoreAadhaarNumber(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getrefnum")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setGetRef_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetRef_Incorrectattempt(Integer.toString(en.getValue()));
//
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("getuid")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setGetUid_Retrievereferencenumber(Integer.toString(en.getValue()));
//
//							} else {
//								summaryobj.setGetUid_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("activate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//
//						} else if (entry.getKey().trim().equalsIgnoreCase("deactivate")) {
//							if (en.getKey().trim().equalsIgnoreCase("y")) {
//								summaryobj.setDeActivate_Retrievereferencenumber(Integer.toString(en.getValue()));
//							} else {
//								summaryobj.setDeActivate_Incorrectattempt(Integer.toString(en.getValue()));
//							}
//						}
//					}
//				}
//				summaryobj.setTotalCount(Integer.toString(rep.getApkCount()));
//				dataforsummary.add(summaryobj);
//			}
//			return dataforsummary;
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return dataforsummary;
//		}
//
//	}
//
//	private String[] getStartandLastDate(String[] datedata) {
//		String[] StartandLastDates = { "", "" };
//		try {
//			if (datedata[0].contentEquals("month")) {
//				YearMonth yearMonth = YearMonth.of(Integer.parseInt(datedata[2]), Integer.parseInt(datedata[1]));
//				LocalDate firstOfMonth = yearMonth.atDay(1);
//				LocalDate lastOfMonth = yearMonth.atEndOfMonth();
//				String first = firstOfMonth.toString();
//				String last = lastOfMonth.toString();
//				StartandLastDates[0] = first;
//				StartandLastDates[1] = last;
//				return StartandLastDates;
//			} else if (datedata[0].contentEquals("qaurter")) {
//				YearMonth quaterfirstyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
//						Integer.parseInt(datedata[1]));
//				LocalDate firstOfMonth = quaterfirstyearMonth.atDay(1);
//				YearMonth quaterlastyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
//						Integer.parseInt(datedata[1]) + 2);
//				LocalDate lastOfMonth = quaterlastyearMonth.atEndOfMonth();
//				String first = firstOfMonth.toString();
//				String last = lastOfMonth.toString();
//				StartandLastDates[0] = first;
//				StartandLastDates[1] = last;
//				return StartandLastDates;
//			}
//			if (datedata[0].contentEquals("year")) {
//				String first = datedata[1] + "-" + "01" + "-" + "01";
//				String last = datedata[1] + "-" + "12" + "-" + "31";
//				StartandLastDates[0] = first;
//				StartandLastDates[1] = last;
//				return StartandLastDates;
//
//			}
//			if (datedata[0].contentEquals("custom")) {
//				StartandLastDates[0] = datedata[1];
//				StartandLastDates[1] = datedata[2];
//				return StartandLastDates;
//			} else if (datedata[0].contentEquals("bill")) {
//				YearMonth quaterfirstyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
//						Integer.parseInt(datedata[1]));
//				LocalDate firstOfMonth = quaterfirstyearMonth.atDay(1);
//				YearMonth quaterlastyearMonth = YearMonth.of(Integer.parseInt(datedata[2]),
//						Integer.parseInt(datedata[1]) + 2);
//				LocalDate lastOfMonth = quaterlastyearMonth.atEndOfMonth();
//				String first = firstOfMonth.toString();
//				String last = lastOfMonth.toString();
//				StartandLastDates[0] = first;
//				StartandLastDates[1] = last;
//				return StartandLastDates;
//			}
//
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//		}
//		return null;
//	}
//
//	@Override
//	public ResponseEntity<byte[]> getBillingDataForPdf(String[] datedata, String username) {
//		String[] dates = getStartandLastDate(datedata);
//		String first = dates[0];
//		String last = dates[1];
//		int totalcounts = 0;
//		double value = 0;
//		double totalamt = 0;
//		String Quarter = "";
//		int quarterNo;
//		LocalDate localDate = LocalDate.parse(first);
//		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
//		try {
//			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
//			if (js.getTotalApkCount() == 0) {
//				throw new Exception("TotalCount is Zero ");
//			}
//			Integer count = js.getTotalApkCount();
//			String deptcode = js.getDc();
//			String url = env.getProperty("igniteapiforbiling");
//			RestTemplate rt = new RestTemplate();
//		
//			String[] arr = { String.valueOf(count), deptcode };
//			String[] billingDetails = rt.postForObject(url, arr, String[].class);
//			js.setSlab(billingDetails[0]);
//			js.setQuarter(Integer.toString(quarterNo));
//			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
//					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));
//
//			List<Billing> billData = new ArrayList<Billing>();
//			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
//				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
//				value = Double.parseDouble(new DecimalFormat("##.##")
//						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));
//				billData.add(new Billing(entry.getKey(), Integer.parseInt(entry.getValue()), value));
//			}
//
//			Quarter = getQuarterForReport(js.getQuarter());
//			if (totalcounts == 0) {
//				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
//			}
//			List<BillingDetails> summaryReport = new ArrayList<>();
//			summaryReport.add(new BillingDetails("Department Name ", js.getDeptName()));
//			summaryReport.add(new BillingDetails("Bill To", billingDetails[2]));
//			summaryReport.add(new BillingDetails("Address", billingDetails[3]));
//			summaryReport.add(new BillingDetails("GST", billingDetails[1]));
//			summaryReport.add(new BillingDetails("Total Transaction", Integer.toString(js.getTotalApkCount())));
//			summaryReport.add(new BillingDetails("Slab Rate", billingDetails[0]));
//			summaryReport.add(new BillingDetails("Total Amount", Double.toString(totalamt)));
//
//			List<BillingDetails> Billdataforbelow2500 = new ArrayList<>();
//			Billdataforbelow2500.add(new BillingDetails("Department Name ", js.getDeptName()));
//			Billdataforbelow2500.add(new BillingDetails("Total Counts", Integer.toString(totalcounts)));
//			Billdataforbelow2500.add(new BillingDetails("Total Amount", "2500.00"));
//			Map<String, Object> BillParamBelow2500 = new HashMap<String, Object>();
//			BillParamBelow2500.put("CollectionForDetails", new JRBeanCollectionDataSource(Billdataforbelow2500));
//			BillParamBelow2500.put("Quarter", Quarter);
//			BillParamBelow2500.put("startDate", first);
//			BillParamBelow2500.put("lastDate", last);
//
//			Map<String, Object> BillParam = new HashMap<String, Object>();
//			BillParam.put("CollectionBeanParam", new JRBeanCollectionDataSource(billData));
//			BillParam.put("CollectionForDetails", new JRBeanCollectionDataSource(summaryReport));
//			BillParam.put("Quarter", Quarter);
//			BillParam.put("TotalCount", Integer.toString(totalcounts));
//			BillParam.put("TotalAmount", Double.toString(totalamt));
//			BillParam.put("startDate", first);
//			BillParam.put("lastDate", last);
//			JasperPrint NormalReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(
//					ResourceUtils.getFile("classpath:" + env.getProperty("jasper.bill.dept")).getAbsolutePath()),
//					BillParam // dynamic
//					// parameters
//					, new JREmptyDataSource());
//
//			JasperPrint ReportBelow2500 = JasperFillManager.fillReport(
//					JasperCompileManager.compileReport(ResourceUtils
//							.getFile("classpath:" + env.getProperty("jasper.bill.dept.2500")).getAbsolutePath()),
//					BillParamBelow2500, new JREmptyDataSource());
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_PDF);
//
//			if (totalamt <= 2500) {
//				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(ReportBelow2500), headers,
//						HttpStatus.OK);
//
//			} else {
//				return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(NormalReport), headers,
//						HttpStatus.OK);
//			}
//
//		} catch (Exception e) {
//			logger.info("getBillingDataForPdf data not found  total count zero " + e.getMessage());
//			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@Override
//	public ResponseEntity<List<String[]>> getBillingDataForCsv(String[] datedata, String username) {
//		String[] dates = getStartandLastDate(datedata);
//		String first = dates[0];
//		int totalcounts = 0;
//		double value = 0;
//		double totalamt = 0;
//		int quarterNo;
//		LocalDate localDate = LocalDate.parse(first);
//		quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
//		try {
//			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
//			if (js.getTotalApkCount() == 0) {
//				throw new Exception("TotalCount is Zero ");
//			}
//			Integer count = js.getTotalApkCount();
//			String deptcode = js.getDc();
//			String url = env.getProperty("igniteapiforbiling");
//			RestTemplate rt = new RestTemplate();
//			String[] arr = { String.valueOf(count), deptcode };
//			
//			String[] billingDetails = rt.postForObject(url, arr, String[].class);
//			js.setSlab(billingDetails[0]);
//			js.setQuarter(Integer.toString(quarterNo));
//			totalamt = Double.parseDouble(new DecimalFormat("##.##").format(
//					Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));
//
//			List<String[]> billData = new ArrayList<>();
//			billData.add(new String[]{"Application Name","Transaction Count","Amount"});
//			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
//				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
//				value = Double.parseDouble(new DecimalFormat("##.##")
//						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));
//				
//				billData.add(new String[] {entry.getKey(),entry.getValue(),Double.toString(value)});
//							
//			}
//			billData.add(new String[]{"Total",Integer.toString(totalcounts),Double.toString(totalamt)});
//			
//			return new ResponseEntity<List<String[]>>(billData,
//					HttpStatus.OK);
//
//		} catch (Exception e) {
//			
//			logger.info("getBillingDataForPdf data not found  total count zero " + e.getMessage());
//			return new ResponseEntity<List<String[]>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}	
//	}
//	
//
//	private String getQuarterForReport(String quarter) {
//		if (quarter.trim().equalsIgnoreCase("1")) {
//
//			quarter = env.getProperty("billing.quarter.4");
//		} else if (quarter.trim().equalsIgnoreCase("2")) {
//
//			quarter = env.getProperty("billing.quarter.1");
//		} else if (quarter.trim().equalsIgnoreCase("3")) {
//
//			quarter = env.getProperty("billing.quarter.2");
//
//		} else if (quarter.trim().equalsIgnoreCase("4")) {
//
//			quarter = env.getProperty("billing.quarter.3");
//		}
//		return quarter;
//	}
//	
//
//@Override
//	public ResponseEntity<byte[]> getBillingDataForPdfapp(String[] datedata, String appcode) {
//		// TODO Auto-generated method stub
//		String[] dates = getStartandLastDate(datedata);
//		String first = dates[0];
//		String last = dates[0];
//		int totalcounts = 0;
//		double value = 0;
//		double totalamt=0;
//		String Quarter = "";
//		int quarterNo;
//        LocalDate localDate = LocalDate.parse(first);
//        quarterNo = localDate.get(IsoFields.QUARTER_OF_YEAR);
//        logger.info("Quarter: "+quarterNo);
//        logger.info("StartDate: "+first +" lastDate: "+last);
//		try {
//			in.cdac.portal.billingModule.JasperBill js = BillingServicesImpl.jasp;
//			if(js.getTotalApkCount()==0) {
//				 return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
//			}
//			String url= env.getProperty("igniteapiforappwisebill");
//			RestTemplate rt = new RestTemplate();
////			String[] arr = {String.valueOf(js.getTotalApkCount()), "A100006" };
//			String [] arr= {appcode,first,last};
//			String[] billingDetails = rt.postForObject(url, arr, String[].class);
//			if(billingDetails ==null) {
//				 return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
//			}
//			js.setSlab(billingDetails[0]);
//			js.setQuarter(Integer.toString(quarterNo));
//			totalamt= Double.parseDouble(new DecimalFormat("##.##")
//					.format(Double.parseDouble(Integer.toString(js.getTotalApkCount())) * Double.parseDouble(js.getSlab())));
//						
//			List<Billing> billData = new ArrayList<Billing>();			
//			for (Map.Entry<String, String> entry : js.getBreakup().entrySet()) {
//				System.out.println(entry.getKey() + " " + entry.getValue());
//				entry.getKey();
//				entry.getValue();
//				totalcounts = totalcounts + Integer.parseInt(entry.getValue());
//				value = Double.parseDouble(new DecimalFormat("##.##")
//						.format(Double.parseDouble(entry.getValue()) * Double.parseDouble(js.getSlab())));
//				billData.add(new Billing(entry.getKey(), Integer.parseInt(entry.getValue()), value));
//			}
//
//			Quarter = getQuarterForReport(js.getQuarter());
//			
//			List<BillingDetails> summaryReport = new ArrayList<>();
////			summaryReport.add(new BillingDetails("Department Name ",js.getDeptName()));			
//			summaryReport.add(new BillingDetails("Bill To",billingDetails[2]));
//			summaryReport.add(new BillingDetails("Address",billingDetails[3]));
//			summaryReport.add(new BillingDetails("GST",billingDetails[1]));			
//			summaryReport.add(new BillingDetails("Total Transaction",Integer.toString(js.getTotalApkCount())));
//			summaryReport.add(new BillingDetails("Slab Rate",billingDetails[0]));
//			summaryReport.add(new BillingDetails("Total Amount",Double.toString(totalamt)));			
//
//			Map<String, Object> BillingParam = new HashMap<String, Object>();
//			BillingParam.put("CollectionBeanParam", new JRBeanCollectionDataSource(billData));
//			BillingParam.put("CollectionForDetails", new JRBeanCollectionDataSource(summaryReport));
//			BillingParam.put("Quarter", Quarter);
//			BillingParam.put("TotalCount", Integer.toString(totalcounts));
//			BillingParam.put("TotalAmount", Double.toString(totalamt));
//			BillingParam.put("startDate", first);
//			BillingParam.put("lastDate", last);
//			JasperPrint empReport = JasperFillManager.fillReport(
//					JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:" + env.getProperty("jasper.bill.app")).getAbsolutePath())
//					
//					, BillingParam // dynamic parameters
//					, new JREmptyDataSource());
//
//			HttpHeaders headers = new HttpHeaders();
//			// set the PDF format
//			headers.setContentType(MediaType.APPLICATION_PDF);
//			headers.setContentDispositionFormData("filename", "Billing.pdf");
//			// create the report in PDF format
//			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//}
