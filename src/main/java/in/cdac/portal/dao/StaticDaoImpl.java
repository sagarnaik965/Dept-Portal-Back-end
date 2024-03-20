//package in.cdac.portal.dao;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StaticDaoImpl implements StaticDao {
//	
//	@Autowired
//	Environment env;
//
//
//	@Override
//	public int getTotaldeshDeptCouont() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		Set<String> deptList = new HashSet<String>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//
//					apkList.add(CscData[2].replace("\"", ""));
//					deptList.add(CscData[0].replace("\"", ""));
//				}
//			}
//
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		return deptList.size();
//	}
//
//
//	@Override
//	public int getTotaldeshAcCount() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		List<Integer> tranCount = new ArrayList<Integer>();
//		Set<String> deptList = new HashSet<String>();
//		Map<String, Integer> apkAndCount = new HashMap<String, Integer>();
//		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//
//				if (!CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					apkList.add(CscData[1].replace("\"", ""));
//
//					if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//						tranCount.add(0);
//						apkAndCount.put(CscData[1].replace("\"", ""), 0);
//					}
//					deptList.add(CscData[0].replace("\"", ""));
//					deptAndCount.put(CscData[0].replace("\"", ""), 0);
//
//				}
//
//			}
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		return (apkList.size());
//	}
//
//
//	@Override
//	public int getTotaldeshTransCount() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		List<Integer> tranCount = new ArrayList<Integer>();
//		Set<String> deptList = new HashSet<String>();
//		Map<String, Integer> apkAndCount = new HashMap<String, Integer>();
//		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[2].replace("\"", "").isEmpty() && !CscData[0].replace("\"", "").isEmpty()
//						&& !CscData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					apkList.add(CscData[2].replace("\"", ""));
//					tranCount.add(Integer.valueOf(CscData[7].replace("\"", "")));
//					apkAndCount.put(CscData[2].replace("\"", ""), Integer.valueOf(CscData[7].replace("\"", "")));
//					deptList.add(CscData[0].replace("\"", ""));
//					deptAndCount.put(CscData[0].replace("\"", ""), 0);
//				}
//			}
//			br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
//						if (!CscData[0].replace("\"", "").isEmpty()
//								&& CscData[0].replace("\"", "").contentEquals(entry.getKey())) {
//							entry.setValue(entry.getValue() + Integer.valueOf(CscData[7].replace("\"", "")));
//						}
//					}
//				}
//			}
//
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//
//		int totTrasactionCount = 0;
//		for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
//			totTrasactionCount = entry.getValue() + totTrasactionCount;
//		}
//		return totTrasactionCount;
//	}
//
//
//	
//
//	
//	
//	public Map<String, Integer> getapkAndTransAcToDept(String dept) {
//
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		String line = "";
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[0].replaceAll("^\"|\"$", "").isEmpty()
//						&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					if (dept.contains((CscData[0]).replaceAll("^\"|\"$", ""))
//							&& !CscData[0].replaceAll("^\"|\"$", "").isEmpty()
//							&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//						if (map.get((CscData[1]).replaceAll("^\"|\"$", "")) != null
//								&& !CscData[1].replaceAll("^\"|\"$", "").isEmpty()
//								&& !CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//							if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//								map.put((CscData[1]).replaceAll("^\"|\"$", ""),
//										map.get((CscData[1]).replaceAll("^\"|\"$", "")) + 0);
//							} else {
//								map.put((CscData[1]).replaceAll("^\"|\"$", ""),
//										map.get((CscData[1]).replaceAll("^\"|\"$", ""))
//												+ Integer.parseInt((CscData[7]).replaceAll("^\"|\"$", "")));
//							}
//
//						} else {
//							if (!CscData[1].replaceAll("^\"|\"$", "").isEmpty()
//									&& !CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//								if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//									map.put((CscData[1]).replaceAll("^\"|\"$", ""), 0);
//								} else {
//
//									map.put((CscData[1]).replaceAll("^\"|\"$", ""),
//											Integer.parseInt((CscData[7]).replaceAll("^\"|\"$", "")));
//								}
//
//							}
//						}
//					}
//				}
//			}
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		return map;
//	}
//
//
//	
//	
//	
//
//	@Override
//	public Map<String, Map<String, Integer>> getdeptLists1() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		Set<String> deptList = new HashSet<String>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					apkList.add(CscData[1].replace("\"", ""));
//					deptList.add(CscData[0].replace("\"", ""));
//				}
//			}
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		Map<String, Map<String, Integer>> deptcounts1 = new HashMap<>();
//		for (String string : deptList) {
//			deptcounts1.put(string, getapkAndTransAcToDept(string));
//		}
//		
////		System.out.println(deptcounts1+"-------------------------------");
//		return deptcounts1;
//	}
//
//
//	@Override
//	public Map<String, Integer> apkWiseCount() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		List<Integer> tranCount = new ArrayList<Integer>();
//		Set<String> deptList = new HashSet<String>();
//		Map<String, Integer> apkAndCount = new HashMap<String, Integer>();
//		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[1].replace("\"", "").isEmpty() && !CscData[1].replaceAll("^\"|\"$", "").isEmpty()) {
//					apkList.add(CscData[1].replace("\"", ""));
//					if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//						tranCount.add(0);
//					} else {
//						tranCount.add(Integer.parseInt(CscData[7].replace("\"", "")));
//					}
//
//					if (apkAndCount.get(CscData[1].replace("\"", "").toString()) != null
//							&& !CscData[1].replace("\"", "").isEmpty()) {
//						apkAndCount.put(CscData[1].replace("\"", ""),
//								apkAndCount.get(CscData[1].replace("\"", "").toString())
//										+ Integer.parseInt(CscData[7].replace("\"", "")));
//					} else {
//						if (!CscData[1].replace("\"", "").isEmpty()
//								&& !CscData[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//								&& !CscData[1].replaceAll("^\"|\"$", "").isEmpty())
//							if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//								apkAndCount.put(CscData[1].replace("\"", ""), 0);
//							} else {
//								apkAndCount.put(CscData[1].replace("\"", ""),
//										Integer.parseInt(CscData[7].replace("\"", "")));
//							}
//
//					}
//					if (!CscData[0].replace("\"", "").isEmpty()
//							&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//							&& !CscData[0].replaceAll("^\"|\"$", "").isEmpty()) {
//						deptList.add(CscData[0].replace("\"", ""));
//						deptAndCount.put(CscData[0].replace("\"", ""), 0);
//					}
//				}
//			}
//			loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
//					if (!CscData[1].replace("\"", "").isEmpty()
//							&& CscData[2].replace("\"", "").contentEquals(entry.getKey())) {
//						entry.setValue(entry.getValue() + Integer.valueOf(CscData[1].replace("\"", "")));
//					}
//				}
//			}
//
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		return apkAndCount;
//	}
//
//
//	@Override
//	public Map<String, Integer> deptwisetransaction() {
//		// TODO Auto-generated method stub
//		String line = "";
//		Set<String> apkList = new HashSet<String>();
//		Set<String> deptList = new HashSet<String>();
//		Map<String, Integer> deptAndCount = new HashMap<String, Integer>();
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			int loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (
//
//				!CscData[2].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
//						&& !CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//					apkList.add(CscData[2].replace("\"", ""));
//					deptList.add(CscData[0].replace("\"", ""));
//					deptAndCount.put(CscData[0].replace("\"", ""), 0);
//				}
//			}
//			br = new BufferedReader(new FileReader(env.getProperty("locationforchart")));
//			loop = 0;
//			while ((line = br.readLine()) != null) {
//				loop++;
//				String[] CscData = line.split(",");
//				if (!CscData[0].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//
//					for (Map.Entry<String, Integer> entry : deptAndCount.entrySet()) {
//						if (CscData[0].replace("\"", "").contentEquals(entry.getKey())) {
//							if (CscData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
//								entry.setValue(entry.getValue() + 0);
//							} else {
//								entry.setValue(entry.getValue() + Integer.valueOf(CscData[7].replace("\"", "")));
//							}
//						}
//					}
//				}
//			}
//
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		return deptAndCount;
//	}
//
//	
//
//}
