package in.cdac.portal.modal;

public class Constant {

	
	public static final String CAPTCHA_NAME = "captchaValue";
	
	public static final String SCHEMANAME_PREFIX="dept";
	
	public static final String SCHEMANAME_DELIMITER="_";
	
	public static final String USERNAME_PREFIX = "aua";
	
	public static final String DEFAULT_PASSWORD ="Welcome@123"; 
	
	public static final String EMAILFROM= "IOCL-Admin";
	
	public static final String LICENCE_KEY_EXPIRIRATION_SUBJECT= "Your Licence keys is about to expire!!";

	public static final String LICENCE_KEY_EXPIRIRATION_BODY_START="Dear User,<br><br> Your following licence keys are about to expire please start the process of renewal of respective  keys.<br><br>Department Name : ";
	
	public static final String LICENCE_KEY_EXPIRIRATION_BODY_MIDDLE=".<br><br><table border='1'><th>AC</th><th>Environment</th><th>Type</th><th>valid till</th><th>licence key</th>";
	
	public static final String LICENCE_KEY_EXPIRIRATION_BODY_END="</table><br><br> Warm Regards,<br> IOCL Management Portal Admin.<hr><br>This is a system generated email.Please do not reply to this email.<br>For any further  assistance contact IOCL Management Portal Help-desk.";
			
	
	
	public static final String USER_CREATION_EMAIL_SUBJECT = "AUA User Credentials !!";
	
	public static final String USER_CREATION_EMAIL_HASH_STRING="welcome_to_IOCL";
	
	
	//public static final String USER_CREATION_EMAIL_URL= "http://10.10.28.144:8080/IOCL/accountactivation?";
	
	public static final String USER_CREATION_EMAIL_BODY_BEGIN = "Dear SA User,<br><br> You have been registered to IOCL AUA service with user name :";
	
	public static final String USER_CREATION_EMAIL_BODY_MIDDLE = "<br><br> Please click on link below to set your password : <br><br> ";
	
	public static final String USER_CREATION_EMAIL_BODY_END = "<br><br>Warm Regards,<br> IOCL Management Portal Admin.<hr><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";

	
	
	public static final String USER_TRANSACTION_ACTIVATE_EMAIL_SUBJECT = "AUA User Account Unlocked !!";
	
	public static final String USER_TRANSACTION_DEACTIVATE_EMAIL_SUBJECT = "AUA User Account Locked !!";
		
	public static final String USER_TRANSACTION_CHANGE_EMAIL_BODY_BEGIN = "Dear AUA User,<br><br>Dept name : ";
	
	public static final String USER_TRANSACTION_EMAIL_BODY_BEGIN_ENVIRONMENT = "<br><br>Environment:  ";
	
	public static final String USER_TRANSACTION_CHANGE_ACTIVATE_EMAIL_BODY_MIDDLE ="<br><br>Your account with IOCL Management Portal for ASA service has been unlocked.<br>For more details kindly contact IOCL Management Portal Help-desk. <br>";
	
	public static final String USER_TRANSACTION_CHANGE_DEACTIVATE_EMAIL_BODY_MIDDLE = "<br>Your account with IOCL Management Portal for ASA service has been locked.You will not be able to perform any transactions until it is unlocked.<br>For more details kindly contact IOCL Management Portal Help-desk. <br>";
	
	public static final String USER_TRANSACTION_CHANGE_EMAIL_BODY_END ="<br><br>Warm Regards,<br> IOCL Management Portal Admin.<hr><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";
	

	
	
	public static final String USER_LOGIN_ACTIVATE_EMAIL_SUBJECT = "AUA User Portal Account Unlocked !!";
	
	public static final String USER_LOGIN_DEACTIVATE_EMAIL_SUBJECT = "AUA User Portal Account Locked !!";
	
	public static final String USER_LOGIN_CHANGE_EMAIL_BODY_BEGIN = "Dear AUA User,<br><br>Dept name :  ";
	
	public static final String USER_LOGIN_CHANGE_EMAIL_BODY_BEGIN_ENVIRONMENT = "<br><br>Environment:  ";
	
	public static final String USER_LOGIN_CHANGE_ACTIVATE_EMAIL_BODY_MIDDLE = "<br><br>Your account with IOCL Management Portal for ASA service web portal has been unlocked.You will be able to login to portal.<br>For more details kindly contact Help-desk.<br>";
	
	public static final String USER_LOGIN_CHANGE_DEACTIVATE_EMAIL_BODY_MIDDLE = "<br>Your account with IOCL Management Portal for ASA service web portal has been locked.You will not be able to login to portal until it is unlocked.<br>For more details kindly contact IOCL Management Portal Help-desk.";
	
	public static final String USER_LOGIN_CHANGE_EMAIL_BODY_END = "<br><br>Warm Regards,<br> IOCL Management Portal Admin.<hr><br><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";
	
	public static final String PASSWORD_RECOVERY_EMAIL_HASH_STRING="welcome_to_IOCL";
	
	//public static final String PASSWORD_RECOVERY_EMAIL_URL= "http://10.10.28.144:8080/IOCL/recoverpassword?";
	
	public static final String PASSWORD_RECOVERY_EMAIL_SUBJECT = "IOCL Management Portal Account : Pasword Recovery !!!";
	
	public static final String PASSWORD_RECOVERY_EMAIL_BODY_BEGIN = "Dear User<br><br> username: ";
	
	public static final String PASSWORD_RECOVERY_EMAIL_BODY_BEGIN_AUA_CODE = "<br> AC Code: ";
	
	public static final String PASSWORD_RECOVERY_EMAIL_BODY_MIDDLE = "<br><br> Please click on link below to reset your password : <br><br> ";
	
	public static final String PASSWORD_RECOVERY_EMAIL_BODY_END = "<br><br>Warm Regards,<br> IOCL Management Portal Admin.<hr><br><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";

	public static final String EMAIL_BODY_END="<br><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";

	
	
	public static final String FRAUD_DETECTION_SUBJECT= "Transaction Notice!!";
	
	public static final String FRAUD_DETECTION_EMAIL_BODY_BEGIN="Dear AUA user,<br><br>Department :";
	
	public static final String FRAUD_DETECTION_EMAIL_BODY_BEGIN_PART_TWO="<br>Environment :";
	
	//public static final String FRAUD_DETECTION_EMAIL_BODY_MIDDLE="<br><br>This is to inform you that following  transactions has been done during Holiday/Sunday or non-working hours. <br>If not done by the department application, Kindly contact IOCL Management Portal Help-desk immediately.<br><br><table border='1'><tr><th>AC</th><th>Count</th><th>Type</th><th>Date</th><th>Time from</th><th>Time To</th></tr>";
	public static final String FRAUD_DETECTION_EMAIL_BODY_MIDDLE="<br><br>This is to bring to your notice that transactions (OTP/Auth/e-KYC) as mentioned in below table have been carried out during non-working day or on odd timings.<br>Kindly contact IOCL Management Portal immediately if these transactions have not been initiated by department application(s).<br><br><table border='1'><tr><th>AC</th><th>UDC</th><th>Count</th><th>Type</th><th>Date</th><th>Time from</th><th>Time To</th></tr>";
	
	public static final String FRAUD_DETECTION_EMAIL_BODY_END="</table><br><br><br><br>Warm Regards,<br>IOCL Management Portal Admin.<hr><br><br>This is a system generated email.Please do not reply to this email.<br>For any further assistance contact IOCL Management Portal Help-desk.";
	
	public static final String CERTIFICATE_EXPIRIRATION_SUBJECT= "Your Certificate is about to expire!!";

	public static final String CERTIFICATE_EXPIRIRATION_BODY_START="Dear User,<br><br> Your following Certificate are about to expire please start the process of renewal of respective  Certificate.<br><br>Department Name : ";
	
	public static final String CERTIFICATE_EXPIRIRATION_BODY_MIDDLE=".<br><br><table border='1'><th>AUA Code</th><th>Environment</th><th>Type</th><th>valid till</th><th>Certificate Serial No.</th>";
	
	public static final String CERTIFICATE_EXPIRIRATION_BODY_END="</table><br><br> Warm Regards,<br> IOCL Management Portal Admin.<hr><br>This is a system generated email.Please do not reply to this email.<br>For any further  assistance contact IOCL Management Portal Help-desk.";
	
	}



