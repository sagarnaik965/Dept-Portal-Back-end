package in.cdac.portal.modal;

public class BillingDetails {

	String key;
	String value;
	public BillingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BillingDetails(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	@Override
	public String toString() {
		return "BillingDetails [key=" + key + ", value=" + value + "]";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
