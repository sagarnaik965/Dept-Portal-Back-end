package in.cdac.portal.entities;

public class SSOSession {

	String sessId;
	String servId;
	String sub;
	public SSOSession() {
		// TODO Auto-generated constructor stub
	}
	public String getSessId() {
		return sessId;
	}
	public void setSessId(String sessId) {
		this.sessId = sessId;
	}
	public String getServId() {
		return servId;
	}
	public void setServId(String servId) {
		this.servId = servId;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public SSOSession(String sessId, String servId, String sub) {
		super();
		this.sessId = sessId;
		this.servId = servId;
		this.sub = sub;
	}
	
}
