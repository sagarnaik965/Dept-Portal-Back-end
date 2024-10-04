package in.cdac.portal.billingModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GetFileData {
	static Map<String, String> apkCode_apk_name;
	static HashSet<String> allApkCodesFromFetch;
	public static ArrayList<String> resAl;
	public static HashSet<String> typesOfOPR;
	public static Map<String, HashSet<String>> dept_code_apk_code;

	public static ArrayList<String> convertArray(ArrayList<String> res) {
		ArrayList<String> ret = new ArrayList<String>();
		for (String line : res) {
			String val[] = line.split(",");
			ret.add(val[2].concat(",").concat(val[4]).concat(",").concat(val[7]).concat(",").concat(val[5]).concat(",")
					.concat(val[3]).concat(",").concat(val[8]).concat(",").concat(val[6]).concat(",")
					.concat(val.length == 12 ? val[11] : "null"));
		}

		resAl = ret;
		return ret;
	}

	public static ArrayList<Report> getAllOprSummaryForJasper(String dept) {
		ArrayList<Report> repoList = new ArrayList<Report>();
		new HashMap<String, HashMap<String, Integer>>();
		ArrayList<String> al = new ArrayList<String>(dept_code_apk_code.get(dept));
		Map<String, Map<String, Integer>> finalRet = new HashMap<String, Map<String, Integer>>();
		HashSet<String> tempOps = allOpsName();
		for (String apk : al) {
			Report repo = new Report();
			repo.setApkName(apk);
			Map<String, Integer> temp = new HashMap<String, Integer>();
			for (String opr : tempOps) {
				temp.put(opr, 0);
				repo.getSummary().put(opr, new HashMap<String, Integer>());
			}
			repoList.add(repo);
			finalRet.put(apk, temp);
		}

		typesOfOPR = new HashSet<String>();
		for (String line : convertArray(resAl)) {
			String[] val = line.split(",");

			typesOfOPR.add(val[6]);
			if (val[7].contentEquals("null")) {
				typesOfOPR.add("null");
			} else {
				typesOfOPR.add(val[7]);
			}
		}
		HashSet<String> temp = typesOfOPR;
		for (Report r : repoList) {
			for (String s : tempOps) {
				for (String t : temp) {
					r.getSummary();
					r.getSummary().get(s);
					r.getSummary().get(s).put(t, 0);
				}
			}
		}
		for (String line : resAl) {
			String[] val = line.split(",");
			for (Report rpt : repoList) {
				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())) {

					rpt.setApkCount(rpt.getApkCount()
							+ Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]));
				}

				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())
						&& (rpt.getSummary().containsKey(val[3])
								|| (rpt.getSummary().get(val[3]).containsKey(val[6])))) {
					int count = Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]);
//					int  = Integer.valueOf(val[2].equalsIgnoreCase(" ")? val[2]:"0");

					count = count + rpt.getSummary().get(val[3]).get(val[6]);
					rpt.getSummary().get(val[3]).replace(val[6], count);
				}

				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())
						&& (rpt.getSummary().containsKey(val[3])
								|| (rpt.getSummary().get(val[3]).containsKey(val[7])))) {
					// int count = Integer.valueOf(val[2].replaceAll("^\"|\"$", ""));
					int count = Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]);

					count = count + rpt.getSummary().get(val[3]).get(val[7]);
					rpt.getSummary().get(val[3]).replace(val[7], count);

				} else {
				}
			}
		}
		return repoList;

	}

	public static ArrayList<Report> getAllOprSummaryForJasperApkWise(String apkCode) {
		ArrayList<Report> repoList = new ArrayList<Report>();
		new HashMap<String, HashMap<String, Integer>>();
		ArrayList<String> al = new ArrayList<String>();
		al.add(apkCode);
		Map<String, Map<String, Integer>> finalRet = new HashMap<String, Map<String, Integer>>();
		HashSet<String> tempOps = allOpsName();
		for (String apk : al) {
			Report repo = new Report();
			repo.setApkName(apk);
			Map<String, Integer> temp = new HashMap<String, Integer>();
			for (String opr : tempOps) {
				temp.put(opr, 0);
				repo.getSummary().put(opr, new HashMap<String, Integer>());
			}
			repoList.add(repo);
			finalRet.put(apk, temp);
		}

		typesOfOPR = new HashSet<String>();
		for (String line : convertArray(resAl)) {
			String[] val = line.split(",");

			typesOfOPR.add(val[6]);
			if (val[7].contentEquals("null")) {
				typesOfOPR.add("null");
			} else {
				typesOfOPR.add(val[7]);
			}
		}
		HashSet<String> temp = typesOfOPR;
		for (Report r : repoList) {
			for (String s : tempOps) {
				for (String t : temp) {
					r.getSummary();
					r.getSummary().get(s);
					r.getSummary().get(s).put(t, 0);
				}
			}
		}
		for (String line : resAl) {
			String[] val = line.split(",");

			for (Report rpt : repoList) {
				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())) {

					rpt.setApkCount(rpt.getApkCount()
							+ Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]));
				}

				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())
						&& (rpt.getSummary().containsKey(val[3])
								|| (rpt.getSummary().get(val[3]).containsKey(val[6])))) {
					int count = Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]);

					count = count + rpt.getSummary().get(val[3]).get(val[6]);
					rpt.getSummary().get(val[3]).replace(val[6], count);
				}

				if (val[0].replaceAll("^\"|\"$", "").contentEquals(rpt.getApkName())
						&& (rpt.getSummary().containsKey(val[3])
								|| (rpt.getSummary().get(val[3]).containsKey(val[7])))) {
					int count = Integer.valueOf(val[2].contentEquals(" ") || val[2].contentEquals("") ? "0" : val[2]);

					count = count + rpt.getSummary().get(val[3]).get(val[7]);
					rpt.getSummary().get(val[3]).replace(val[7], count);

				} else {
				}
			}
		}
		return repoList;

	}

	public static HashSet<String> allOpsName() {
		HashSet<String> allOpsName = new HashSet<String>();
		try {

			for (String line : resAl) {
				String[] val = line.split(",");
				allOpsName.add(val[5].replaceAll("^\"|\"$", ""));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allOpsName;
	}

}
