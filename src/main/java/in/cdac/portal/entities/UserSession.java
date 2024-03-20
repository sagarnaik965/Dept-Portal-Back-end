package in.cdac.portal.entities;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class UserSession {

public	static HashMap<String ,SSOSession> currSession;



public static HashMap<String, SSOSession> getCurrSession() {
	return currSession;
}

public static void setCurrSession(String user, SSOSession session) {
	if(currSession==null) 
	{
		currSession=new HashMap<String ,SSOSession>();
	}
	UserSession.currSession.put(user, session);
}
	
}
