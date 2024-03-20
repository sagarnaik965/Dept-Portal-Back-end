package in.cdac.portal.modal;

import java.util.HashMap;

public class JasperBill {

	String quarter;
	String deptName;
	String totalApkCount;
	String slab;
	String amount;
	HashMap<String, String> breakup ;
	public JasperBill() {
		// TODO Auto-generated constructor stub
	}
	public JasperBill(String quarter, String deptName, String totalApkCount, String slab, String amount,
			HashMap<String, String> breakup) {
		super();
		this.quarter = quarter;
		this.deptName = deptName;
		this.totalApkCount = totalApkCount;
		this.slab = slab;
		this.amount = amount;
		this.breakup = breakup;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTotalApkCount() {
		return totalApkCount;
	}
	public void setTotalApkCount(String totalApkCount) {
		this.totalApkCount = totalApkCount;
	}
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public HashMap<String, String> getBreakup() {
		return breakup;
	}
	public void setBreakup(HashMap<String, String> breakup) {
		this.breakup = breakup;
	}
	
	
}
