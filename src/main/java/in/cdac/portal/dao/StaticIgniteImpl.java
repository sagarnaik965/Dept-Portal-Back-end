package in.cdac.portal.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.RestTemplate;

import in.cdac.portal.modal.WrapperClass;

@Service
@Primary
public class StaticIgniteImpl implements StaticDao{

	@Autowired
	Environment env;

//	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	 private final  static Logger logger = LogManager.getLogger( StaticIgniteImpl.class );

	public long getDataForChartMonthly(String Month) {
		ArrayList<String> resAl = getDataFromIgnite();

		int count = 0;
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[3].toString().isEmpty() && CsvData[3].toString() != ""
						&& !CsvData[3].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					if (Month.equals(LocalDate.parse(CsvData[3]).getMonth().toString())
							&& LocalDate.parse(CsvData[3]).getMonth().toString() != ""
							&& !CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
							&& !CsvData[7].replaceAll("^\"|\"$", "").isEmpty()) {
						count = count + Integer.parseInt(CsvData[7]);
					}
				}
			}
			return count;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	public long getDataForChart(String date) {
		ArrayList<String> resAl = getDataFromIgnite();
		int count = 0;
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[3].toString().isEmpty()) {
					if (date.contentEquals((CsvData[3])) && CsvData[3] != ""
							&& !CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
							&& !CsvData[7].replaceAll("^\"|\"$", "").isEmpty()) {
						count = count + Integer.parseInt(CsvData[7]);
					}
				}
			}
			return count;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;

	}

	public int apkCount() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()) {
					apkList.add(CsvData[1].replace("\"", "").trim());
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return (apkList.size());
	}

	public int deptCount() {
		ArrayList<String> resAl = getDataFromIgnite();

		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {

					apkList.add(CsvData[2].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return deptList.size();
	}

	public Map<String, Map<String, Integer>> deptList() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					apkList.add(CsvData[1].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Map<String, Map<String, Integer>> deptcounts1 = new HashMap<>();
		for (String string : deptList) {
			deptcounts1.put(string, getapkAndTransAcToDept(string));
		}
		return deptcounts1;
	}

	public Map<String, Integer> apkWiseCount() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		Map<String, Integer> apkAndCount = new HashMap<String, Integer>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[1].replace("\"", "").isEmpty() && !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()) {
					apkList.add(CsvData[1].replace("\"", "").trim());
					if (apkAndCount.get(CsvData[1].replace("\"", "").toString()) != null
							&& !CsvData[1].replace("\"", "").isEmpty()
							&& apkAndCount.containsKey(CsvData[1].replace("\"", "").trim())) {
						apkAndCount.put(CsvData[1].replace("\"", ""),
								apkAndCount.get(CsvData[1].replace("\"", "").toString())
										+ Integer.parseInt(CsvData[7].replace("\"", "")));
					} else {
						if (!CsvData[1].replace("\"", "").isEmpty()
								&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
								&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty())
							if (CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
								apkAndCount.put(CsvData[1].replace("\"", ""), 0);
							} else {
								apkAndCount.put(CsvData[1].replace("\"", ""),
										Integer.parseInt(CsvData[7].replace("\"", "")));
							}
					}
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return apkAndCount;
	}

	public Map<String, Integer> deptWiseCount() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					apkList.add(CsvData[2].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
					deptAndCount.put(CsvData[0].replace("\"", ""), 0);
				}
			}
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {

					for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
						if (CsvData[0].replace("\"", "").contentEquals(entry.getKey())) {
							if (CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
								entry.setValue(entry.getValue() + 0);
							} else {
								entry.setValue(entry.getValue() + Integer.valueOf(CsvData[7].replace("\"", "")));
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return deptAndCount;
	}

	public int totTrnsaction() {
		int totTrasactionCount = 0;
		ArrayList<String> resAl = getDataFromIgnite();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[7].replace("\"", "").isEmpty() 
						&& !CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					
					totTrasactionCount += Integer.parseInt(CsvData[7].replaceAll("^\"|\"$", "").trim());
				}
			}
			return totTrasactionCount;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return totTrasactionCount;
	}

	public HashSet<String> getAppListDeptWise(String dept) {
		ArrayList<String> resAl = getDataFromIgnite();
		ArrayList<String> list = new ArrayList<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					if (dept.contains((CsvData[0]).replaceAll("^\"|\"$", ""))
							&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()) {
						if (!CsvData[1].replaceAll("^\"|\"$", "").isEmpty()
								&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null"))
							list.add(CsvData[1].replaceAll("^\"|\"$", ""));
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		HashSet<String> ret = new HashSet<String>();
		ret.addAll(list);
		return ret;
	}

	public Map<String, Integer> getapkAndTransAcToDept(String dept) {
		ArrayList<String> resAl = getDataFromIgnite();

		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			for (String line : resAl) {

				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					if (dept.contains((CsvData[0]).replaceAll("^\"|\"$", ""))
							&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
							&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
						if (map.get((CsvData[1]).replaceAll("^\"|\"$", "")) != null
								&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()
								&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
							if (CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
								map.put((CsvData[1]).replaceAll("^\"|\"$", ""),
										map.get((CsvData[1]).replaceAll("^\"|\"$", "")) + 0);
							} else {
								map.put((CsvData[1]).replaceAll("^\"|\"$", ""),
										map.get((CsvData[1]).replaceAll("^\"|\"$", ""))
												+ Integer.parseInt((CsvData[7]).replaceAll("^\"|\"$", "")));
							}

						} else {
							if (!CsvData[1].replaceAll("^\"|\"$", "").isEmpty()
									&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
								if (CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
									map.put((CsvData[1]).replaceAll("^\"|\"$", ""), 0);
								} else {

									map.put((CsvData[1]).replaceAll("^\"|\"$", ""),
											Integer.parseInt((CsvData[7]).replaceAll("^\"|\"$", "")));
								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getDataFromIgnite() {
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
			logger.info(e.getMessage());
		}
		return resAl;
	}

	@Override
	public int getTotaldeshDeptCouont() {
		ArrayList<String> resAl = getDataFromIgnite();

		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {

					apkList.add(CsvData[2].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return deptList.size();
	}

	@Override
	public int getTotaldeshAcCount() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()) {
					apkList.add(CsvData[1].replace("\"", "").trim());
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return (apkList.size());
	}

	@Override
	public int getTotaldeshTransCount() {
		int totTrasactionCount = 0;
		ArrayList<String> resAl = getDataFromIgnite();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[7].replace("\"", "").isEmpty() 
						&& !CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					
					totTrasactionCount += Integer.parseInt(CsvData[7].replaceAll("^\"|\"$", "").trim());
				}
			}
			return totTrasactionCount;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return totTrasactionCount;
	}

	@Override
	public Map<String, Map<String, Integer>> getdeptLists1() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[1].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					apkList.add(CsvData[1].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		Map<String, Map<String, Integer>> deptcounts1 = new HashMap<>();
		for (String string : deptList) {
			deptcounts1.put(string, getapkAndTransAcToDept(string));
		}
		return deptcounts1;
	}

	@Override
	public Map<String, Integer> deptwisetransaction() {
		ArrayList<String> resAl = getDataFromIgnite();
		Set<String> apkList = new HashSet<String>();
		Set<String> deptList = new HashSet<String>();
		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
		try {
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
						&& !CsvData[0].replaceAll("^\"|\"$", "").isEmpty()
						&& !CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
					apkList.add(CsvData[2].replace("\"", ""));
					deptList.add(CsvData[0].replace("\"", ""));
					deptAndCount.put(CsvData[0].replace("\"", ""), 0);
				}
			}
			for (String line : resAl) {
				String[] CsvData = line.split(",");
				if (!CsvData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {

					for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
						if (CsvData[0].replace("\"", "").contentEquals(entry.getKey())) {
							if (CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
								entry.setValue(entry.getValue() + 0);
							} else {
								entry.setValue(entry.getValue() + Integer.valueOf(CsvData[7].replace("\"", "")));
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return deptAndCount;
	}
	
}
