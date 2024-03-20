package in.cdac.portal.modal;

public class Summary {

	private String applicationName;
	private String strUid_StoreAadhaarNumber;
	private String strUid_Getexistingreferencenumber;
	private String strUid_Aadhaarduplicatecheck;
	private String getRef_Retrievereferencenumber;
	private String getRef_Incorrectattempt;
	private String getUid_Retrievereferencenumber;
	private String getUid_Incorrectattempt;
	private String activate_Retrievereferencenumber;
	private String activate_Incorrectattempt;
	private String deActivate_Retrievereferencenumber;
	private String deActivate_Incorrectattempt;
	private String totalCount;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getStrUid_StoreAadhaarNumber() {
		return strUid_StoreAadhaarNumber;
	}
	public void setStrUid_StoreAadhaarNumber(String strUid_StoreAadhaarNumber) {
		this.strUid_StoreAadhaarNumber = strUid_StoreAadhaarNumber;
	}
	public String getStrUid_Getexistingreferencenumber() {
		return strUid_Getexistingreferencenumber;
	}
	public void setStrUid_Getexistingreferencenumber(String strUid_Getexistingreferencenumber) {
		this.strUid_Getexistingreferencenumber = strUid_Getexistingreferencenumber;
	}
	public String getStrUid_Aadhaarduplicatecheck() {
		return strUid_Aadhaarduplicatecheck;
	}
	public void setStrUid_Aadhaarduplicatecheck(String strUid_Aadhaarduplicatecheck) {
		this.strUid_Aadhaarduplicatecheck = strUid_Aadhaarduplicatecheck;
	}
	public String getGetRef_Retrievereferencenumber() {
		return getRef_Retrievereferencenumber;
	}
	public void setGetRef_Retrievereferencenumber(String getRef_Retrievereferencenumber) {
		this.getRef_Retrievereferencenumber = getRef_Retrievereferencenumber;
	}
	public String getGetRef_Incorrectattempt() {
		return getRef_Incorrectattempt;
	}
	public void setGetRef_Incorrectattempt(String getRef_Incorrectattempt) {
		this.getRef_Incorrectattempt = getRef_Incorrectattempt;
	}
	public String getGetUid_Retrievereferencenumber() {
		return getUid_Retrievereferencenumber;
	}
	public void setGetUid_Retrievereferencenumber(String getUid_Retrievereferencenumber) {
		this.getUid_Retrievereferencenumber = getUid_Retrievereferencenumber;
	}
	public String getGetUid_Incorrectattempt() {
		return getUid_Incorrectattempt;
	}
	public void setGetUid_Incorrectattempt(String getUid_Incorrectattempt) {
		this.getUid_Incorrectattempt = getUid_Incorrectattempt;
	}
	public String getActivate_Retrievereferencenumber() {
		return activate_Retrievereferencenumber;
	}
	public void setActivate_Retrievereferencenumber(String activate_Retrievereferencenumber) {
		this.activate_Retrievereferencenumber = activate_Retrievereferencenumber;
	}
	public String getActivate_Incorrectattempt() {
		return activate_Incorrectattempt;
	}
	public void setActivate_Incorrectattempt(String activate_Incorrectattempt) {
		this.activate_Incorrectattempt = activate_Incorrectattempt;
	}
	public String getDeActivate_Retrievereferencenumber() {
		return deActivate_Retrievereferencenumber;
	}
	public void setDeActivate_Retrievereferencenumber(String deActivate_Retrievereferencenumber) {
		this.deActivate_Retrievereferencenumber = deActivate_Retrievereferencenumber;
	}
	public String getDeActivate_Incorrectattempt() {
		return deActivate_Incorrectattempt;
	}
	public void setDeActivate_Incorrectattempt(String deActivate_Incorrectattempt) {
		this.deActivate_Incorrectattempt = deActivate_Incorrectattempt;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "Summary [applicationName=" + applicationName + ", strUid_StoreAadhaarNumber="
				+ strUid_StoreAadhaarNumber + ", strUid_Getexistingreferencenumber=" + strUid_Getexistingreferencenumber
				+ ", strUid_Aadhaarduplicatecheck=" + strUid_Aadhaarduplicatecheck + ", getRef_Retrievereferencenumber="
				+ getRef_Retrievereferencenumber + ", getRef_Incorrectattempt=" + getRef_Incorrectattempt
				+ ", getUid_Retrievereferencenumber=" + getUid_Retrievereferencenumber + ", getUid_Incorrectattempt="
				+ getUid_Incorrectattempt + ", activate_Retrievereferencenumber=" + activate_Retrievereferencenumber
				+ ", activate_Incorrectattempt=" + activate_Incorrectattempt + ", deActivate_Retrievereferencenumber="
				+ deActivate_Retrievereferencenumber + ", deActivate_Incorrectattempt=" + deActivate_Incorrectattempt
				+ ", totalCount=" + totalCount + "]";
	}
	public Summary(String applicationName, String strUid_StoreAadhaarNumber, String strUid_Getexistingreferencenumber,
			String strUid_Aadhaarduplicatecheck, String getRef_Retrievereferencenumber, String getRef_Incorrectattempt,
			String getUid_Retrievereferencenumber, String getUid_Incorrectattempt,
			String activate_Retrievereferencenumber, String activate_Incorrectattempt,
			String deActivate_Retrievereferencenumber, String deActivate_Incorrectattempt, String totalCount) {
		super();
		this.applicationName = applicationName;
		this.strUid_StoreAadhaarNumber = strUid_StoreAadhaarNumber;
		this.strUid_Getexistingreferencenumber = strUid_Getexistingreferencenumber;
		this.strUid_Aadhaarduplicatecheck = strUid_Aadhaarduplicatecheck;
		this.getRef_Retrievereferencenumber = getRef_Retrievereferencenumber;
		this.getRef_Incorrectattempt = getRef_Incorrectattempt;
		this.getUid_Retrievereferencenumber = getUid_Retrievereferencenumber;
		this.getUid_Incorrectattempt = getUid_Incorrectattempt;
		this.activate_Retrievereferencenumber = activate_Retrievereferencenumber;
		this.activate_Incorrectattempt = activate_Incorrectattempt;
		this.deActivate_Retrievereferencenumber = deActivate_Retrievereferencenumber;
		this.deActivate_Incorrectattempt = deActivate_Incorrectattempt;
		this.totalCount = totalCount;
	}
	public Summary() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
            