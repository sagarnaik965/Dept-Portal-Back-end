package in.cdac.portal.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import in.cdac.portal.modal.AllowedOpr;
import in.cdac.portal.modal.AppDetail;
import in.cdac.portal.modal.AppList;
import in.cdac.portal.modal.AppLk;
import in.cdac.portal.modal.DeptList;

public interface UserService {


	public DeptList getDeptcodeFromUsername(String username);

	 
	public List<AppList> getAppListR(String deptcode);

	 
	public AppDetail getAppDetailR(String appcode);

	 
	public  List<AppLk> getAppLkR(String appcode);

	 
	public AllowedOpr getOprR(String appcode);

 
	public RedirectView getusername(HttpSession session);

 
	public RedirectView getLogin(@RequestParam(required = false) String authfailed, String logout, String denied,
			String captchainvalid, String disabled);


	public ResponseEntity<String> isSessNull(String username);

	
	public RedirectView getHome(HttpSession session);


	public ResponseEntity<String> loggingOut(String username);

	public RedirectView getusernames(HttpSession session);

	public RedirectView getHomeSaveSession(HttpSession session);

	public List<AppList> getAppListForReportsR(String username);

	ModelAndView logoutSSO(String sessionId,String sub);

	public Long applkexpiryalert(String appcode);


}
