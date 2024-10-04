package in.cdac.portal.services;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.cdac.portal.dao.UserDao;
import in.cdac.portal.modal.ChartData;
import in.cdac.portal.modal.Count;
import in.cdac.portal.modal.Count1;
import in.cdac.portal.modal.DeptDetails;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.UserStatus;
import in.cdac.portal.modal.WrapperClass;

@Service
public class DashboardServiceImpl implements DashboardService {
//	private final static Logger logger = Logger.getLogger(DashboardServiceImpl.class);
	 private final  static Logger logger = LogManager.getLogger( DashboardServiceImpl.class );

	@Autowired
	UserDao userDao;
	
	@Autowired
	Environment env;


	@Override
	public int getHomePageSuccessCountR(String username) {
		// TODO Auto-generated method stub
		return userDao.getHomePageSuccessCountR(username);
	}

	@Override
	public int getTotalErrorCountR(String username) {
		// TODO Auto-generated method stub
		return userDao.getTotalErrorCountR(username);
	}

	@Override
	public Map<String, Integer> getMonthlyTotalTransR(String userName) {
		return userDao.getMonthlyTotalTransR(userName);
	}

	@Override
	public int getTotalAcCountDeptWiseR(String userName) {
		// TODO Auto-generated method stub
		return userDao.getTotalAcCountDeptWiseR(userName);
	}

	@Override
	public List<Count1> DonutChart(String userName) {		
		Map<String, Integer> map = null;
		List<Count1> l1 = new ArrayList<>();
		try {
			map = userDao.DonutChart(userName);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				Count1 c1 = new Count1();
				c1.setY(entry.getValue());
				c1.setName(entry.getKey());
				l1.add(c1);
			}
			return l1;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return l1;
		}
	}

	@Override
	public List<Count1> DonutchartType(String[] chartData) {	
		String userName = chartData[0];
		String type = chartData[1];
		Map<String, Integer> map = null;
		List<Count1> l1 = new ArrayList<>();
		try {
			map = userDao.DonutchartType(userName,type);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				Count1 c1 = new Count1();
				c1.setY(entry.getValue());
				c1.setName(entry.getKey());
				l1.add(c1);
			}
			return l1;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return l1;
		}
	}

	
	@Override
	public List<UserStatus> getAppcodeR(String username) {
		List<UserStatus> us = userDao.getAppcodeR(username);
		return us;
	}

	@Override
	public List<Count> acwiseTotaltransR(String[] acCode) {
		List<Count> l1 = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
//		map = userDao.acwiseTotaltransR(acCode);
		map = userDao.acwiseTotaltransIgnite(acCode);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Count c1 = new Count();
			c1.setY(entry.getValue());
			c1.setLabel(entry.getKey());
			l1.add(c1);
		}
		return l1;
	}
	@Override
	public Map<String, DeptDetails> getDeptServiceDetailsR(String deptcode) {
		// TODO Auto-generated method stub
		return userDao.getrecordR(deptcode);
	}

	@Override
	public List<DeptList> getDeptListR() {
		// TODO Auto-generated method stub
		return userDao.getDeptListR();
	}

	@Override
	public ChartData getDataForChart(String Username) {
		ChartData chartData = new ChartData();
		String label[] = new String[7];
		ArrayList<String> resAl = getDataFromignite();
		try {			
			long[] arr = new long[7];
			chartData.setData(arr);
			chartData.setLabels(label);
			
			LocalDate localdate = LocalDate.now();
			String date = localdate.toString();
			label[6] = date;
			arr[6] = apiDataForDateRange(Username, date ,resAl);

			String dateforchart = findPrevDay(1);
			label[5] = dateforchart;
			arr[5] = apiDataForDateRange(Username, dateforchart,resAl);

			dateforchart = findPrevDay(2);
			label[4] = dateforchart;
			arr[4] = apiDataForDateRange(Username, dateforchart,resAl);

			dateforchart = findPrevDay(3);
			label[3] = dateforchart;
			arr[3] = apiDataForDateRange(Username, dateforchart,resAl);

			dateforchart = findPrevDay(4);
			label[2] = dateforchart;
			arr[2] = apiDataForDateRange(Username, dateforchart,resAl);

			dateforchart = findPrevDay(5);
			label[1] = dateforchart;
			arr[1] = apiDataForDateRange(Username, dateforchart,resAl);

			dateforchart = findPrevDay(6);
			label[0] = dateforchart;
			arr[0] = apiDataForDateRange(Username, dateforchart,resAl);

			chartData.setData(arr);
			return chartData;
		} catch (Exception e) {
			logger.info("Exception chart data not found " + e.getMessage());
			return null;
		}
	}
	
	
	

	@SuppressWarnings("unchecked")
	public int apiDataForDateRange(String Username ,String date ,ArrayList<String> resAl)
	{
			int count = 0;
			try {
//				ArrayList<String> resAl = getDataFromignite();
				for (String line : resAl) {
					String[] CsvData = line.split(",");
					if (!CsvData[3].toString().isEmpty()) {
						if (date.contentEquals((CsvData[3])) && CsvData[3] != ""
							 && 	CsvData[9].contentEquals(Username)
								&& !CsvData[7].replaceAll("^\"|\"$", "").equalsIgnoreCase("null")
								&& !CsvData[7].replaceAll("^\"|\"$", "").isEmpty()) {
							count = count + Integer.parseInt(CsvData[7]);
						}
					}
				}
				return count;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage() +" Problem in fetching igniteapiforreports data");
		}
			return count;
	
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getDataFromignite() {
		ArrayList<String> resAl = new ArrayList<String>();
		try {
			String url = env.getProperty("apiurl");
			RestTemplate rt = new RestTemplate();
//			byte[] b = rt.getForObject(url, byte[].class);
//			resAl = (ArrayList<String>) SerializationUtils.deserialize(b);
			
			WrapperClass b = rt.getForObject(url, WrapperClass.class);
			resAl = (ArrayList<String>) SerializationUtils.deserialize(b.getData());
			return resAl;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return resAl;
	}
	
	

	
	

	private String findPrevDay(int days) {
		LocalDate localdate = LocalDate.now();
		return localdate.minusDays(days).toString();
	}

	@Override
	public String getCurrentEmailId(String userName) {
		return userDao.getCurrentEmailId(userName);
	}

}
