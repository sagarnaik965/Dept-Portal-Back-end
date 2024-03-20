package in.cdac.portal.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cdac.portal.dao.StaticDao;

@Service
public class StaticServiceImpl implements StaticService {

	@Autowired
	StaticDao statdao;

	@Override
	public int getTotaldeshDeptCouont() {
		// TODO Auto-generated method stub
		return statdao.getTotaldeshDeptCouont();
	}

	@Override
	public int getTotaldeshAcCount() {
		// TODO Auto-generated method stub
		return statdao.getTotaldeshAcCount();
	}

	@Override
	public int getTotaldeshTransCount() {
		// TODO Auto-generated method stub
		return statdao.getTotaldeshTransCount();
	}

	

	

	@Override
	public Map<String, Map<String, Integer>> getdeptLists1() {
		// TODO Auto-generated method stub
		return statdao.getdeptLists1();
	}

	@Override
	public Map<String, Integer> apkWiseCount() {
		// TODO Auto-generated method stub
		return statdao.apkWiseCount();
	}

	@Override
	public Map<String, Integer> deptwisetransaction() {
		// TODO Auto-generated method stub
		return statdao.deptwisetransaction();
	}

	@Override
	public Map<String, Integer> getapkAndTransAcToDept(String deptName) {
		// TODO Auto-generated method stub
		return statdao.getapkAndTransAcToDept(deptName);
	}

}
