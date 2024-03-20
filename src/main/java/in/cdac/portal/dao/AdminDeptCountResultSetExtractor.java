package in.cdac.portal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import in.cdac.portal.modal.AdminDeptCount;

public class AdminDeptCountResultSetExtractor implements ResultSetExtractor<List<AdminDeptCount>> {

	Map<Map<String, String>, Map<String, Integer>> map0 = new HashMap<Map<String, String>, Map<String, Integer>>();

	public List<AdminDeptCount> extractData(ResultSet rs) throws SQLException, DataAccessException {

		String deptName = "";
		AdminDeptCount obj = new AdminDeptCount();

		List<AdminDeptCount> list = new ArrayList<AdminDeptCount>();

		while (rs.next()) {

			if (deptName.isEmpty()) {
				deptName = rs.getString("app_name");
				obj = new AdminDeptCount();
				obj.setAppName(deptName);
				//obj.setDeptId(rs.getInt("dept_id"));

			}

			if (!deptName.equals(rs.getString("app_name"))) {
				obj.setTotal(obj.getStruid() + obj.getGetuid() + obj.getGetrefnum() + obj.getActivate()+ obj.getDeactivate());
				list.add(obj);

				obj = new AdminDeptCount();
				deptName = rs.getString("dept_name");
				obj.setAppName(deptName);
				//obj.setDeptId(rs.getInt("dept_id"));

			}
			if (rs.getString("opr").equals("struid")) {
				obj.setStruid(rs.getInt("count"));
			} else if (rs.getString("opr").equals("getuid")) {
				obj.setGetuid(rs.getInt("count"));
			} else if (rs.getString("opr").equals("getrefnum")) {
				obj.setGetrefnum(rs.getInt("count"));
			} else if (rs.getString("opr").equals("activate")) {
				obj.setActivate(rs.getInt("count"));
			}else if (rs.getString("opr").equals("deactivate")) {
				obj.setActivate(rs.getInt("count"));
			}

		}


		obj.setTotal(obj.getStruid() + obj.getGetuid() + obj.getGetrefnum() + obj.getActivate()+ obj.getDeactivate());
		list.add(obj);

		return list;

	}

}
