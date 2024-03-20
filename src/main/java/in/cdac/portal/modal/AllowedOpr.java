package in.cdac.portal.modal;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class AllowedOpr {

	public Boolean is_Struid;
	public Boolean is_Getrefnum;
	public Boolean is_Getuid;
	public Boolean is_Activate;
	public Boolean is_Deactivate;
	public Boolean is_dupcheck;
	public Boolean getIs_dupcheck() {
		return is_dupcheck;
	}
	
	
	public void setIs_dupcheck(Boolean is_dupcheck) {
		this.is_dupcheck = is_dupcheck;
	}
	public  String applicode;
	public String getApplicode() {
		return applicode;
	}
	public void setApplicode(String applicode) {
		this.applicode = applicode;
	}
	public Boolean getis_Struid() {
		return is_Struid;
	}
	public void setis_Struid(Boolean is_Struid) {
		this.is_Struid = is_Struid;
	}
	public Boolean getis_Getrefnum() {
		return is_Getrefnum;
	}
	public void setis_Getrefnum(Boolean is_Getrefnum) {
		this.is_Getrefnum = is_Getrefnum;
	}
	public Boolean getis_Getuid() {
		return is_Getuid;
	}
	public void setis_Getuid(Boolean is_Getuid) {
		this.is_Getuid = is_Getuid;
	}
	public Boolean getis_Activate() {
		return is_Activate;
	}
	public void setis_Activate(Boolean is_Activate) {
		this.is_Activate = is_Activate;
	}
	public Boolean getis_Deactivate() {
		return is_Deactivate;
	}
	public void setis_Deactivate(Boolean is_Deactivate) {
		this.is_Deactivate = is_Deactivate;
	}
	
	@Override
	public String toString() {
		return "AllowedOpr [is_Struid=" + is_Struid + ", is_Getrefnum=" + is_Getrefnum + ", is_Getuid=" + is_Getuid
				+ ", is_Activate=" + is_Activate + ", is_Deactivate=" + is_Deactivate + ", is_dupcheck=" + is_dupcheck
				+ ", applicode=" + applicode + "]";
	}
	}
	
	
	
	

