package in.cdac.portal.billingModule;

import java.util.HashMap;
import java.util.Map;

public class Report {

	public String apkName;
	public Map<String, HashMap<String, Integer>> summary;
	public int apkCount;

	public Report() {
		// TODO Auto-generated constructor stub
		summary = new HashMap<String, HashMap<String, Integer>>();
	}

	public int getApkCount() {
		return apkCount;
	}

	public void setApkCount(int apkCount) {
		this.apkCount = apkCount;
	}

	public Report(String apkName, Map<String, HashMap<String, Integer>> summary, int apkCount) {
		super();
		this.apkName = apkName;
		this.summary = summary;
		this.apkCount = apkCount;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public Map<String, HashMap<String, Integer>> getSummary() {
		return summary;
	}

	public void setSummary(Map<String, HashMap<String, Integer>> summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Report [apkName=" + apkName + ", summary=" + summary + ", apkCount=" + apkCount + "]";
	}

	
}
