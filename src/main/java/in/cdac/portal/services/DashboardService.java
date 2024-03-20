package in.cdac.portal.services;

import java.util.List;
import java.util.Map;

import in.cdac.portal.modal.ChartData;
import in.cdac.portal.modal.Count;
import in.cdac.portal.modal.Count1;
import in.cdac.portal.modal.DeptDetails;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.UserStatus;

public interface DashboardService {

	// dash
	public int getHomePageSuccessCountR(String username);

	// dash
	public int getTotalErrorCountR(String username);

	// dash
	public Map<String, Integer> getMonthlyTotalTransR(String principal);

	// dash
	public int getTotalAcCountDeptWiseR(String string);

	// dash
	public List<Count1> DonutChart(String string);

	// dash
	public List<UserStatus> getAppcodeR(String username);

	// dash
	public List<Count> acwiseTotaltransR(String[] appcodedata);

	// dash
	public Map<String, DeptDetails> getDeptServiceDetailsR(String string);

	// dash
	public List<DeptList> getDeptListR();

	// dash
	public ChartData getDataForChart(String username);

	// dash
	public String getCurrentEmailId(String userName);

	List<Count1> DonutchartType(String[] chartData);
}
