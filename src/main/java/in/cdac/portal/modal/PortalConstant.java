package in.cdac.portal.modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
@PropertySource(value ="classpath:application.properties")
public class PortalConstant {
	
	
	public static final String DATASOURCE = "classpath:/datasources.properties";
	//public static final String APKPROP = "classpath:application.properties";
	public static final String EMAIL_CONFIG = "classpath:email_config.properties";
	
	
}
