package in.cdac.portal.modal;


import java.util.Date;

public class UserStatus {
	private String id;
	
	private String deptName;
	
	private String userName;
	private String auaCode;
	private String lk;
	private String email;
	private String appName;
	private boolean loginStatus;
	private boolean authStatus;
	private boolean otpStatus;
	private boolean ekycStatus;
	private String type;
	private String validity;
	private Date validTill;
	
	public UserStatus() {
		
	}

	public UserStatus(String id,String deptname, String username, boolean loginStatus) {
		super();
		this.id = id;
		this.deptName = deptname;
		this.userName = username;
		this.loginStatus = loginStatus;
	
	}
	
	
	public UserStatus(String id,String deptname, String username, boolean loginStatus,String type) {
		super();
		this.id = id;
		this.deptName = deptname;
		this.userName = username;
		this.loginStatus = loginStatus;
		this.type=type;
	}


	

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	/*
	 * @Override public String toString() { return "UserStatus [id=" + id +
	 * ", deptName=" + deptName + ", userName=" + userName + ", auaCode=" + auaCode
	 * + ", loginStatus=" + loginStatus + ", authStatus=" + authStatus +
	 * ", otpStatus=" + otpStatus + ", ekycStatus=" + ekycStatus + ", type=" + type
	 * + "]"; }
	 */
	@Override
	public String toString() {
		return "UserStatus [id=" + id + ", deptName=" + deptName + ", userName=" + userName + ", auaCode=" + auaCode
				+ ", lk=" + lk + ", email=" + email + ", appName=" + appName + ", loginStatus=" + loginStatus
				+ ", authStatus=" + authStatus + ", otpStatus=" + otpStatus + ", ekycStatus=" + ekycStatus + ", type="
				+ type + ", validity=" + validity + ", validTill=" + validTill + "]";
	}

	public UserStatus(String id, String deptName, String userName, String auaCode, boolean loginStatus, boolean authStatus,
			boolean otpStatus, boolean ekycStatus, String type) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.userName = userName;
		this.auaCode = auaCode;
		this.loginStatus = loginStatus;
		this.authStatus = authStatus;
		this.otpStatus = otpStatus;
		this.ekycStatus = ekycStatus;
		this.type = type;
	}

	

	public String getAuaCode() {
		return auaCode;
	}

	public void setAuaCode(String auaCode) {
		this.auaCode = auaCode;
	}

	public boolean isAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(boolean authStatus) {
		this.authStatus = authStatus;
	}

	public boolean isOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(boolean otpStatus) {
		this.otpStatus = otpStatus;
	}

	public boolean isEkycStatus() {
		return ekycStatus;
	}

	public void setEkycStatus(boolean ekycStatus) {
		this.ekycStatus = ekycStatus;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public String getLk() {
		return lk;
	}

	public void setLk(String lk) {
		this.lk = lk;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	

}
