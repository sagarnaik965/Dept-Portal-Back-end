package in.cdac.portal.modal;

import java.io.Serializable;
import java.sql.Timestamp;



public class UserRole implements Serializable 
{
	private static final long serialVersionUID = 1L;

	UserRole()
	{
		setRoleId(1);
		setDeptId(4);
	}

	private int roleId;
	private int deptId;
	private String updateBy;
	private Timestamp updateTimestamp;
	private String username;

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", deptId=" + deptId + ", updateBy=" + updateBy + ", updateTimestamp="
				+ updateTimestamp + ", username=" + username + "]";
	}

}