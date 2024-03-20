package in.cdac.portal.dao;

import java.util.Map;

public interface StaticDao {

	public int getTotaldeshDeptCouont();

	public int getTotaldeshAcCount();

	public int getTotaldeshTransCount();




	public Map<String, Map<String, Integer>> getdeptLists1();

	public Map<String, Integer> apkWiseCount();

	public Map<String, Integer> deptwisetransaction();

	public Map<String, Integer> getapkAndTransAcToDept(String deptName);

}
