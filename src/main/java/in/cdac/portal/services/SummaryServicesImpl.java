
package in.cdac.portal.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
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

import in.cdac.portal.billingModule.BillingServicesImpl;
import in.cdac.portal.dao.UserDao;
import in.cdac.portal.modal.Summary;
import in.cdac.portal.modal.SummaryReportTotals;
import in.cdac.portal.modal.WrapperClass;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class SummaryServicesImpl implements SummaryServices {
//	private final static Logger logger = Logger.getLogger(SummaryServicesImpl.class);
	private final  static Logger logger = LogManager.getLogger( SummaryServicesImpl.class );

	@Autowired
	UserDao userDao;
	@Autowired
	Environment env;
	@Autowired
	BillingServicesImpl billSer;
	
	@Autowired
	SummaryServices summServ;


	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<byte[]> getSummaryForJasperIgnite(String[] datedata) {
		String username = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String startDate = dates[0];
		String lastDate = dates[1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		try {
//			String nameandcode = userDao.getDeptcodeFromUsernameforreport(username);
			String nameandcode = summServ.getDeptcodeFromUsernameforreportIgnite(username);
//			System.out.println("nameandcode :"+nameandcode);
			String[] deptnamenadcode = nameandcode.split(",");
//			String[] deptnamenadcode = {"dept0061","CRICS"};
			String deptName = deptnamenadcode[1];

			String url = env.getProperty("igniteapiforreports");
			RestTemplate rt = new RestTemplate();
			String[] arr = { deptnamenadcode[0], startDate, lastDate };
			
			//POST FOR REPORT API
			byte[] bdata = rt.postForObject(url, arr, byte[].class);
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);
			
			BillingServicesImpl.resAl = resAl;
			List<Summary> summaryReport = new ArrayList<>();
			summaryReport = billSer.getOprSummaryForJasper(deptnamenadcode[0]);
			if (summaryReport == null) {
				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}
		
			SummaryReportTotals sumtotcnt = getTotalCountsForSummary(summaryReport);
			
			if (sumtotcnt.getTotal_totalCount() == 0) {
				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
			}
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CollectionParamBean", new JRBeanCollectionDataSource(summaryReport));
			empParams.put("DeptName", deptName);
			empParams.put("startDate", startDate);
			empParams.put("lastDate", lastDate);
			empParams.put("dateAndTimeStamp", dtf.format(now));
			empParams.put("struid_san", sumtotcnt.getTotal_strUid_StoreAadhaarNumber());
			empParams.put("struid_ger", sumtotcnt.getTotal_strUid_Getexistingreferencenumber());
			empParams.put("struid_adc", sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck());
			empParams.put("getref_rr", sumtotcnt.getTotal_getRef_Retrievereferencenumber());
			empParams.put("getref_ie", sumtotcnt.getTotal_getRef_Incorrectattempt());
			empParams.put("getuid_rr", sumtotcnt.getTotal_getUid_Retrievereferencenumber());
			empParams.put("getuid_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
			empParams.put("activate_rr", sumtotcnt.getTotal_activate_Retrievereferencenumber());
			empParams.put("activate_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
			empParams.put("deactivate_rr", sumtotcnt.getTotal_deActivate_Retrievereferencenumber());
			empParams.put("deactivate_ie", sumtotcnt.getTotal_deActivate_Incorrectattempt());
			empParams.put("totcnt", sumtotcnt.getTotal_totalCount());
			JasperPrint Report = JasperFillManager.fillReport(
					JasperCompileManager.compileReport(
							ResourceUtils.getFile("classpath:" + env.getProperty("jasper.summary")).getAbsolutePath()),
					empParams, new JREmptyDataSource());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "Summary.pdf");
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(Report), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception " + e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Override
	public ResponseEntity<byte[]> getSummaryForJasperIgniteapp(String[] datedata) {	
		String appcode = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String first= dates[0];
		String last = dates[1];	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		try {	
		String url=env.getProperty("igniteapiforappwisereport");
		RestTemplate rt = new RestTemplate();
		String [] arr= {appcode,first,last};
		byte [] bdata=rt.postForObject(url, arr,byte[].class);
		ArrayList<String> resAl = new ArrayList<String>();
		resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);		
		BillingServicesImpl.resAl = resAl;
		 List<Summary> summaryReport = new ArrayList<>();
		 summaryReport =  billSer.getOprSummaryForJasperApkWise(appcode);
		 if(summaryReport==null)
		 {
			 return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
		 }
		Map<String, Object> SummaryParam = new HashMap<String, Object>();	
		SummaryParam.put("CollectionParamBean", new JRBeanCollectionDataSource(summaryReport));
		SummaryParam.put("startDate", first);
		SummaryParam.put("lastDate", last);
		SummaryParam.put("dateAndTimeStamp", dtf.format(now));
		if(!summaryReport.isEmpty()) {
		SummaryParam.put("applicationName", summaryReport.get(0).getApplicationName());
		}
		JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:" + env.getProperty("jasper.summaryApp")).getAbsolutePath())
				, SummaryParam // dynamic parameters
				, new JREmptyDataSource());

		HttpHeaders headers = new HttpHeaders();
		// set the PDF format
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "Summary.pdf");
		// create the report in PDF format
		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Exception "+e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@Override
	public ResponseEntity<List<String[]>> getSummaryForcsv(String[] datedata, String username) {

		List<String[]> billData = new ArrayList<>();
		billData.add(new String[] { "Application Name", "Store Aadhaar Number", "Store Aadhaar Number",
				"Store Aadhaar Number", "Get Reference Number", "Get Reference Number", "Get UID", "Get UID",
				"Activate", "Activate", "Deactivate", "Deactivate", "Total Counts" });
		billData.add(new String[] { "", "Store Aadhaar Number", ":Get existing reference", "Aadhaar duplicate check",
				"Retrieve reference ", "Incorrect attempt\r\n" + "", "Retrieve reference ",
				"Incorrect attempt\r\n" + "", "Retrieve reference ", "Incorrect attempt\r\n" + "",
				"Retrieve reference ", "Incorrect attempt\r\n" + "", "" });
		String[] dates = getStartandLastDate(datedata);
		String startDate = dates[0];
		String lastDate = dates[1];
		try {
//			String nameandcode = userDao.getDeptcodeFromUsernameforreport(username);
			String nameandcode = summServ.getDeptcodeFromUsernameforreportIgnite(username);
			String[] deptnamenadcode = nameandcode.split(",");
			String url = env.getProperty("igniteapiforreports");
			RestTemplate rt = new RestTemplate();
			String[] arr = { deptnamenadcode[0], startDate, lastDate };
			byte[] bdata = rt.postForObject(url, arr, byte[].class);
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);
			BillingServicesImpl.resAl = resAl;
			List<Summary> summaryReport = new ArrayList<>();
			summaryReport = billSer.getOprSummaryForJasper(deptnamenadcode[0]);
			if (summaryReport == null) {
				return null;
			}
			for (Summary summary : summaryReport) {
				billData.add(new String[] { summary.getApplicationName(), summary.getStrUid_StoreAadhaarNumber(),
						summary.getStrUid_Getexistingreferencenumber(), summary.getGetRef_Incorrectattempt(),
						summary.getGetRef_Retrievereferencenumber(), summary.getGetRef_Incorrectattempt(),
						summary.getGetUid_Retrievereferencenumber(), summary.getGetUid_Incorrectattempt(),
						summary.getActivate_Retrievereferencenumber(), summary.getActivate_Incorrectattempt(),
						summary.getDeActivate_Retrievereferencenumber(), summary.getDeActivate_Incorrectattempt() });
			}

			SummaryReportTotals sumtotcnt = getTotalCountsForSummary(summaryReport);
			billData.add(new String[] { "Total", Long.toString(sumtotcnt.getTotal_strUid_StoreAadhaarNumber()),
					Long.toString(sumtotcnt.getTotal_strUid_Getexistingreferencenumber()),
					Long.toString(sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck()),
					Long.toString(sumtotcnt.getTotal_getRef_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_getRef_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_getUid_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_getUid_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_activate_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_activate_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_deActivate_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_deActivate_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_totalCount()) });

			return new ResponseEntity<List<String[]>>(billData, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Exception " + e.getMessage());
			return new ResponseEntity<List<String[]>>(HttpStatus.INTERNAL_SERVER_ERROR);
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
			} else if (datedata[0].contentEquals("quarter")) {
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
			// TODO: handle exception
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

	private SummaryReportTotals getTotalCountsForSummary(List<Summary> summaryReport) {
		// TODO Auto-generated method stub
	
		Long total_strUid_StoreAadhaarNumber = (long) 0;
		Long total_strUid_Getexistingreferencenumber = (long) 0;
		Long total_strUid_Aadhaarduplicatecheck = (long) 0;
		Long total_getRef_Retrievereferencenumber = (long) 0;
		Long total_getRef_Incorrectattempt = (long) 0;
		Long total_getUid_Retrievereferencenumber = (long) 0;
		Long total_getUid_Incorrectattempt = (long) 0;
		Long total_activate_Retrievereferencenumber = (long) 0;
		Long total_activate_Incorrectattempt = (long) 0;
		Long total_deActivate_Retrievereferencenumber = (long) 0;
		Long total_deActivate_Incorrectattempt = (long) 0;
		Long total_totalCount = (long) 0;
		
		SummaryReportTotals sumtotcnt = new SummaryReportTotals();
		for (Iterator<Summary> iterator = summaryReport.iterator(); iterator.hasNext();) {
			Summary summary = iterator.next();

			total_strUid_StoreAadhaarNumber = total_strUid_StoreAadhaarNumber
					+ Long.parseLong(summary.getStrUid_StoreAadhaarNumber());
			total_strUid_Getexistingreferencenumber += Long.parseLong(summary.getStrUid_Getexistingreferencenumber());
			total_strUid_Aadhaarduplicatecheck += Long.parseLong(summary.getStrUid_Aadhaarduplicatecheck());
			total_getRef_Retrievereferencenumber += Long.parseLong(summary.getGetRef_Retrievereferencenumber());
			total_getRef_Incorrectattempt += Long.parseLong(summary.getGetRef_Incorrectattempt());
			total_getUid_Retrievereferencenumber += Long.parseLong(summary.getGetUid_Retrievereferencenumber());
			total_getUid_Incorrectattempt += Long.parseLong(summary.getGetUid_Incorrectattempt());
			total_activate_Retrievereferencenumber += Long.parseLong(summary.getActivate_Retrievereferencenumber());
			total_activate_Incorrectattempt += Long.parseLong(summary.getActivate_Incorrectattempt());
			total_deActivate_Retrievereferencenumber += Long.parseLong(summary.getDeActivate_Retrievereferencenumber());
			total_deActivate_Incorrectattempt += Long.parseLong(summary.getDeActivate_Incorrectattempt());
			total_totalCount += Long.parseLong(summary.getTotalCount());

		}
		
		sumtotcnt.setTotal_strUid_StoreAadhaarNumber(total_strUid_StoreAadhaarNumber);
		sumtotcnt.setTotal_strUid_Getexistingreferencenumber(total_strUid_Getexistingreferencenumber);
		sumtotcnt.setTotal_strUid_Aadhaarduplicatecheck(total_strUid_Aadhaarduplicatecheck);
		sumtotcnt.setTotal_getRef_Retrievereferencenumber(total_getRef_Retrievereferencenumber);
		sumtotcnt.setTotal_getRef_Incorrectattempt(total_getRef_Incorrectattempt);
		sumtotcnt.setTotal_getUid_Retrievereferencenumber(total_getUid_Retrievereferencenumber);
		sumtotcnt.setTotal_getUid_Incorrectattempt(total_getUid_Incorrectattempt);
		sumtotcnt.setTotal_activate_Retrievereferencenumber(total_activate_Retrievereferencenumber);
		sumtotcnt.setTotal_activate_Incorrectattempt(total_activate_Incorrectattempt);
		sumtotcnt.setTotal_deActivate_Retrievereferencenumber(total_deActivate_Retrievereferencenumber);
		sumtotcnt.setTotal_deActivate_Incorrectattempt(total_deActivate_Incorrectattempt);
		sumtotcnt.setTotal_totalCount(total_totalCount);
		
		return sumtotcnt;
	}


	@Override
	public ResponseEntity<List<String[]>> getSummaryForJasperIgnitecsv(String[] datedata) {
		
		String username = datedata[3];
		String[] dates = getStartandLastDate(datedata);
		String startDate = dates[0];
		String lastDate = dates[1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

			String nameandcode = userDao.getDeptcodeFromUsernameforreport(username);
			String[] deptnamenadcode = nameandcode.split(",");
			String deptName = deptnamenadcode[1];

			String url = env.getProperty("igniteapiforreports");
			RestTemplate rt = new RestTemplate();
			String[] arr = { deptnamenadcode[0], startDate, lastDate };
			byte[] bdata = rt.postForObject(url, arr, byte[].class);
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);
			BillingServicesImpl.resAl = resAl;
			List<Summary> summaryReport = new ArrayList<>();
			summaryReport = billSer.getOprSummaryForJasper(deptnamenadcode[0]);
			if (summaryReport == null) {
				return new ResponseEntity<List<String[]>>(HttpStatus.NO_CONTENT);
			}
			SummaryReportTotals sumtotcnt = getTotalCountsForSummary(summaryReport);

			List<String[]> billData = new ArrayList<>();
			billData.add(new String[] {"Department Name", deptName });
			billData.add(new String[] {"From", startDate ,"To", lastDate });
			billData.add(new String[] { "Application Name", "Store Aadhaar Number", "Store Aadhaar Number",
					"Store Aadhaar Number", "Get Reference Number", "Get Reference Number", "Get UID", "Get UID",
					"Activate", "Activate", "Deactivate", "Deactivate", "Total Counts" });
			billData.add(new String[] { "", "Store Aadhaar Number", ":Get existing reference", "Aadhaar duplicate check",
					"Retrieve reference ", "Incorrect attempt\r\n" + "", "Retrieve reference ",
					"Incorrect attempt\r\n" + "", "Retrieve reference ", "Incorrect attempt\r\n" + "",
					"Retrieve reference ", "Incorrect attempt\r\n" + "", "" });
			
			for (Summary summary : summaryReport) {
				billData.add(new String[] { summary.getApplicationName(), summary.getStrUid_StoreAadhaarNumber(),
						summary.getStrUid_Getexistingreferencenumber(), summary.getGetRef_Incorrectattempt(),
						summary.getGetRef_Retrievereferencenumber(), summary.getGetRef_Incorrectattempt(),
						summary.getGetUid_Retrievereferencenumber(), summary.getGetUid_Incorrectattempt(),
						summary.getActivate_Retrievereferencenumber(), summary.getActivate_Incorrectattempt(),
						summary.getDeActivate_Retrievereferencenumber(), summary.getDeActivate_Incorrectattempt() });
			}

			billData.add(new String[] { "Total", Long.toString(sumtotcnt.getTotal_strUid_StoreAadhaarNumber()),
					Long.toString(sumtotcnt.getTotal_strUid_Getexistingreferencenumber()),
					Long.toString(sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck()),
					Long.toString(sumtotcnt.getTotal_getRef_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_getRef_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_getUid_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_getUid_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_activate_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_activate_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_deActivate_Retrievereferencenumber()),
					Long.toString(sumtotcnt.getTotal_deActivate_Incorrectattempt()),
					Long.toString(sumtotcnt.getTotal_totalCount()) });
			
			return new ResponseEntity<List<String[]>>(billData, HttpStatus.OK);		
	}


	@Override
	public String getDeptcodeFromUsernameforreportIgnite(String username) {
		// TODO Auto-generated method stub
		ArrayList<String> resAl = new ArrayList<String>();
		String deptName="";
		resAl = getDataFromignite();
		Map<String, String> deptCode = new HashMap<>();
		for (String responseData : resAl) {
			if(!deptCode.isEmpty())
			{
				break;
			}
			String[] responseDataArray = responseData.split(",");
			if ( responseDataArray.length > 10 && !responseDataArray[10].isEmpty() && !responseDataArray[9].isEmpty() &&  responseDataArray[9].contentEquals(username)) {
				 deptCode.put(username, responseDataArray[10]);		
				 deptName=responseDataArray[0];
			}
		}
	
		return deptCode.get(username)+","+deptName;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getDataFromignite() {
		ArrayList<String> resAl = new ArrayList<String>();
		try {
			String url = env.getProperty("apiurl");
			RestTemplate rt = new RestTemplate();
//			byte[] b = rt.getForObject(url, byte[].class);
//			resAl = (ArrayList<String>) SerializationUtils.deserialize(b);
			WrapperClass b  = rt.getForObject(url, WrapperClass.class);
			resAl = (ArrayList<String>) SerializationUtils.deserialize(b.getData());
			return resAl;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return resAl;
	}

}




//////////old class

//package in.cdac.portal.services;
//
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.SerializationUtils;
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
//import in.cdac.portal.billingModule.BillingServicesImpl;
//import in.cdac.portal.dao.UserDao;
//import in.cdac.portal.modal.Summary;
//import in.cdac.portal.modal.SummaryReportTotals;
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//@Service
//public class SummaryServicesImpl implements SummaryServices {
//	private final static Logger logger = Logger.getLogger(SummaryServicesImpl.class);
//
//	@Autowired
//	UserDao userDao;
//	@Autowired
//	Environment env;
//	@Autowired
//	BillingServicesImpl billSer;
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public ResponseEntity<byte[]> getSummaryForJasperIgnite(String[] datedata, String username) {
//
//		String[] dates = getStartandLastDate(datedata);
//		String startDate = dates[0];
//		String lastDate = dates[1];
//		try {
//			String nameandcode = userDao.getDeptcodeFromUsernameforreport(username);
//			String[] deptnamenadcode = nameandcode.split(",");
//			String deptName = deptnamenadcode[1];
//
//			String url = env.getProperty("igniteapiforreports");
//			RestTemplate rt = new RestTemplate();
//			String[] arr = { deptnamenadcode[0], startDate, lastDate };
//			byte[] bdata = rt.postForObject(url, arr, byte[].class);
//			ArrayList<String> resAl = new ArrayList<String>();
//			resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);
//			BillingServicesImpl.resAl = resAl;
//			List<Summary> summaryReport = new ArrayList<>();
//			summaryReport = billSer.getOprSummaryForJasper(deptnamenadcode[0]);
//			if (summaryReport == null) {
//				return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
//			}
//
//			SummaryReportTotals sumtotcnt = getTotalCountsForSummary(summaryReport);
//			Map<String, Object> empParams = new HashMap<String, Object>();
//			empParams.put("CollectionParamBean", new JRBeanCollectionDataSource(summaryReport));
//			empParams.put("DeptName", deptName);
//			empParams.put("startDate", startDate);
//			empParams.put("lastDate", lastDate);
//			empParams.put("struid_san", sumtotcnt.getTotal_strUid_StoreAadhaarNumber());
//			empParams.put("struid_ger", sumtotcnt.getTotal_strUid_Getexistingreferencenumber());
//			empParams.put("struid_adc", sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck());
//			empParams.put("getref_rr", sumtotcnt.getTotal_getRef_Retrievereferencenumber());
//			empParams.put("getref_ie", sumtotcnt.getTotal_getRef_Incorrectattempt());
//			empParams.put("getuid_rr", sumtotcnt.getTotal_getUid_Retrievereferencenumber());
//			empParams.put("getuid_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
//			empParams.put("activate_rr", sumtotcnt.getTotal_activate_Retrievereferencenumber());
//			empParams.put("activate_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
//			empParams.put("deactivate_rr", sumtotcnt.getTotal_deActivate_Retrievereferencenumber());
//			empParams.put("deactivate_ie", sumtotcnt.getTotal_deActivate_Incorrectattempt());
//			empParams.put("totcnt", sumtotcnt.getTotal_totalCount());
//			JasperPrint Report = JasperFillManager.fillReport(
//					JasperCompileManager.compileReport(
//							ResourceUtils.getFile("classpath:" + env.getProperty("jasper.summary")).getAbsolutePath()),
//					empParams, new JREmptyDataSource());
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_PDF);
//			headers.setContentDispositionFormData("filename", "Summary.pdf");
//			logger.info("File Generated");
//			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(Report), headers, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("Exception " + e.getMessage());
//			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//	@Override
//	public ResponseEntity<byte[]> getSummaryForJasperIgniteapp(String[] datedata, String appcode) {	
//		logger.info("getSummaryForJasperIgnite  "+datedata[0]+"="+datedata[1]+"="+datedata[2]);
//		String[] dates = getStartandLastDate(datedata);
//		String first= dates[0];
//		String last = dates[1];	
//		logger.info("StartDate: "+first +" lastDate: "+last);
//		try {	
//		String url=env.getProperty("igniteapiforappwisereport");
//		RestTemplate rt = new RestTemplate();
//		String [] arr= {appcode,first,last};
//		byte [] bdata=rt.postForObject(url, arr,byte[].class);
//		ArrayList<String> resAl = new ArrayList<String>();
//		resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);		
//		BillingServicesImpl.resAl = resAl;
//		 List<Summary> summaryReport = new ArrayList<>();
//		 summaryReport =  billSer.getOprSummaryForJasperApkWise(appcode);
//		 if(summaryReport==null)
//		 {
//			 logger.info("Data not found for request ----------");
//			 return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
//		 }
//		Map<String, Object> SummaryParam = new HashMap<String, Object>();	
//		SummaryParam.put("CollectionParamBean", new JRBeanCollectionDataSource(summaryReport));
//		SummaryParam.put("startDate", first);
//		SummaryParam.put("lastDate", last);
//		JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:" + env.getProperty("jasper.summary.app")).getAbsolutePath())
//				, SummaryParam // dynamic parameters
//				, new JREmptyDataSource());
//
//		HttpHeaders headers = new HttpHeaders();
//		// set the PDF format
//		headers.setContentType(MediaType.APPLICATION_PDF);
//		headers.setContentDispositionFormData("filename", "Summary.pdf");
//		// create the report in PDF format
//		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.info("Exception "+e.getMessage());
//			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//
//	@Override
//	public ResponseEntity<List<String[]>> getSummaryForcsv(String[] datedata, String username) {
//
//		List<String[]> billData = new ArrayList<>();
//		billData.add(new String[] { "Application Name", "Store Aadhaar Number", "Store Aadhaar Number",
//				"Store Aadhaar Number", "Get Reference Number", "Get Reference Number", "Get UID", "Get UID",
//				"Activate", "Activate", "Deactivate", "Deactivate", "Total Counts" });
//		billData.add(new String[] { "", "Store Aadhaar Number", ":Get existing reference", "Aadhaar duplicate check",
//				"Retrieve reference ", "Incorrect attempt\r\n" + "", "Retrieve reference ",
//				"Incorrect attempt\r\n" + "", "Retrieve reference ", "Incorrect attempt\r\n" + "",
//				"Retrieve reference ", "Incorrect attempt\r\n" + "", "" });
//		String[] dates = getStartandLastDate(datedata);
//		String startDate = dates[0];
//		String lastDate = dates[1];
//		try {
//			String nameandcode = userDao.getDeptcodeFromUsernameforreport(username);
//			String[] deptnamenadcode = nameandcode.split(",");
//			String url = env.getProperty("igniteapiforreports");
//			RestTemplate rt = new RestTemplate();
//			String[] arr = { deptnamenadcode[0], startDate, lastDate };
//			byte[] bdata = rt.postForObject(url, arr, byte[].class);
//			ArrayList<String> resAl = new ArrayList<String>();
//			resAl = (ArrayList<String>) SerializationUtils.deserialize(bdata);
//			BillingServicesImpl.resAl = resAl;
//			List<Summary> summaryReport = new ArrayList<>();
//			summaryReport = billSer.getOprSummaryForJasper(deptnamenadcode[0]);
//			if (summaryReport == null) {
//				throw new Exception("Data not found for request");
//			}
//			for (Summary summary : summaryReport) {
//				billData.add(new String[] { summary.getApplicationName(), summary.getStrUid_StoreAadhaarNumber(),
//						summary.getStrUid_Getexistingreferencenumber(), summary.getGetRef_Incorrectattempt(),
//						summary.getGetRef_Retrievereferencenumber(), summary.getGetRef_Incorrectattempt(),
//						summary.getGetUid_Retrievereferencenumber(), summary.getGetUid_Incorrectattempt(),
//						summary.getActivate_Retrievereferencenumber(), summary.getActivate_Incorrectattempt(),
//						summary.getDeActivate_Retrievereferencenumber(), summary.getDeActivate_Incorrectattempt() });
//			}
//
//			SummaryReportTotals sumtotcnt = getTotalCountsForSummary(summaryReport);
//			billData.add(new String[] { "Total", Long.toString(sumtotcnt.getTotal_strUid_StoreAadhaarNumber()),
//					Long.toString(sumtotcnt.getTotal_strUid_Getexistingreferencenumber()),
//					Long.toString(sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck()),
//					Long.toString(sumtotcnt.getTotal_getRef_Retrievereferencenumber()),
//					Long.toString(sumtotcnt.getTotal_getRef_Incorrectattempt()),
//					Long.toString(sumtotcnt.getTotal_getUid_Retrievereferencenumber()),
//					Long.toString(sumtotcnt.getTotal_getUid_Incorrectattempt()),
//					Long.toString(sumtotcnt.getTotal_activate_Retrievereferencenumber()),
//					Long.toString(sumtotcnt.getTotal_activate_Incorrectattempt()),
//					Long.toString(sumtotcnt.getTotal_deActivate_Retrievereferencenumber()),
//					Long.toString(sumtotcnt.getTotal_deActivate_Incorrectattempt()),
//					Long.toString(sumtotcnt.getTotal_totalCount()) });
////			Map<String, Object> empParams = new HashMap<String, Object>();
////			empParams.put("CollectionParamBean", new JRBeanCollectionDataSource(summaryReport));
////			empParams.put("DeptName", deptName);
////			empParams.put("startDate", startDate);
////			empParams.put("lastDate", lastDate);
////			empParams.put("struid_san", sumtotcnt.getTotal_strUid_StoreAadhaarNumber());
////			empParams.put("struid_ger", sumtotcnt.getTotal_strUid_Getexistingreferencenumber());
////			empParams.put("struid_adc", sumtotcnt.getTotal_strUid_Aadhaarduplicatecheck());
////			empParams.put("getref_rr", sumtotcnt.getTotal_getRef_Retrievereferencenumber());
////			empParams.put("getref_ie", sumtotcnt.getTotal_getRef_Incorrectattempt());
////			empParams.put("getuid_rr", sumtotcnt.getTotal_getUid_Retrievereferencenumber());
////			empParams.put("getuid_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
////			empParams.put("activate_rr", sumtotcnt.getTotal_activate_Retrievereferencenumber());
////			empParams.put("activate_ie", sumtotcnt.getTotal_getUid_Incorrectattempt());
////			empParams.put("deactivate_rr", sumtotcnt.getTotal_deActivate_Retrievereferencenumber());
////			empParams.put("deactivate_ie", sumtotcnt.getTotal_deActivate_Incorrectattempt());
////			empParams.put("totcnt", sumtotcnt.getTotal_totalCount());
////			JasperPrint Report = JasperFillManager.fillReport(
////					JasperCompileManager.compileReport(
////							ResourceUtils.getFile("classpath:" + env.getProperty("jasper.summary")).getAbsolutePath()),
////					empParams, new JREmptyDataSource());
////
////			HttpHeaders headers = new HttpHeaders();
////			headers.setContentType(MediaType.APPLICATION_PDF);
////			headers.setContentDispositionFormData("filename", "Summary.pdf");
////			logger.info("File Generated");
//			return new ResponseEntity<List<String[]>>(billData, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.info("Exception " + e.getMessage());
//			return new ResponseEntity<List<String[]>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
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
//			} else if (datedata[0].contentEquals("quarter")) {
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
//			// TODO: handle exception
//			logger.info(e.getMessage());
//		}
//		return null;
//	}
//
//	private SummaryReportTotals getTotalCountsForSummary(List<Summary> summaryReport) {
//		// TODO Auto-generated method stub
//
//		Long total_strUid_StoreAadhaarNumber = (long) 0;
//		Long total_strUid_Getexistingreferencenumber = (long) 0;
//		Long total_strUid_Aadhaarduplicatecheck = (long) 0;
//		Long total_getRef_Retrievereferencenumber = (long) 0;
//		Long total_getRef_Incorrectattempt = (long) 0;
//		Long total_getUid_Retrievereferencenumber = (long) 0;
//		Long total_getUid_Incorrectattempt = (long) 0;
//		Long total_activate_Retrievereferencenumber = (long) 0;
//		Long total_activate_Incorrectattempt = (long) 0;
//		Long total_deActivate_Retrievereferencenumber = (long) 0;
//		Long total_deActivate_Incorrectattempt = (long) 0;
//		Long total_totalCount = (long) 0;
//		SummaryReportTotals sumtotcnt = new SummaryReportTotals();
//		for (Iterator<Summary> iterator = summaryReport.iterator(); iterator.hasNext();) {
//			Summary summary = iterator.next();
//
//			total_strUid_StoreAadhaarNumber = total_strUid_StoreAadhaarNumber
//					+ Long.parseLong(summary.getStrUid_StoreAadhaarNumber());
//			total_strUid_Getexistingreferencenumber += Long.parseLong(summary.getStrUid_Getexistingreferencenumber());
//			total_strUid_Aadhaarduplicatecheck += Long.parseLong(summary.getStrUid_Aadhaarduplicatecheck());
//			total_getRef_Retrievereferencenumber += Long.parseLong(summary.getGetRef_Retrievereferencenumber());
//			total_getRef_Incorrectattempt += Long.parseLong(summary.getGetRef_Incorrectattempt());
//			total_getUid_Retrievereferencenumber += Long.parseLong(summary.getGetUid_Retrievereferencenumber());
//			total_getUid_Incorrectattempt += Long.parseLong(summary.getGetUid_Incorrectattempt());
//			total_activate_Retrievereferencenumber += Long.parseLong(summary.getActivate_Retrievereferencenumber());
//			total_activate_Incorrectattempt += Long.parseLong(summary.getActivate_Incorrectattempt());
//			total_deActivate_Retrievereferencenumber += Long.parseLong(summary.getDeActivate_Retrievereferencenumber());
//			total_deActivate_Incorrectattempt += Long.parseLong(summary.getDeActivate_Incorrectattempt());
//			total_totalCount += Long.parseLong(summary.getTotalCount());
//
//		}
//		sumtotcnt.setTotal_strUid_StoreAadhaarNumber(total_strUid_StoreAadhaarNumber);
//		sumtotcnt.setTotal_strUid_Getexistingreferencenumber(total_strUid_Getexistingreferencenumber);
//		sumtotcnt.setTotal_strUid_Aadhaarduplicatecheck(total_strUid_Aadhaarduplicatecheck);
//		sumtotcnt.setTotal_getRef_Retrievereferencenumber(total_getRef_Retrievereferencenumber);
//		sumtotcnt.setTotal_getRef_Incorrectattempt(total_getRef_Incorrectattempt);
//		sumtotcnt.setTotal_getUid_Retrievereferencenumber(total_getUid_Retrievereferencenumber);
//		sumtotcnt.setTotal_getUid_Incorrectattempt(total_getUid_Incorrectattempt);
//		sumtotcnt.setTotal_activate_Retrievereferencenumber(total_activate_Retrievereferencenumber);
//		sumtotcnt.setTotal_activate_Incorrectattempt(total_activate_Incorrectattempt);
//		sumtotcnt.setTotal_deActivate_Retrievereferencenumber(total_deActivate_Retrievereferencenumber);
//		sumtotcnt.setTotal_deActivate_Incorrectattempt(total_deActivate_Incorrectattempt);
//		sumtotcnt.setTotal_totalCount(total_totalCount);
//
//		return sumtotcnt;
//	}
//
//}
