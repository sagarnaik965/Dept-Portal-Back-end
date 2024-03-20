package in.cdac.portal.modal;

public class AppList {

	public String appname;
	public String appcode;
	
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	//public String deptcode;
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
//	public String getDeptcode() {
//		return deptcode;
//	}
//	public void setDeptcode(String deptcode) {
//		this.deptcode = deptcode;
//	}
	public AppList(String appname, String appcode) {
		super();
		this.appname = appname;
		this.appcode = appcode;
	}
	@Override
	public String toString() {
		return "AppList [appname=" + appname + ", appcode=" + appcode + "]";
	}
	public AppList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

