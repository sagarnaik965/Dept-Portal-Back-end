package in.cdac.portal.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int deptId;
	private String ac;
	private String addressLine;
	private String caEmail;
	private String cdEmail;
	private String cdMobile;
	private String cdName;
	private String cdPhone;
	private String city;
	private String deptName;
	private Timestamp emailVerificationTimestamp;
	private boolean isActive;
	private String passwd;
	private String pincode;
	private Timestamp registrationTimestamp;
	private String remarks;
	private String schemaName;
	private String stateCode;
	private String updateBy;
	private Timestamp updateTimestamp;
	private Timestamp fpRequestTimestamp;
	private boolean isFpActive;
	private String username;
	private UUID fpToken;
	private String tenantname;
	
	public String getTenantname() {
		return tenantname;
	}
	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCaEmail() {
		return caEmail;
	}
	public void setCaEmail(String caEmail) {
		this.caEmail = caEmail;
	}
	public String getCdEmail() {
		return cdEmail;
	}
	public void setCdEmail(String cdEmail) {
		this.cdEmail = cdEmail;
	}
	public String getCdMobile() {
		return cdMobile;
	}
	public void setCdMobile(String cdMobile) {
		this.cdMobile = cdMobile;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public String getCdPhone() {
		return cdPhone;
	}
	public void setCdPhone(String cdPhone) {
		this.cdPhone = cdPhone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Timestamp getEmailVerificationTimestamp() {
		return emailVerificationTimestamp;
	}
	public void setEmailVerificationTimestamp(Timestamp emailVerificationTimestamp) {
		this.emailVerificationTimestamp = emailVerificationTimestamp;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Timestamp getRegistrationTimestamp() {
		return registrationTimestamp;
	}
	public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	public Timestamp getFpRequestTimestamp() {
		return fpRequestTimestamp;
	}
	public void setFpRequestTimestamp(Timestamp fpRequestTimestamp) {
		this.fpRequestTimestamp = fpRequestTimestamp;
	}
	public boolean isFpActive() {
		return isFpActive;
	}
	public void setFpActive(boolean isFpActive) {
		this.isFpActive = isFpActive;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UUID getFpToken() {
		return fpToken;
	}
	public void setFpToken(UUID fpToken) {
		this.fpToken = fpToken;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [deptId=" + deptId + ", ac=" + ac + ", addressLine=" + addressLine + ", caEmail=" + caEmail
				+ ", cdEmail=" + cdEmail + ", cdMobile=" + cdMobile + ", cdName=" + cdName + ", cdPhone=" + cdPhone
				+ ", city=" + city + ", deptName=" + deptName + ", emailVerificationTimestamp="
				+ emailVerificationTimestamp + ", isActive=" + isActive + ", passwd=" + passwd + ", pincode=" + pincode
				+ ", registrationTimestamp=" + registrationTimestamp + ", remarks=" + remarks + ", schemaName="
				+ schemaName + ", stateCode=" + stateCode + ", updateBy=" + updateBy + ", updateTimestamp="
				+ updateTimestamp + ", fpRequestTimestamp=" + fpRequestTimestamp + ", isFpActive=" + isFpActive
				+ ", username=" + username + ", fpToken=" + fpToken + ", tenantname=" + tenantname + "]";
	}
	

	
}