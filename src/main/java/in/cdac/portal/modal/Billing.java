package in.cdac.portal.modal;


public class Billing {
	private String application_name;
	private int count;
	private double amount;
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Billing [application_name=" + application_name + ", count=" + count + ", amount=" + amount + "]";
	}
	public Billing(String application_name, int count, double amount) {
		super();
		this.application_name = application_name;
		this.count = count;
		this.amount = amount;
	}
	public Billing() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
