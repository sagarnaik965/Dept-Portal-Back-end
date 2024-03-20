package in.cdac.portal.modal;


public class DeptList {
	
	public String dept_name;
	public String dept_code;
	public String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public DeptList(String dept_name, String dept_code, String username) {
		super();
		this.dept_name = dept_name;
		this.dept_code = dept_code;
		this.username = username;
	}

	public DeptList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DeptList [dept_name=" + dept_name + ", dept_code=" + dept_code + ", username=" + username + "]";
	}

	

	

	

	
	
	

}
