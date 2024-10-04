package in.cdac.portal.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.portal.services.StaticService;
@CrossOrigin(origins = { "*"})

@RequestMapping("/dept")
@RestController
public class StaticController {
	
	@Autowired
	Environment env;
	
	@Autowired
	StaticService statserv;

	@GetMapping(value = "/workshopfile")
	public  List<String> readFileAsString() throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(env.getProperty("workshopfilepath"))));
		Map<String,String> workshopmap=new HashedMap<>();
		List<String> workshopList = new ArrayList<>();
		String[] arrOfStr = data.split(";");
		for (String a : arrOfStr) {
			workshopList.add(a);
			workshopmap.put(a,a);
		}
//		System.out.println(workshopmap);
		return workshopList;
	}

	
	@GetMapping(value = "/workshopfilehindi")
	public  List<String> readFileAsStringHindi() throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(env.getProperty("workshopfilepathhindi"))));
		Map<String,String> workshopmap=new HashedMap<>();
		List<String> workshopList = new ArrayList<>();
		String[] arrOfStr = data.split(";");
		for (String a : arrOfStr) {
			workshopList.add(a);
			workshopmap.put(a,a);
		}
//		System.out.println(workshopmap);
		return workshopList;
	}


////dashboard dept count
//	@ResponseBody
//	@RequestMapping(value = "/count", method = RequestMethod.GET)
//	public int getTotaldeshDeptCouont(Model model) {
//		try {
//			return readSer.deptCount();
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return 1;
//		}
//
//	}


@GetMapping(value = "deptcount")
public int getTotaldeshDeptCouont() {
	return statserv.getTotaldeshDeptCouont();

	}

@GetMapping(value = "account")
public int getTotaldeshAcCount() {
	return statserv.getTotaldeshAcCount();

	}


@GetMapping(value = "transcount")
public int getTotaldeshTransCount() {
	return statserv.getTotaldeshTransCount();

	}




////controller for getting Lists of department
//	@GetMapping(value = "/deptLists")
//	public String deptLists(ModelMap model) {
//		try {
//			model.addAttribute("deptdata1", readSer.deptList());
//			return "/deptLists";
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//	}





@GetMapping(value = "deptList1")
public Map<String, Map<String,Integer>> getdeptLists1() {
	

	return statserv.getdeptLists1();

	}
//
//@CrossOrigin(origins = PortalConstant.BASEURL + PortalConstant.PORT)
//@GetMapping(value = "deptList")
//public String deptservicewisetransaction() {
//	return statserv.getdeptLists();
//
//	}


////controller for getting dept-service wise transaction
//	@GetMapping(value = "/deptservicewisetransaction")
//	public String deptservicewisetransaction(@RequestParam String deptName, ModelMap model) {
//		try {
//			Map<String, Integer> deptservicetransactions = readSer.getapkAndTransAcToDept(deptName);
//			model.addAttribute("deptdata", deptservicetransactions);
//			return "/deptservicewisetransaction";
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//	}







//// controller for getting dept wise transaction
//@GetMapping(value = "/depttransactions")
//public String depttransaction(ModelMap model) {
//	try {
//		model.addAttribute("deptdata", readSer.deptWiseCount());
//		return "/depttransactions";
//	} catch (Exception e) {
//		logger.info(e.getMessage());
//		return null;
//	}
//}




//////controller for getting dept-service wise transaction
//@CrossOrigin(origins = PortalConstant.BASEURL + PortalConstant.PORT)
//@GetMapping(value = "depttransactions")
//public Map<String, Integer> depttransaction() {
//return statserv.depttransaction();
//
//}







////controller for getting dept-service wise transaction
//	@GetMapping(value = "/deptservicewisetransaction")
//	public String deptservicewisetransaction(@RequestParam String deptName, ModelMap model) {
//		try {
//			Map<String, Integer> deptservicetransactions = readSer.getapkAndTransAcToDept(deptName);
//			model.addAttribute("deptdata", deptservicetransactions);
//			return "/deptservicewisetransaction";
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//	}



@GetMapping(value = "deptandServicecount")
public Map<String, Integer> apkWiseCount() {
	

	return statserv.apkWiseCount();

	}



////controller for getting department and counts of services
//	@GetMapping(value = "/deptandServicecount")
//	public String getDetailsOfDepartments(ModelMap model) {
//		try {
//			model.addAttribute("deptdata", readSer.apkWiseCount());
//			return "/deptServiceCount";
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//			return null;
//		}
//	}


// controller for getting dept wise transaction
@GetMapping(value = "depttransactions")
public Map<String, Integer> deptwisetransaction() {
	

	return statserv.deptwisetransaction();

	}




//// controller for getting dept wise transaction
//@GetMapping(value = "/depttransactions")
//public String depttransaction(ModelMap model) {
//	try {
//		model.addAttribute("deptdata", readSer.deptWiseCount());
//		return "/depttransactions";
//	} catch (Exception e) {
//		logger.info(e.getMessage());
//		return null;
//	}


// controller for getting dept-service wise transaction
@PostMapping(value = "/deptservicewisetransaction")
public 	Map<String, Integer> deptservicewisetransaction(@RequestBody String deptName) {
//	System.out.println(deptName+"------------");
	try {
		Map<String, Integer> deptservicetransactions = statserv.getapkAndTransAcToDept(deptName);
	
		return deptservicetransactions;
	} catch (Exception e) {
//		logger.info(e.getMessage());
		return null;
	}
}

}














