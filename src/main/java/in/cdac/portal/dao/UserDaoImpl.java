package in.cdac.portal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.RestTemplate;

import in.cdac.dbswitch.CustomeUserDetails;
import in.cdac.portal.entities.User;
import in.cdac.portal.modal.AllowedOpr;
import in.cdac.portal.modal.AppDetail;
import in.cdac.portal.modal.AppList;
import in.cdac.portal.modal.AppLk;
import in.cdac.portal.modal.DeptDetails;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.UserStatus;
import in.cdac.portal.modal.WrapperClass;

@Service
public class UserDaoImpl implements UserDao {

//	private final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	 private final  static Logger logger = LogManager.getLogger( UserDaoImpl.class );

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

	@Autowired
	Environment env;

	private JdbcTemplate jdbcTemplate(String tenantName) {

		if (tenantName.equalsIgnoreCase("test")) {
			JdbcTemplate temp = new JdbcTemplate(ds1);

			return temp;
		} else if (tenantName.equalsIgnoreCase("preproduction")) {
			JdbcTemplate temp = new JdbcTemplate(ds2);

			return temp;
		} else if (tenantName.equals("production")) {
			JdbcTemplate temp = new JdbcTemplate(ds3);

			return temp;
		}
		logger.info("return DS NULL");
		return null;
	}

	@Override
	public int getHomePageSuccessCountR(String userName) {
		int count = 0;
		ArrayList<String> resAl = new ArrayList<String>();
		try {
		resAl = getDataFromignite();
		for (String responseData : resAl) {
			String[] responseDataArray = responseData.split(",");
			if (responseDataArray.length > 10 && responseDataArray[9].contentEquals(userName)
					&& responseDataArray[6].contentEquals("y")) {
				count += Integer.parseInt(responseDataArray[7]);
			}
		}
	
		return count;
		} catch (Exception e) {
			logger.info("Data for Success count not found " + e.getMessage());
			return count;
		}
	}

	@Override
	public int getTotalErrorCountR(String userName) {
		int count = 0;
		try {
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = getDataFromignite();
			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10 && !responseDataArray[7].toString().isEmpty()
							&& responseDataArray[9].contentEquals(userName)
							&& responseDataArray[6].contentEquals("n")) {
						count += Integer.parseInt(responseDataArray[7]);
					}
				}
			}
			return count;
		} catch (Exception e) {
			logger.info("Data for unsuccessful count not found " + e.getMessage());
		}
		return count;
	}
	
	@Override
	public int getTotalAcCountDeptWiseR(String userName) {
		ArrayList<String> resAl = new ArrayList<String>();
		HashSet<String> applications = new HashSet<>();
		try {
		resAl = getDataFromignite();
		for (String responseData : resAl) {
			String[] responseDataArray = responseData.split(",");
			if (!responseDataArray[1].toString().isEmpty() && responseDataArray[1].toString() != ""
					&& !responseDataArray[1].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")) {
				if (responseDataArray[9].contentEquals(userName)) {
					applications.add(responseDataArray[1]);
				}
			}
		}
		return applications.size();
		} catch (Exception e) {
			logger.info("Data for application count  not found " + e.getMessage());
			return applications.size();
		}
	}

	@Override
	public Map<String, Integer> getMonthlyTotalTransR(String userName) {
		logger.info("getMonthlyTotalTrans userName::" + userName);
		JdbcTemplate select = jdbcTemplate("test");
		try {
			return select.query(
					"select to_char(date,'month') as month,sum(txn_count) as count from public.trans_stats   group by 1,1 order by month desc limit 12  ",
					new ResultSetExtractor<Map<String, Integer>>() {
						@Override
						public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
							Map<String, Integer> map = new LinkedHashMap<String, Integer>();
							String month = null;
							int count = 0;
							while (rs.next()) {
								month = rs.getString("month");
								count = rs.getInt("count");
								map.put(month, count);
							}
							return map;
						}
					});
		} catch (DataAccessException ex) {
			logger.info(ex.getMessage());
			return null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	

	@Override
	public Map<String, Integer> DonutChart(String userName) {
		Map<String, Integer> oprmap = new HashMap<String, Integer>();
		oprmap.put("struid", 0);
		oprmap.put("getuid", 0);
		oprmap.put("getrefnum", 0);
		oprmap.put("activate", 0);
		oprmap.put("deactivate", 0);

		try {
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = getDataFromignite();

			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10) {
						if (responseDataArray[9].toString().contentEquals(userName)) {
							if (oprmap.containsKey(responseDataArray[5])) {
								oprmap.put(responseDataArray[5],
										Integer.parseInt(responseDataArray[7]) + oprmap.get(responseDataArray[5]));
							} else {
								oprmap.put(responseDataArray[5], 0);
							}
						}
					}
				}
			}
			return oprmap;
		} catch (Exception e) {
			logger.info(e.getMessage());

		}
		return null;
	}

	@Override
	public Map<String, Integer> DonutchartType(String userName, String type) {
		Map<String, Integer> oprmap = new HashMap<String, Integer>();
		oprmap.put("struid", 0);
		oprmap.put("getuid", 0);
		oprmap.put("getrefnum", 0);
		oprmap.put("activate", 0);
		oprmap.put("deactivate", 0);

		try {
			ArrayList<String> resAl = new ArrayList<String>();
			resAl = getDataFromignite();

			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10) {
						if (responseDataArray[9].toString().contentEquals(userName)
								&& responseDataArray[6].contentEquals(type.trim())) {
							if (oprmap.containsKey(responseDataArray[5])) {
								oprmap.put(responseDataArray[5],
										Integer.parseInt(responseDataArray[7]) + oprmap.get(responseDataArray[5]));
							} else {
								oprmap.put(responseDataArray[5], 0);
							}
						}
					}
				}
			}
			return oprmap;
		} catch (Exception e) {
			logger.info(e.getMessage());

		}
		return null;
	}

	@Override
	public List<UserStatus> getAppcodeR(String Username) {
		List<UserStatus> applicationDetails = new ArrayList<>();
		ArrayList<String> resAl = new ArrayList<String>();
		HashMap<String, String> mapApplicationDetail = new HashMap<>();
		resAl = getDataFromignite();
		for (String responseData : resAl) {
			if (!responseData.isEmpty()) {
				String[] responseDataArray = responseData.split(",");
				if (responseDataArray.length > 10) {
					if (responseDataArray[9].toString().contentEquals(Username) && !responseDataArray[2].isEmpty()
							&& !responseDataArray[1].isEmpty() && !responseDataArray[1].contentEquals("null")
							&& !responseDataArray[2].contentEquals("null")) {
						mapApplicationDetail.put(responseDataArray[2], responseDataArray[1]);
					}
				}
			}
		}

		for (Map.Entry<String, String> entry : mapApplicationDetail.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			UserStatus userstatus = new UserStatus();
			userstatus.setAuaCode(key);
			userstatus.setAppName(val);
			applicationDetails.add(userstatus);
		}
		return applicationDetails;

	}

	@Override
	public Map<String, Integer> acwiseTotaltransIgnite(String[] acCodeData) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<String> resAl = new ArrayList<String>();
		String appCode = acCodeData[0];
		map.put("struid", 0);
		map.put("getuid", 0);
		map.put("getrefnum", 0);
		map.put("activate", 0);
		map.put("deactivate", 0);
		resAl = getDataFromignite();
		try {			
		
		if (acCodeData[1].contentEquals("Total")) {
			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10) {
						if (responseDataArray[2].trim().contentEquals(appCode)) {
							if (!map.containsKey(responseDataArray[5]) && !responseDataArray[5].trim().isEmpty()
									&& !responseDataArray[7].trim().isEmpty()
									&& !responseDataArray[7].trim().contentEquals("null")) {
								map.put(responseDataArray[5], Integer.parseInt(responseDataArray[7].trim()));
							} else {
								map.put(responseDataArray[5],
										map.get(responseDataArray[5]) + Integer.parseInt(responseDataArray[7].trim()));
							}
						}

					}
				}
			}

		} else if (acCodeData[1].contentEquals("Yes")) {
			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10) {
						if (responseDataArray[2].trim().contentEquals(appCode)
								&& responseDataArray[6].trim().contentEquals("y")) {
							if (!map.containsKey(responseDataArray[5]) && !responseDataArray[5].trim().isEmpty()
									&& !responseDataArray[7].trim().isEmpty()
									&& !responseDataArray[7].trim().contentEquals("null")) {
								map.put(responseDataArray[5], Integer.parseInt(responseDataArray[7].trim()));
							} else {
								map.put(responseDataArray[5],
										map.get(responseDataArray[5]) + Integer.parseInt(responseDataArray[7].trim()));
							}
						}

					}
				}
			}

		} else if (acCodeData[1].contentEquals("No")) {
			for (String responseData : resAl) {
				if (!responseData.isEmpty()) {
					String[] responseDataArray = responseData.split(",");
					if (responseDataArray.length > 10) {
						if (responseDataArray[2].trim().contentEquals(appCode)
								&& responseDataArray[6].trim().contentEquals("n")) {
							if (!map.containsKey(responseDataArray[5]) && !responseDataArray[5].trim().isEmpty()
									&& !responseDataArray[7].trim().isEmpty()
									&& !responseDataArray[7].trim().contentEquals("null")) {
								map.put(responseDataArray[5], Integer.parseInt(responseDataArray[7].trim()));
							} else {
								map.put(responseDataArray[5],
										map.get(responseDataArray[5]) + Integer.parseInt(responseDataArray[7].trim()));
							}
						}

					}
				}
			}

		}
		return map;

		} catch (Exception e) {
			logger.info("Exception Data for opr wise chart not found " + e.getMessage());
			return map;
			
		}
	}

	@Override
	public Map<String, DeptDetails> getrecordR(String deptcode) {
		JdbcTemplate select = jdbcTemplate("test");
		return select.query(
				"  select * from(select d.dept_name as name, d.dept_code as deptcode ,a.app_name as appname from dept_details as d, application_details as a where d.dept_code = a.dept_code) as new  where new.deptcode=?;",
				new Object[] { deptcode }, new ResultSetExtractor<Map<String, DeptDetails>>() {
					@Override
					public Map<String, DeptDetails> extractData(ResultSet rs) throws SQLException, DataAccessException {
						Map<String, DeptDetails> map = new HashMap<String, DeptDetails>();
						while (rs.next()) {
							DeptDetails dept = new DeptDetails();
							dept.setDept_name(rs.getString("name"));
							dept.setApp_name(rs.getString("appname"));
							map.put(rs.getString("appname"), dept);
						}
						return map;
					}
				});
	}

	@Override
	public String getDeptcodeFromUsernameforreport(String username) {

		try {

			JdbcTemplate select = jdbcTemplate("test");
			String url = "select dept_name,  dept_code from dept_details where username =" + "'" + username + "' ;";
			return select.query(url, new ResultSetExtractor<String>() {
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					String deptcode = "";
					while (rs.next()) {
						deptcode = rs.getString("dept_code") + "," + rs.getString("dept_name");
					}
					return deptcode;
				}
			});

		} catch (Exception e) {
			logger.info(e.getMessage());
			return "";
		}
	}

	@Override
	public DeptList getDeptcodeFromUsername(String username) {
		 
		JdbcTemplate select = jdbcTemplate("test");

		String url = "select dept_code , dept_name from dept_details where username =" + "'" + username + "' ;";

		return select.query(url, new ResultSetExtractor<DeptList>() {
			@Override
			public DeptList extractData(ResultSet rs) throws SQLException, DataAccessException {
				DeptList dept = new DeptList();

				while (rs.next()) {
					dept.setDept_code(rs.getString("dept_code"));
					dept.setDept_name(rs.getString("dept_name"));

				}
				return dept;
			}
		});
	}

	@Override
	public List<AppList> getAppListR(String deptcode) {
		 

		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
				"select app_name,app_code from application_details where dept_Code='" + deptcode + "'" + ";",
				new ResultSetExtractor<List<AppList>>() {

					@Override
					public List<AppList> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<AppList> applist = new ArrayList<>();
						while (rs.next()) {
							AppList appl = new AppList();
							appl.setAppname(rs.getString("app_name"));
							appl.setAppcode(rs.getString("app_code"));

							applist.add(appl);
						}
						return applist;
					}
				});

	}

	@Override
	public AppDetail getAppDetailR(String appcode) {


		JdbcTemplate select = jdbcTemplate("test");

		return select.query(
				"select app_name,email,description from application_details where app_code='" + appcode + "'" + ";",
				new ResultSetExtractor<AppDetail>() {

					@Override
					public AppDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
						AppDetail appd = new AppDetail();
						while (rs.next()) {

							appd.setAppname(rs.getString("app_name"));
							appd.setEmail(rs.getString("email"));
							appd.setDesc(rs.getString("description"));

						}
						return appd;
					}
				});

	}

	@Override
	public List<AppLk> getAppLkR(String appcode) {

		JdbcTemplate select = jdbcTemplate("test");

		return select.query("select lk,lk_expiry_date::date,app_is_active from application_lk where app_code='"
				+ appcode + "' order by lk_expiry_date desc" + ";", new ResultSetExtractor<List<AppLk>>() {

					@Override
					public List<AppLk> extractData(ResultSet rs) throws SQLException, DataAccessException {

						List<AppLk> lklist = new ArrayList<>();
						Date lkexpiryvar = null;
						String strDate;

						while (rs.next()) {
							AppLk appl = new AppLk();
							String masklk = String.valueOf(rs.getString("lk"));
							char[] ch = new char[masklk.length()];
							for (int i = 0; i < 36; i++) {
								ch[i] = masklk.charAt(i);
							}

							for (int i = 0; i < 28; i++) {
								ch[i] = '*';
							}

							String finallk = String.valueOf(ch);

							appl.setLk(finallk);

							appl.setLkexpiry(rs.getDate("lk_expiry_date"));

							appl.setApp_is_active(rs.getBoolean("app_is_active"));

							lklist.add(appl);

							lkexpiryvar = appl.getLkexpiry();

							SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("MM/dd/yyyy");
							String date_to_string = dateformatyyyyMMdd.format(lkexpiryvar);
							String pattern12 = "MM/dd/yyyy";
							String dateInString = new SimpleDateFormat(pattern12).format(new Date());

							String dateStart = dateInString;
							String dateStop = date_to_string;

							SimpleDateFormat format12 = new SimpleDateFormat("MM/dd/yyyy");

							Date d1 = null;
							Date d2 = null;

							try {
								d1 = format12.parse(dateStart);
								d2 = format12.parse(dateStop);

								long diff = d2.getTime() - d1.getTime();
								long diffDays = diff / (24 * 60 * 60 * 1000);
							

								appl.setDiffexpirydateforalert(diffDays);

							} catch (Exception e) {
								logger.info(e.getMessage());
							}

						}

						return lklist;
					}
				});

	}

	@Override
	public Long applkexpiryalert(String appcode) {
		 
		JdbcTemplate select = jdbcTemplate("test");

		return select.query("select lk,lk_expiry_date::date,app_is_active from application_lk where app_code='"
				+ appcode + "'" + ";", new ResultSetExtractor<Long>() {

					@Override
					public Long extractData(ResultSet rs) throws SQLException, DataAccessException {

						List<AppLk> lklist = new ArrayList<>();
						Date lkexpiryvar = null;
						String strDate;
						long diffDaysExpired = 0;

						while (rs.next()) {
							AppLk appl = new AppLk();
							String masklk = String.valueOf(rs.getString("lk"));
							char[] ch = new char[masklk.length()];
							for (int i = 0; i < 36; i++) {
								ch[i] = masklk.charAt(i);
							}

							for (int i = 0; i < 28; i++) {
								ch[i] = '*';
							}

							String finallk = String.valueOf(ch);

							appl.setLk(finallk);

							appl.setLkexpiry(rs.getDate("lk_expiry_date"));

							appl.setApp_is_active(rs.getBoolean("app_is_active"));

							lklist.add(appl);

							lkexpiryvar = appl.getLkexpiry();

							SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("MM/dd/yyyy");
							String date_to_string = dateformatyyyyMMdd.format(lkexpiryvar);
							String pattern12 = "MM/dd/yyyy";
							String dateInString = new SimpleDateFormat(pattern12).format(new Date());

							String dateStart = dateInString;
							String dateStop = date_to_string;

							SimpleDateFormat format12 = new SimpleDateFormat("MM/dd/yyyy");

							Date d1 = null;
							Date d2 = null;

							try {
								d1 = format12.parse(dateStart);
								d2 = format12.parse(dateStop);

								long diff = d2.getTime() - d1.getTime();
								long diffDays = diff / (24 * 60 * 60 * 1000);
								if (diffDays <= 30) {
									diffDaysExpired = diffDays;
								}

							
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						}

						return diffDaysExpired;
					}
				});

	}

	@Override
	public AllowedOpr getOprR(String appcode) {

		JdbcTemplate select = jdbcTemplate("test");

		return select.query(

				"select is_struid,is_getrefnum,is_getuid,is_activate,is_deactivate,is_dupcheck,app_code from application_lk where app_code='"
						+ appcode + "'" + ";",
				new ResultSetExtractor<AllowedOpr>() {

					@Override
					public AllowedOpr extractData(ResultSet rs) throws SQLException, DataAccessException {

						AllowedOpr opra = new AllowedOpr();
						while (rs.next()) {

							opra.setis_Struid(rs.getBoolean("is_struid"));
							opra.setis_Getrefnum(rs.getBoolean("is_getrefnum"));
							opra.setis_Getuid(rs.getBoolean("is_getuid"));
							opra.setis_Activate(rs.getBoolean("is_activate"));
							opra.setis_Deactivate(rs.getBoolean("is_deactivate"));
							opra.setIs_dupcheck(rs.getBoolean("is_dupcheck"));
							opra.setApplicode(rs.getString("app_code"));

						}
						return opra;
					}
				});

	}

	@Override
	public String getCurrentEmailId(String userName) {
		JdbcTemplate select = jdbcTemplate(getTenantName());
		String email = null;
		try {
			email = select.queryForObject("SELECT email FROM public.dept_details where username= ?",
					new Object[] { userName }, String.class);

		} catch (DataAccessException ex) {
			logger.info(ex);
			return null;

		} catch (Exception e) {
			logger.info(e);
			return null;
		}

		return email;
	}

	@Override
	public List<DeptList> getDeptListR() {
		JdbcTemplate select = jdbcTemplate("test");

		return select.query(

				"select dept_name,dept_code,username from dept_details", new ResultSetExtractor<List<DeptList>>() {
					@Override
					public List<DeptList> extractData(ResultSet rs) throws SQLException, DataAccessException {

						List<DeptList> deptlist = new ArrayList<>();
						while (rs.next()) {
							DeptList dept = new DeptList();
							dept.setDept_name(rs.getString("dept_name"));
							dept.setDept_code(rs.getString("dept_code"));
							dept.setUsername(rs.getString("username"));
							deptlist.add(dept);

						}
						return deptlist;
					}
				});
	}

	@Override
	public String getRole(String principal) {

		try {

			JdbcTemplate select = jdbcTemplate("test");

			String url = "select role_id from user_roles where username = " + "'" + principal + "'" + ";";
			return select.query(url, new ResultSetExtractor<String>() {
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {

					String r = "";
					while (rs.next()) {
						r = rs.getString("role_id");

					}
					return r;
				}
			});

		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> getActivityListByUsername(String username, String tenant) {
		JdbcTemplate select = jdbcTemplate(tenant);
		logger.info("username " + username);

		return select.query(
				"select a.activity from activities a join role_activities ra on a.activity_id = ra.activity_id join  user_roles ur on ra.role_id = ur.role_id  where ur.username = ?; ",
				new Object[] { username }, new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getString(1);
					}
				});
	}

	public List<String> getUsernameList(long dept_id) {
		JdbcTemplate select = jdbcTemplate(getTenantName());
		logger.info("dept_id " + dept_id);
		return select.query("select username from public.users  where dept_id = ? ", new Object[] { dept_id },
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getString(1);
					}
				});
	}

	private String getTenantName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String tenantName = null;
		if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
			
			CustomeUserDetails userDetails = (CustomeUserDetails) auth.getPrincipal();
			tenantName = userDetails.getTenant();
		}
		return tenantName;
	}

	@Override
	public List<User> getUser(String userName) {
		JdbcTemplate select = jdbcTemplate(getTenantName());
		logger.info("getUser UserName::" + userName);

		return select.query(
				"select u.username, u.passwd,u.dept_is_active from public.dept_details as u  where  u.username = ?",
				new Object[] { userName }, new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int arg1) throws SQLException {
						User user = new User();
						user.setUsername(rs.getString(1));
						user.setPasswd(rs.getString(2));
						user.setActive(rs.getBoolean(3));
						return user;
					}
				});
	}

	@Override
	public List<User> loadUserByUsernameAndTenantname(String username, String tenantName) {
		logger.info(
				"_________________________________________________________________________________________________");

		logger.info("UserName ::" + username + " ::tenant name::" + tenantName);
		JdbcTemplate jdbcTemp = jdbcTemplate(tenantName);

		String q = "select u.username, u.passwd,u.dept_is_active ,m.para_desc from public.dept_details as u,m_config_para m where m.para_value=? and u.username = ?";
		List<User> user = jdbcTemp.query(q, new Object[] { tenantName, username }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setPasswd(rs.getString(2));

				user.setActive(rs.getBoolean(3));
				user.setTenantname(rs.getString(4));
				return user;
			}
		});
		return user;
	}

	@Override
	public Map<String, Long> getDataForChart(String Username) {
		 
		try {
			JdbcTemplate select = jdbcTemplate("test");
			String url = "select date,sum(txn_count) as cnt from trans_stats where ac in (select app_code from application_details where dept_code in (select dept_code from dept_details where username = '"
					+ Username + "')) and date >= (now()-interval '7 days') group by date;";
			return select.query(url, new ResultSetExtractor<Map<String, Long>>() {
				@Override
				public Map<String, Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
					Map<String, Long> chartdata = new HashMap<>();
					while (rs.next()) {
						if (rs.getString("cnt") != null)
							chartdata.put(rs.getString("date"), Long.parseLong(rs.getString("cnt")));
					}
					return chartdata;
				}
			});

		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@Override
	public String matachPass(String username) {

		try {

			JdbcTemplate select = jdbcTemplate("test");
	
			String url = "select passwd from dept_details where username='" + username + "' ;";
			return select.query(url, new ResultSetExtractor<String>() {
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					String pass = "";
					while (rs.next()) {

						pass = rs.getString("passwd");
				
					}
					return pass;
				}
			});

		} catch (Exception e) {
			logger.info(e.getMessage());
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getDataFromignite() {
		ArrayList<String> resAl = new ArrayList<String>();
		try {
			String url = env.getProperty("apiurl");
			RestTemplate rt = new RestTemplate();
//			byte[] b = rt.getForObject(url, byte[].class);
//			resAl = (ArrayList<String>) SerializationUtils.deserialize(b);
			WrapperClass b  = rt.getForObject(url, WrapperClass.class);
			resAl = (ArrayList<String>) SerializationUtils.deserialize(b.getData());
			return resAl;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return resAl;
	}

	@Override
	public List<AppList> getAppListIgnite(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
