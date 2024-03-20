package in.cdac.portal.services;

import java.util.Map;

import in.cdac.portal.modal.StaticDept;

public interface StaticService {

	public int getTotaldeshDeptCouont();

	public int getTotaldeshAcCount();

	public int getTotaldeshTransCount();



	public Map<String, Map<String, Integer>> getdeptLists1();

	public Map<String, Integer> apkWiseCount();

	public Map<String, Integer> deptwisetransaction();

	public Map<String, Integer> getapkAndTransAcToDept(String deptName);

}
