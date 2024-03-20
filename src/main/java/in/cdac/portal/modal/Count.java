package in.cdac.portal.modal;

import java.util.Arrays;

public class Count {
	private int y;
	private String label;
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "Count [y=" + y + ", label=" + label + "]";
	}
	public Count(int y, String label) {
		super();
		this.y = y;
		this.label = label;
	}
	public Count() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
