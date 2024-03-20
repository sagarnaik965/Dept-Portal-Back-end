package in.cdac.portal.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

//import org.apache.log4j.//;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReadDao {

	@Autowired
	@Qualifier("test_ds")
	DataSource ds1;

	@Autowired
	@Qualifier("preproduction_ds")
	DataSource ds2;

	@Autowired
	@Qualifier("production_ds")
	DataSource ds3;

	@Autowired
	PasswordEncoder encoder;


	private JdbcTemplate jdbcTemplate(String tenantName) {
		if (tenantName.equalsIgnoreCase("test")) {
			JdbcTemplate temp = new JdbcTemplate(ds1);
			temp.setDataSource(ds1);
			return temp;
		} else if (tenantName.equalsIgnoreCase("preproduction")) {
			JdbcTemplate temp = new JdbcTemplate(ds2);
			temp.setDataSource(ds2);
			return temp;
		} else if (tenantName.equals("production")) {
			JdbcTemplate temp = new JdbcTemplate(ds3);
			temp.setDataSource(ds3);
			return temp;
		}
//		logger.info("return DS NULL");
		return null;
	}

	public Map<String, ArrayList<String>> getroleActivity() {
		Map<String, ArrayList<String>> roleActivity = new HashMap<String, ArrayList<String>>();

		JdbcTemplate select = jdbcTemplate("test");

		return select.query("select role_id as rid from  roles;",
				new ResultSetExtractor<Map<String, ArrayList<String>>>() {

					@Override
					public Map<String, ArrayList<String>> extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						while (rs.next()) {

							String id = rs.getString("rid");

							ArrayList<String> alNew = new ArrayList<String>();
							roleActivity.put(id, alNew);
//						

						}
						return roleActivity;
					}
				});

	}

	public Map<String, String> getuserActivity() {
		Map<String, String> userActivity = new HashMap<String, String>();
		JdbcTemplate select = jdbcTemplate("test");
		return select.query(" select username as u from dept_details;", new ResultSetExtractor<Map<String, String>>() {

			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					while (rs.next()) {

						String id = rs.getString("u");

						userActivity.put(id, "");
						// userName.add(id);

					}
				}
				return userActivity;
			}
		});

	}

	public List<String> getuserName() {
		List<String> userName = new ArrayList<String>();
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(" select username as u from dept_details;", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {

					String id = rs.getString("u");

					// userActivity.put(id, "");
					userName.add(id);

				}
				return userName;
			}
		});

	}

	public Map<String, String> getuserRole() {
		Map<String, String> userRole = new HashMap<String, String>();
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(

				"select username as u,role_id as rid from user_roles;", new ResultSetExtractor<Map<String, String>>() {

					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {

							String rid = rs.getString("u");
							String aid = rs.getString("rid");

							userRole.put(rid, aid);
						}
						return userRole;
					}
				});

	}

	public Map<String, String> getactivityIdActivity() {
		Map<String, String> activityIdActivity = new HashMap<String, String>();
		JdbcTemplate select = jdbcTemplate("test");
		return select.query("select activity_id as aid,activity as a from activities;",
				new ResultSetExtractor<Map<String, String>>() {

					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {

							String aid = rs.getString("aid");
							String a = rs.getString("a");

							activityIdActivity.put(aid, a);
						}
						return activityIdActivity;
					}
				});

	}

	// Map<String, ArrayList<String>>
	public String fillroleActivity(Map<String, ArrayList<String>> roleActivity) {
		JdbcTemplate select = jdbcTemplate("test");
		return select.query(

				"select role_id as rid,activity_id as aid from role_activities;", new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {

						while (rs.next()) {

							rs.getString("rid");
							String aid = rs.getString("aid");

							for (Entry<String, ArrayList<String>> entry : roleActivity.entrySet()) {
								String a = rs.getString("rid");
								String b = entry.getKey();
								if (a.contains(b)) {
									entry.getValue().add(aid);
								}
							}
						}
						return "";
					}
				});

	}

	public List<String> getSchemaname() {
		List<String> ret = new ArrayList<String>();
		JdbcTemplate select = jdbcTemplate("test");
		return select.query("select schema_name from dept_details;", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					String schema_name = rs.getString("schema_name");
					ret.add(schema_name);

				}
				return ret;
			}
		});

	}

	public List<String[]> getWritingData(List<String> schemaname, int i, int daysBefore) {
		List<String[]> ret = new ArrayList<String[]>();
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
//			" select state_code as codes, state_name as statenames from m_state;",
				"SELECT a.app_code ,t.scheme_code,count(t.resp) as txn_count,t.opr,req_ts::date,t.err,t.resp ,t.is_exist_ref_num from dept_details  as d inner join application_details as a on d.dept_code=a.dept_code left join "
						+ schemaname.get(i) + ".vault_log as t on a.app_code=t.ac where a.dept_code='"
						+ schemaname.get(i) + "' AND req_ts::date=CURRENT_DATE-" + daysBefore
						+ "  group by a.app_code,t.scheme_code,req_ts::date,t.opr,t.err,t.resp,t.is_exist_ref_num;",
				new ResultSetExtractor<List<String[]>>() {

					@Override
					public List<String[]> extractData(ResultSet rs) throws SQLException, DataAccessException {

						while (rs.next()) {

							String app_code = rs.getString("app_code");
							String scheme_code = rs.getString("scheme_code");
							String txn_count = rs.getString("txn_count");
							String opr = rs.getString("opr");
							String req_ts = rs.getString("req_ts");
							String err = rs.getString("err");
							String resp = rs.getString("resp");
							String is_exist_ref_num = rs.getString("is_exist_ref_num");
							String[] data = { app_code, scheme_code, txn_count, opr, req_ts, err, is_exist_ref_num,
									resp };
							if (app_code != null && scheme_code != null && txn_count != null && opr != null
									&& req_ts != null && resp != null) {
								// writer.writeNext(data);
								ret.add(data);
							}

						}
						return ret;
					}
				});

	}

	public ArrayList<String> transData(int daysBefore) {
		ArrayList<String> ret = new ArrayList<String>();
		JdbcTemplate select = jdbcTemplate("test");
		return select.query("select * from trans_stats  where date = CURRENT_DATE-" + daysBefore + ";",
				new ResultSetExtractor<ArrayList<String>>() {

					@Override
					public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {

							String ac = rs.getString("ac");
							String scheme_code = rs.getString("scheme_code");
							String txn_count = rs.getString("txn_count");
							String opr = rs.getString("opr");
							String date = rs.getString("date");
							String err = rs.getString("err");
							String status = rs.getString("status");
							String is_exist_ref_num = rs.getString("is_exist_ref_num");
							String s1 = ac + " " + scheme_code + " " + txn_count + " " + opr + " " + date + " " + err
									+ " " + is_exist_ref_num + " " + status;
							ret.add(s1);

						}
						return ret;
					}
				});

	}

	public String updaterefNumIsNull(int txns_count_sum, String[] arr) {

		Date date = Date.valueOf(arr[4].replaceAll("^\"|\"$", ""));
		JdbcTemplate update = jdbcTemplate("test");
		try {

			update.update(
					"update trans_stats set txn_count = " + txns_count_sum
							+ " where ac=? and opr=? and date=? and err=? and status=? and is_exist_ref_num is null;",
					new Object[] { arr[0].replaceAll("^\"|\"$", ""), arr[3].replaceAll("^\"|\"$", ""), date,
							arr[5].replaceAll("^\"|\"$", ""), arr[7].replaceAll("^\"|\"$", "")

					});

		} catch (Exception e) {

			return "successfully opr updated";

		}
		return "successfully opr updated";
	}

	public String updaterefNumIsNotNull(int txns_count_sum, String[] arr) {
//		 String url = "" +
//		  opr.getIs_struid().getClass().getField(name);
		Date date = Date.valueOf(arr[4].replaceAll("^\"|\"$", ""));
		JdbcTemplate update = jdbcTemplate("test");
		Boolean val = true;
		if (arr[6].replaceAll("^\"|\"$", "").contentEquals("t")) {
			val = Boolean.valueOf("true");
		}
		if (arr[6].replaceAll("^\"|\"$", "").contentEquals("f")) {
			val = Boolean.valueOf("false");
		}
		try {

			update.update(
					"update trans_stats set txn_count = " + txns_count_sum
							+ " where ac=? and opr=? and date=? and err=? and status=? and is_exist_ref_num=?;",
					new Object[] { arr[0].replaceAll("^\"|\"$", ""), arr[3].replaceAll("^\"|\"$", ""), date,
							arr[5].replaceAll("^\"|\"$", ""), arr[7].replaceAll("^\"|\"$", ""), val

					});

		} catch (Exception e) {

			return "successfully opr updated";

		}
		return "successfully opr updated";
	}

	public String insertWithBool(String newDataArray[]) {
		// JdbcTemplate update = jdbcTemplate("test");
		Date date = Date.valueOf(newDataArray[4].replaceAll("^\"|\"$", ""));
		JdbcTemplate update = jdbcTemplate("test");
		Boolean val = true;
		if (newDataArray[6].replaceAll("^\"|\"$", "").contentEquals("t")) {
			val = Boolean.valueOf("true");
		}
		if (newDataArray[6].replaceAll("^\"|\"$", "").contentEquals("f")) {
			val = Boolean.valueOf("false");
		}

		try {
			update.update(
					"insert into trans_stats  ( ac ,scheme_code ,opr , date , err ,txn_count,is_exist_ref_num, status) values(?,?,?,?,?,?,?,?);",
					new Object[] { newDataArray[0].replaceAll("^\"|\"$", ""), newDataArray[1].replaceAll("^\"|\"$", ""),
							newDataArray[3].replaceAll("^\"|\"$", ""), date, newDataArray[5].replaceAll("^\"|\"$", ""),
							Integer.valueOf(newDataArray[2].replaceAll("^\"|\"$", "")), val,
							newDataArray[7].replaceAll("^\"|\"$", "")

					});
		}

		catch (Exception e) {
			throw new RuntimeException();
		}
		return "successfully app created";
	}

	public String insert(String newDataArray[]) {
		// JdbcTemplate update = jdbcTemplate("test");
		Date date = Date.valueOf(newDataArray[4].replaceAll("^\"|\"$", ""));
		JdbcTemplate update = jdbcTemplate("test");
		if (newDataArray[6].replaceAll("^\"|\"$", "").contentEquals("t")) {
			Boolean.valueOf("true");
		}
		if (newDataArray[6].replaceAll("^\"|\"$", "").contentEquals("f")) {
			Boolean.valueOf("false");
		}

		try {
			update.update(
					"insert into trans_stats  ( ac ,scheme_code ,opr , date , err ,txn_count, status) values(?,?,?,?,?,?,?);",
					new Object[] { newDataArray[0].replaceAll("^\"|\"$", ""), newDataArray[1].replaceAll("^\"|\"$", ""),
							newDataArray[3].replaceAll("^\"|\"$", ""), date, newDataArray[5].replaceAll("^\"|\"$", ""),
							Integer.valueOf(newDataArray[2].replaceAll("^\"|\"$", "")),
							newDataArray[7].replaceAll("^\"|\"$", "")

					});
		}

		catch (Exception e) {
			throw new RuntimeException();
		}
		return "successfully app created";
	}

	public ArrayList<String> dataForCSV() {
		ArrayList<String> ret = new ArrayList<String>();

		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
				"SELECT d.dept_name ,app_name,app_code,t.date,t.scheme_code,t.opr,t.status, sum(t.txn_count) as txn_count, t.err ,d.username from dept_details  as d inner join application_details as a on d.dept_code=a.dept_code left join trans_stats as t  on a.app_code=t.ac group by d.dept_name,a.app_name,t.scheme_code,t.opr,t.status,app_code,t.date, t.err ,d.username;",
				new ResultSetExtractor<ArrayList<String>>() {

					@Override
					public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

						while (rs.next()) {

							String dept = rs.getString("dept_name");
							String appName = rs.getString("app_name");
							String appCode = rs.getString("app_code");
							String date = rs.getString("date");
							String schemeCode = rs.getString("scheme_code");
							String opr = rs.getString("opr");
							String status = rs.getString("status");

							String txn_count = rs.getString("txn_count");
							String err = rs.getString("err");
							String userName = rs.getString("username");
							String data = dept + "," + appName + "," + appCode + "," + date + "," + schemeCode + ","
									+ opr + "," + status + "," + txn_count + "," + err + "," + userName;
							ret.add(data);

						}
						return ret;
					}
				});

	}

	public String getDept(String apkCode) {

		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
//				" select state_code as codes, state_name as statenames from m_state;",
				"select  dept_code as dc from  application_details where  app_code='" + apkCode + "';",
				new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
//						Map<String, String> map = new HashMap<String, String>();
//						List<AllowedOpr> opr = new ArrayList<>();
						// AllowedOpr opra = new String();
						String id = "";
						while (rs.next()) {

							id = rs.getString("dc");
							new ArrayList<String>();

						}
						return id;

					}
				});

	}

	public ArrayList<String> getAllApkCode(String deptCode) {

		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
//				" select state_code as codes, state_name as statenames from m_state;",
				"select  app_code as ap from  application_details where   dept_code='" + deptCode + "';",
				new ResultSetExtractor<ArrayList<String>>() {

					@Override
					public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
//						Map<String, String> map = new HashMap<String, String>();
//						List<AllowedOpr> opr = new ArrayList<>();
						// AllowedOpr opra = new String();
						ArrayList<String> allApk = new ArrayList<String>();
						while (rs.next()) {

							String apk = rs.getString("ap");
							allApk.add(apk);

						}
						return allApk;
					}
				});
	}

	public Map<String, String> getNameOfDept() {
		Map<String, String> dept_code_dept_name = new HashMap<String, String>();
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
//				" select state_code as codes, state_name as statenames from m_state;",
				" select dept_code,dept_name from dept_details ;", new ResultSetExtractor<Map<String, String>>() {

					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
						new ArrayList<String>();
						while (rs.next()) {

							String dept_code = rs.getString("dept_code");
							String dept_name = rs.getString("dept_name");
							dept_code_dept_name.put(dept_code, dept_name);

						}
						return dept_code_dept_name;
					}
				});
	}

	public Map<String, String> getNameOfApk() {
		Map<String, String> apkCode_apk_name = new HashMap<String, String>();
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(" select app_code,app_name from application_details;",
				new ResultSetExtractor<Map<String, String>>() {

					@Override
					public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {

						new ArrayList<String>();
						while (rs.next()) {
							String apk_code = rs.getString("app_code");
							String app_name = rs.getString("app_name");
							apkCode_apk_name.put(apk_code, app_name);
						}
						return apkCode_apk_name;
					}
				});
	}

}
