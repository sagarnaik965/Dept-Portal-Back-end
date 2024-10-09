package in.cdac.portal.controller;

import java.util.List;

import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

import org.opensaml.saml2.metadata.validator.EmailAddressSchemaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.portal.modal.Activities;
import in.cdac.portal.modal.Algo_Info;
import in.cdac.portal.modal.AllowedOpr;
import in.cdac.portal.modal.AppDetail;
import in.cdac.portal.modal.AppList;
import in.cdac.portal.modal.AppLk;
import in.cdac.portal.modal.ChartforInvoice;
import in.cdac.portal.modal.DeptDetails;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.GenerateAppLK;
import in.cdac.portal.modal.KeyInfo;
import in.cdac.portal.modal.KeyMapping;
import in.cdac.portal.modal.OprList;
import in.cdac.portal.modal.Slot;
import in.cdac.portal.modal.StateList;
import in.cdac.portal.services.AdminService;
import in.cdac.portal.services.DashboardService;
import in.cdac.portal.services.EmailServices;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	AdminService service;
	static HttpSession sess;

	@Inject
	Validator validator;
	@Autowired
	Environment env;
	
	@Autowired
	DashboardService dashServ;
	@Autowired
	EmailServices es;
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	// Admin Application Detail

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/appdetail/{appcode}", method = RequestMethod.GET)

	public AppDetail appdetailprint(@PathVariable(name = "appcode", required = false) String appcode) {

		AppDetail appdetail = service.getAppDetailR(appcode);
//				sort(deptList);
		if (appdetail != null) {
			return appdetail;
		}
		return null;
	}

	// @ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/applist")
//	@RequestMapping(value = "/applist", method = RequestMethod.POST)
	public List<AppList> applistprint(@RequestBody	 String deptcode) {
		List<AppList> appList = service.getAppListR(deptcode);
//					sort(deptList);
		if (appList != null) {
			return appList;
		}
//		System.out.println(appList);
		return null;
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

	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/adminapplk/{appcode}", method = RequestMethod.GET)

	public AppLk adminapplkprint(@PathVariable(name = "appcode", required = false) String appcode) {

//		System.out.println("admin app lk -------------------");
		// if(sess!=null) {
		AppLk applk = service.getAdminAppLkR(appcode);

//					sort(deptList);
		if (applk != null) {
			return applk;
		}
		// return null;
		// }
		return null;
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/adminmultipleapplk/{appcode}", method = RequestMethod.GET)

	public List< AppLk> adminmultipleapplkprint(@PathVariable(name = "appcode", required = false) String appcode) {

//		System.out.println("admin app lk -------------------");
		// if(sess!=null) {
		List< AppLk> applk = service.getAdminMultipleAppLkR(appcode);

//					sort(deptList);
		if (applk != null) {
			return applk;
		}
		// return null;
		// }
		return null;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/appupdate/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//			@RequestMapping(value="/appupdate")
	@PostMapping
	// @JsonManagedReference
	public String getAppUpdateR(@Valid @RequestBody AppDetail appdetail,
			@PathVariable(name = "appcode", required = false) String appcode) {

		String adu = service.getAppUpdateR(appdetail, appcode);

		return adu;

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/opr/{appcode}", method = RequestMethod.GET)

	public AllowedOpr oprprint(@PathVariable(name = "appcode", required = false) String appcode) {

		AllowedOpr opr = service.getOprR(appcode);
//					sort(deptList);
		if (opr != null) {
			return opr;
		}
		return null;
	}

//Admin Dept Registration

	@GetMapping("/statelist")
	public List<StateList> getStateList() {

		List<StateList> stateList = service.getStateList();
		sort(stateList);
		if (stateList != null) {
			return stateList;
		}
		return null;
	}

	public static void sort(List<StateList> stateList) {

		stateList.sort((o1, o2) -> o1.getStateCode().compareTo(o2.getStateCode()));
	}

	// for department registration

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/deptregistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String deptRegistrationR(@RequestBody DeptDetails deptdetails) {
		// System.out.println("in getting roles : "+ useranem);

		String deptregis = service.deptRegistrationR(deptdetails);
//		System.out.println(deptdetails + "--------------------in dept reg controller");
		return deptregis;

	}

	/// for application creation

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/appcreate/{deptcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String appCreateR(@Valid @RequestBody AppDetail appcreate,
			@PathVariable(name = "deptcode", required = false) String deptcode) {
		// System.out.println("in getting roles : "+ useranem);

		String appcreation = service.appCreateR(appcreate, deptcode);

		return appcreation;

	}

	// --------------------for key registration------------------------

	@GetMapping("/oprlist")
	public List<OprList> getOprList() {

		List<OprList> oprList = service.getOprList();
//					 sort(stateList);
		if (oprList != null) {
			return oprList;
		}
		return null;
	}

	@GetMapping("/algoid")
	public List<Algo_Info> getAlgoIdList() {

		List<Algo_Info> algoidList = service.getAlgoIdList();
//					 sort(stateList);
		if (algoidList != null) {
			return algoidList;
		}
		return null;
	}

	@GetMapping("/slot")
	public List<Slot> getSlotList() {

		List<Slot> slot = service.getSlotList();
//					 sort(stateList);
		if (slot != null) {
			return slot;
		}
		return null;
	}

	// key info insertion

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/keyinfoinsert/{deptcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String keyInfoInsert(@Valid @RequestBody KeyInfo keyinfo,
			@PathVariable(name = "deptcode", required = false) String deptcode) {
		// System.out.println("in getting roles : "+ useranem);

		String keyinfoinsert = service.keyInfoInsert(keyinfo, deptcode);

		return keyinfoinsert;

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/keyinfoinsertforsoft/{deptcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String keyinfoinsertforsoft(@Valid @RequestBody KeyInfo keyinfo,
			@PathVariable(name = "deptcode", required = false) String deptcode) {
		// System.out.println("in getting roles : "+ useranem);

		String keyinfoinsert = service.keyinfoinsertforsoft(keyinfo, deptcode);

		return keyinfoinsert;

	}

	// key Mapping
	@GetMapping("/keyid")
	public List<KeyInfo> getKeyId() {

		List<KeyInfo> keyid = service.getKeyId();
//					 sort(stateList);
		if (keyid != null) {
			return keyid;
		}
		return null;
	}

//		@GetMapping("/keyinfodetails")
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public List<Activities> getActivity() {

		List<Activities> activity = service.getActivity();
//			 sort(stateList);
		if (activity != null) {
			return activity;
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/deptname/{deptcode}", method = RequestMethod.GET)

	public DeptList getDeptnamewithDeptcode(@PathVariable(name = "deptcode", required = false) String deptcode) {

		DeptList dptname = service.getDeptnamewithDeptcode(deptcode);
//				sort(deptList);
		if (dptname != null) {
			return dptname;
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/keymappinginsert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String keyMappingInsert(@Valid @RequestBody KeyMapping keymap) {
		// System.out.println("in getting roles : "+ useranem);

		String keymapping = service.keyMappingInsert(keymap);

		return keymapping;

	}

//	@GetMapping("/keyinfodetails")
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/keyinfodetails/{key_info_id}", method = RequestMethod.GET)
	public KeyInfo getKeyinfo(@PathVariable(name = "key_info_id", required = false) int key_info_id) {

		KeyInfo key = service.getKeyinfo(key_info_id);
//		 sort(stateList);
		if (key != null) {
			return key;
		}
		return null;
	}
	
	
	//for lk expiry update
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/lkexpiryupdate/{lk}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String getlkexpiryupdateR(@Valid @RequestBody AppLk applkex,
			@PathVariable(name = "lk", required = false) String lk) {
		// System.out.println("in getting roles : "+ useranem);
//		 Set<ConstraintViolation<AppLk>> violations = validator.validate(applkex);
		String applkexpiry = service.getlkexpiryupdateR(applkex, lk);
//		  if (violations.isEmpty()) {
//			  return "not valid!!!!!!!!!!";
//		  }
//		  else
//		  {
				return applkexpiry;
//		  }
	

	}
	
	
	//for lk expiry generate
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/generatelk/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String generatelkR(@Valid @RequestBody GenerateAppLK genlk,
			@PathVariable(name = "appcode", required = false) String appcode) {
		// System.out.println("in getting roles : "+ useranem);

		String lk = service.generatelkR(genlk, appcode);

		return lk;

	}
	//for opr
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/appisactive/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	// @JsonManagedReference
	public String getappisactiveR(@Valid @RequestBody AllowedOpr opr,
			@PathVariable(name = "appcode", required = false) String appcode) {
		// System.out.println("in getting roles : "+ useranem);

		String opru = service.getappisactiveR(opr, appcode);

		return opru;

	}
	
	
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/struid/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getstruidR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getstruidR(opr, appcode);

			return opru;

		}
		
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/refnum/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getrefnumR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getrefnumR(opr, appcode);

			return opru;

		}

		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/uid/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getuidR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getuidR(opr, appcode);

			return opru;

		}

		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/act/{appcode}")
		@PostMapping
		// @JsonManagedReference
		public String getactivateR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getactivateR(opr, appcode);

			return opru;

		}

		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/deact/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getdeactivateR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getdeactivateR(opr, appcode);

			return opru;

		}

		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/dupcheck/{appcode}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getdupcheckR(@Valid @RequestBody AllowedOpr opr,
				@PathVariable(name = "appcode", required = false) String appcode) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getdupcheckR(opr, appcode);

			return opru;

		}
		
		
		@GetMapping("/otp")
		public String  sendEmail()
		{
			Random random = new Random();

			String otp = String.format("%04d", random.nextInt(10000));
//			System.out.println("otp"+otp);
		es.sendMail( env.getProperty("otpMail"), "Aadhaar Data Vault Administrator ", "OTP :"+otp, false);
		
			return otp;
		}

		//---------INVOICE----------
		@RequestMapping("/invoice")
		@GetMapping
		@CrossOrigin(origins = "http://localhost:3000")
		public List<ChartforInvoice> invoicedata() {
			List<ChartforInvoice> chartforInvoice = service.getinvoicedata();
			if (chartforInvoice != null) {
				return chartforInvoice;
			}
			return null;
		}
		
		@RequestMapping("/invoiceclientlist")
		@GetMapping
		@CrossOrigin(origins = "http://localhost:3000")
		public List<ChartforInvoice> invoiceclientlist() {
			List<ChartforInvoice> chartforInvoice = service.invoiceclientlist();
			if (chartforInvoice != null) {
				return chartforInvoice;
			}
			return null;
		}
		
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping("/invoicefordept/{deptcode}/{year}")
		@GetMapping
	
		public  List<ChartforInvoice> getinvoicedetails(@PathVariable(name = "deptcode", required = false) String deptcode,@PathVariable(name = "year", required = false) String year) {
			// System.out.println("in getting roles : "+ useranem);

//			System.out.println(deptcode+"----deptcode");
//			System.out.println(year+"-year");
			 List<ChartforInvoice> cfi = service.getinvoicedetails(deptcode,year);

			return cfi;

		}
		
//		@CrossOrigin(origins = "http://localhost:3000")
//		@RequestMapping("/invoicefordeptq/{deptcode}/")
//		@GetMapping
//	
//		public  Map<Float,String> getinvoicedetailsQ(@PathVariable(name = "deptcode", required = false) String deptcode) {
//			// System.out.println("in getting roles : "+ useranem);
//
//			 Map<Float,String> map = service.getinvoicedetailsQ(deptcode);
//
//			return map;
//
//		}
		
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/invoiceform", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getinvoiceregistration(@Valid @RequestBody ChartforInvoice cfi) {
			// System.out.println("in getting roles : "+ useranem);

			String done = service.getinvoiceregistration(cfi);

			return done;

		}
	
	
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value = "/invoicestatus/{deptcode}/{quarter}/{paymentstatusedited}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping
		// @JsonManagedReference
		public String getinvoicestatusR(@Valid @RequestBody ChartforInvoice cfi,
				@PathVariable(name = "deptcode", required = false) String deptcode,@PathVariable(name = "quarter", required = false) String quarter,@PathVariable(name = "paymentstatusedited", required = false) String paymentstatusedited) {
			// System.out.println("in getting roles : "+ useranem);

			String opru = service.getinvoicestatusR(deptcode, quarter,paymentstatusedited);

			return opru;

		}


}
