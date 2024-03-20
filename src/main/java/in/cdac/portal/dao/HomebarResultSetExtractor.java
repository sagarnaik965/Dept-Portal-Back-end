package in.cdac.portal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

class HomebarResultSetExtractor implements ResultSetExtractor<Map<String, Map<String, Integer>>> {
//	private final static Logger logger = Logger.getLogger(HomebarResultSetExtractor.class);
	 private final  static Logger logger = LogManager.getLogger( HomebarResultSetExtractor.class );
	
	private Map<String, Map<String, Integer>> map1 = new LinkedHashMap<String, Map<String, Integer>>();

	public HomebarResultSetExtractor() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");

		for (int i = 1; i < 6; i++) {

			if (i == 1) {
				Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
				map2.put("AUTH", 0);
				map2.put("OTP", 0);
				map2.put("e-KYC", 0);
				map2.put("BFD", 0);
				map1.put(format1.format(now.getTime()), map2);
			}

			Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
			logger.info(format1.format(now.getTime()));
			map2.put("AUTH", 0);
			map2.put("OTP", 0);
			map2.put("e-KYC", 0);
			map2.put("BFD", 0);

			now.add(Calendar.MONTH, -1);
			map1.put(format1.format(now.getTime()), map2);
		}
	}

	public Map<String, Map<String, Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {

		Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
		map2.put("AUTH", 0);
		map2.put("OTP", 0);
		map2.put("e-KYC", 0);
		map2.put("BFD", 0);
		String cm = "";

		while (rs.next()) {

			if (cm.equals(""))
				cm = rs.getString("months");

			if (!cm.equals(rs.getString("months"))) {

				map1.put(cm, map2);
				map2 = new HashMap<String, Integer>();
				logger.info("after clearing values" + map2);
				cm = rs.getString("months");
				map2.put("AUTH", 0);
				map2.put("OTP", 0);
				map2.put("e-KYC", 0);

			}

			if (rs.getString("opr").equals("AUTH")) {
				map2.put("AUTH", rs.getInt("count"));
			} else if (rs.getString("opr").equals("e-KYC")) {
				map2.put("e-KYC", rs.getInt("count"));
			} else if (rs.getString("opr").equals("OTP")) {
				map2.put("OTP", rs.getInt("count"));
			} else if (rs.getString("opr").equals("BFD")) {
				map2.put("BFD", rs.getInt("count"));
			}
		}
		map1.put(cm, map2);
		return map1;
	}

}
