package in.cdac.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import in.cdac.portal.modal.AllowedOpr;
import in.cdac.portal.modal.AppDetail;
import in.cdac.portal.modal.AppList;
import in.cdac.portal.modal.AppLk;
import in.cdac.portal.modal.ChartData;
import in.cdac.portal.modal.Count;
import in.cdac.portal.modal.Count1;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.UserStatus;
import in.cdac.portal.modal.WrapperClass;
import in.cdac.portal.services.BillingServices;
import in.cdac.portal.services.DashboardService;
import in.cdac.portal.services.SummaryServices;
import in.cdac.portal.services.UserService;

@CrossOrigin(origins = { "*" })
@RequestMapping("/dept")
@RestController
public class DeptController {

	@Autowired
	Environment env;
	
	@Autowired
	UserService userServ;

	@Autowired
	BillingServices billSer;

	@Autowired
	DashboardService dashServ;

	@Autowired
	SummaryServices summServ;

//	private final static Logger logger = Logger.getLogger(DeptController.class);
	 private final  static Logger logger = LogManager.getLogger( DeptController.class );


	@PostMapping("/getDeptcodeandName")
	public DeptList getDeptcodeFromUsername(@RequestBody String username) {
		try {

			return userServ.getDeptcodeFromUsername(username);
		} catch (Exception e) {

			logger.info("Problem to fetch deptList " + e);
			return null;
		}

	}

	@PostMapping("/applist")
	public List<AppList> applistprint(@RequestBody String deptcode) {
		List<AppList> appList = userServ.getAppListR(deptcode);

		if (appList != null) {
			return appList;
		}

		return null;

	}

	@PostMapping("/appdetail")
	public AppDetail appdetailprint(@RequestBody String appcode) {
		AppDetail appdetail = userServ.getAppDetailR(appcode);
		if (appdetail != null) {
			return appdetail;
		}
		return null;
	}

	@PostMapping("/applk")
	public List<AppLk> applkprint(@RequestBody String appcode) {

		List<AppLk> applk = userServ.getAppLkR(appcode);
		if (applk != null) {
			return applk;
		}
		return null;
	}

	@PostMapping("/applkexpiryalert")
	public Long applkexpiryalert(@RequestBody String appcode) {
		Long applk = userServ.applkexpiryalert(appcode);
		if (applk != null) {
			return applk;
		}
		return null;
	}

	@PostMapping("/opr")
	public AllowedOpr oprprint(@RequestBody String appcode) {
		AllowedOpr opr = userServ.getOprR(appcode);
		if (opr != null) {
			return opr;
		}
		return null;
	}

	@GetMapping(path = "/getusername")
	public RedirectView getusername(HttpSession session) {

		return userServ.getusernames(session);
	}

	@RequestMapping("/deptlist")
	@GetMapping
	public List<DeptList> deptlistprint() {
		List<DeptList> deptList = dashServ.getDeptListR();
		if (deptList != null) {
			return deptList;
		}
		return null;
	}

	@ResponseBody
	@PostMapping(value = "/appcodedetails")
	public List<UserStatus> appcodeDetailsR(@RequestBody String username) {
		List<UserStatus> userlist = new ArrayList<>();
		try {
			userlist = dashServ.getAppcodeR(username);
			return userlist;
		} catch (Exception e) {
			logger.info("Exception Data for list of application not found " + e.getMessage());
			return userlist;
		}

	}

	@RequestMapping(value = "/applicationwisedata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public List<Count> acwiseTotaltransR(@RequestBody String[] appcodedata) {		
			return  dashServ.acwiseTotaltransR(appcodedata);			
	}

//	------------------------HomePage Dashboard Stats-----------------------------

	@PostMapping(path = "/homepagesuccesscount")
	public int getHomePageSuccessCountR(@RequestBody String username) {
		return dashServ.getHomePageSuccessCountR(username);
	}

	@PostMapping(path = "/totalerrorcount")
	public int totalErrorCountR(@RequestBody String username) {
		return dashServ.getTotalErrorCountR(username);
	}

	@PostMapping(path = "/totalaccountdeptwise")
	public int getTotalAcCountDeptWiseR(@RequestBody String username) {
	     return dashServ.getTotalAcCountDeptWiseR(username);
			
	}

//	---------------------------Billing-----------------------------------------
	
	@PostMapping(value = "/billDeptCategpdf")
	public ResponseEntity<byte[]> getBillDeptLevelCategorypdf(@RequestBody String[] datedata) {			
			return billSer.getBillingDataForPdf(datedata);
	}

	@PostMapping(value = "/billDeptCategCsv")
	public ResponseEntity<List<String[]>> getBillDeptLevelCategorycsv(@RequestBody String[] datedata) {			
			return billSer.getBillingDataForDeptCsv(datedata);		
	}	
	
	@PostMapping(value = "/billAppCategCsv")
	public ResponseEntity<List<String[]>> getBillAppLevelCategorycsv(@RequestBody String[] datedata) {
			return billSer.getBillingDataForAppCsv(datedata);		
	}	

	@RequestMapping(value = "/billAppCategpdf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ResponseEntity<byte[]> getBillAppLevelCategorypdf(@RequestBody String[] datedata) {
			return billSer.getBillingDataForPdfapp(datedata);
	}
	
	
	


//	--------------------------Summary reports------------------------------------
	@PostMapping(value = "/summaryreportappwise")
	public ResponseEntity<byte[]> summaryreportpdfApp(@RequestBody String[] datedata) {		
			return summServ.getSummaryForJasperIgniteapp(datedata);
	}

	@PostMapping(value = "/summaryreport")
	public ResponseEntity<byte[]> summaryreportpdf(@RequestBody String[] datedata) {		
			return summServ.getSummaryForJasperIgnite(datedata);
	}
	
	@PostMapping(value = "/summaryreportcsv")
	public ResponseEntity<List<String[]>> summaryreportcsv(@RequestBody String[] datedata) {		
			return summServ.getSummaryForJasperIgnitecsv(datedata);
	}
	
//	@PostMapping(value = "/summaryreportappwisecsv")
//	public ResponseEntity<byte[]> summaryreportcsvApp(@RequestBody String[] datedata) {		
//			return summServ.getSummaryForJasperIgniteappcsv(datedata);
//	}

	
	@PostMapping("/applistForReports")
	public List<AppList> applistForReports(@RequestBody String username) {
		List<AppList> appList = userServ.getAppListForReportsR(username);
		if (appList != null) {
			return appList;
		}
		return null;

	}

//	---------------------------Charts ---------------------------------------
	@PostMapping(path = "/DonutChart")
	public List<Count1> DonutChart(@RequestBody String username) {
		return dashServ.DonutChart(username);
			 
	}

	@PostMapping(path = "/DonutchartType")
	public List<Count1> DonutchartType(@RequestBody String[] chartData) {
			return dashServ.DonutchartType(chartData);
			
	}

	@PostMapping(value = "chartfordays")
	public ChartData countsforchartdatwise(@RequestBody String username) {		
			return dashServ.getDataForChart(username);
	}

	@PostMapping("/testName")
	public void testName(@RequestBody String pass) {

	}
//	------------------------------------------------------------
	
	@ResponseBody
	@RequestMapping(value = "/PortalData", method = RequestMethod.GET)
	public WrapperClass PortalDataIgnite() {
		try {
			ArrayList<String> resAl = new ArrayList<String>();
			
			String url = env.getProperty("apiurl");
			RestTemplate rt = new RestTemplate();
			WrapperClass b = rt.getForObject(url, WrapperClass.class);
			return b;	
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}
 

}
