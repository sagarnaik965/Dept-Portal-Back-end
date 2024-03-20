package in.cdac.portal.modal;

public class StaticDept {
	
//	public String deptname;
	public String appname;
	public int transcount;
//	public String getDeptname() {
//		return deptname;
//	}
//	public void setDeptname(String deptname) {
//		this.deptname = deptname;
//	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public int getTranscount() {
		return transcount;
	}
	public void setTranscount(int transcount) {
		this.transcount = transcount;
	}
	@Override
	public String toString() {
		return "StaticDept [  appname=" + appname + ", transcount=" + transcount + "]";
	}
	
	

}
