package in.cdac.portal.modal;

import org.hibernate.validator.constraints.NotEmpty;

public class GenerateUdcCode {
	
	
	private String udc;
	
	private String deviceType;
	
	@NotEmpty(message="Lenght should be more than 3")
	private String vendorCode;
	
	
	
	public GenerateUdcCode() {
	
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getVendorCode() {
		return vendorCode;
	}
	
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getUdc() {
		return udc;
	}

	public void setUdc(String udc) {
		this.udc = udc;
	}

	
}
