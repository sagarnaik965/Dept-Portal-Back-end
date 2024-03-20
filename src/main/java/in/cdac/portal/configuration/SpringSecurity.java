package in.cdac.portal.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Service;

import in.cdac.dbswitch.CustomUserDetailsAuthenticationProvider;
import in.cdac.dbswitch.CustomUserDetailsService;
import in.cdac.dbswitch.DataSourceProperties;
import in.cdac.portal.filter.CaptchaUsernamePasswordAuthenticationFilter;

@Configuration
@Service
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

//	private final static Logger logger = Logger.getLogger(SpringSecurity.class);
	 private final  static Logger logger = LogManager.getLogger( SpringSecurity.class );


	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	DataSourceProperties dsp;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("configureGlobal");
		auth.authenticationProvider(authProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.info("configure");
		web.ignoring().antMatchers("/resources/**").antMatchers("/captcha.png");
		web.ignoring().antMatchers("/Demo");
		web.ignoring().antMatchers("/UDemo");
		web.ignoring().antMatchers("/printUS");
		web.ignoring().antMatchers("/Logout/**");

		web.ignoring().antMatchers("/postSSOlogout/**");

		// onetimepushback
		// loadverification.do
		web.ignoring().antMatchers("/loadverification.do");
		web.ignoring().antMatchers("/onetimepushback");
		web.ignoring().antMatchers("/isSessNull");
		// isSessNull
		// http://localhost:8080/Logout
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure");
		http.headers().frameOptions().disable();
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**", "/error", "/client/*").permitAll()
				.antMatchers("/dashboard/**").permitAll().antMatchers("/admin/**").access("hasRole('2')")
				.antMatchers("/accountactivation*").permitAll().antMatchers("/updateaccountpassword*").permitAll()
				.antMatchers("/recoverpassword*").permitAll().antMatchers("/updaterecovepassword*").permitAll()
				.antMatchers("/sendrecoverypasswordlink*").permitAll().antMatchers("/osticketlogin*").permitAll()
				.antMatchers("/forgetpassword*").permitAll().antMatchers("/aboutus*").permitAll()
				.antMatchers("/contactus*").permitAll().antMatchers("/aua/contactus*").permitAll().antMatchers("/help*")
				.permitAll().antMatchers("/logout*").permitAll().antMatchers("/captcha.png").permitAll()
				.antMatchers("/login*").permitAll()
				// .antMatchers("/saveSession/**").permitAll()

//				------------for react application-------------------
//				----------------For Home page------------------------
				.antMatchers("/dept/getRoles/**").permitAll().antMatchers("/dept/DonutChart/**").permitAll()
				.antMatchers("/dept/DonutchartType/**").permitAll()
				
				.antMatchers("/dept/homepagesuccesscount").permitAll().antMatchers("/dept/totalerrorcount").permitAll()
				.antMatchers("/dept/totalaccountdeptwise/**").permitAll()
//				----------------For Application Detaisl------------------------
				.antMatchers("/dept/appcodedetails/**").permitAll()
//				----------------For Application Info------------------------
				.antMatchers("/dept/applicationwisedataNo/*").permitAll().antMatchers("/dept/applicationwisedataYes/*")
				.permitAll().antMatchers("/dept/applicationwisedata/*").permitAll()
				.antMatchers("/dept/applistForReports/**").permitAll()

				.antMatchers("/dept/appupdate/**").permitAll().antMatchers("/dept**").permitAll()
				.antMatchers("/dept/role/**").permitAll()

//				-------------for department summary -------------------------				
				.antMatchers("/dept/monthlyreportpdf/**").permitAll().antMatchers("/dept/quaterlyreportpdf/**")
				.permitAll().antMatchers("/dept/yearlyreportpdf/**").permitAll().antMatchers("/dept/customreportpdf/**")
				.permitAll()
				.antMatchers("/dept/summaryreport/**").permitAll()
				.antMatchers("/dept/summaryreportappwise/**").permitAll()
				
				.antMatchers("/dept/summaryreportcsv/**").permitAll()
				.antMatchers("/dept/summaryreportappwisecsv/**").permitAll()
				
				.antMatchers("/dept/applicationwisedata/**").permitAll()
				

				// applicationwisedata

//				-------------for application summary -------------------------				
				.antMatchers("/dept/monthlyreportpdfapp/**").permitAll().antMatchers("/dept/quaterlyreportpdfapp/**")
				.permitAll().antMatchers("/dept/yearlyreportpdfapp/**").permitAll()
				.antMatchers("/dept/customreportpdfapp/**").permitAll()

				// -----------for static ------------------------
				.antMatchers("/dept/workshopfile/**").permitAll().antMatchers("/dept/workshopfilehindi/**").permitAll()
				.antMatchers("/dept/deptcount/**").permitAll().antMatchers("/dept/account/**").permitAll()
				.antMatchers("/dept/transcount/**").permitAll().antMatchers("/dept/deptList1/**").permitAll()
				.antMatchers("/dept/deptandServicecount/**").permitAll().antMatchers("/dept/depttransactions/**")
				.permitAll()
//				deptservicewisetransaction
				.antMatchers("/dept/deptservicewisetransaction/**").permitAll()

//				
//				----------------For Application Magagement------------------------
				.antMatchers("/dept/applk/**").permitAll().antMatchers("/dept/opr/**").permitAll()
				//applkexpiryalert
				.antMatchers("/dept/applkexpiryalert/**").permitAll()

//				----------------ForAdmin LK Magagement------------------------
				.antMatchers("/dept/adminapplk/*").permitAll().antMatchers("/dept/appcreate/*").permitAll()
				.antMatchers("/dept/statelist").permitAll().antMatchers("/dept/deptregistration").permitAll()
				// .antMatchers("/dept/deptregistration/*").permitAll()

				// for key registration

				.antMatchers("/dept/oprlist").permitAll().antMatchers("/dept/algoid").permitAll()
				.antMatchers("/dept/slot").permitAll().antMatchers("/dept/keyid").permitAll()
				.antMatchers("/dept/keyinfodetails/*").permitAll().antMatchers("/dept/deptname/*").permitAll()
				.antMatchers("/dept/keymappinginsert").permitAll().antMatchers("/dept/keyinfoinsert/*").permitAll()
				.antMatchers("/dept/keyinfoinsertforsoft/*").permitAll()
				// keymappinginsert /keyinfoinsertforsoft/{deptcode}
				// keyinfoinsert

//				
				.antMatchers("/dept/appdetail/**").permitAll().antMatchers("/dept/deptlist").permitAll()
				.antMatchers("/dept/applist/**").permitAll().antMatchers("/dept/dupcheck/*").permitAll()

				.antMatchers("/dept/deact/*").permitAll().antMatchers("/dept/act/*").permitAll()
				.antMatchers("/dept/uid/*").permitAll().antMatchers("/dept/refnum/*").permitAll()
				.antMatchers("/dept/struid/*").permitAll().antMatchers("/dept/lkexpiryupdate/*").permitAll()
				.antMatchers("/dept/generatelk/*").permitAll().antMatchers("/dept/getDeptcodeandName/**").permitAll()

//				-------------for report -------------------------				
				.antMatchers("/dept/monthly/**").permitAll().antMatchers("/dept/quaterly/**").permitAll()
				.antMatchers("/dept/yearly/**").permitAll().antMatchers("/dept/custom/**").permitAll()

//				-------------for summary -------------------------				
				.antMatchers("/dept/monthly1/**").permitAll().antMatchers("/dept/quaterly1/**").permitAll()
				.antMatchers("/dept/yearly1/**").permitAll().antMatchers("/dept/custom1/**").permitAll()

//				-------------for Billing -------------------------		
				.antMatchers("/dept/Billing/**").permitAll().antMatchers("/dept/BillingTillToday/**").permitAll()
//				----------------for role ------------------------------------------------
				.antMatchers("/dept/role/**").permitAll().antMatchers("/dept/tempMap").permitAll()

				// ------------for user management-----
				.antMatchers("/dept/getusername").permitAll().antMatchers("/dept/activity").permitAll()
				.antMatchers("/dept/userroleinsert").permitAll()
//				roleactivityinsert
				.antMatchers("/dept/roleactivityinsert").permitAll()

				// summaryreport
				// ------------for chart--------------
				.antMatchers("/dept/chartfordays/**").permitAll()

				// ------------for Bill generation--------------
				.antMatchers("/dept/billDeptCateg").permitAll().antMatchers("/dept/billDeptCategpdf/**").permitAll()
				.antMatchers("/dept/billDeptCategCsv/**").permitAll()
				.antMatchers("/dept/billAppCategCsv/**").permitAll()
				.antMatchers("/dept/billAppCategpdf/**").permitAll()

				// ---------------for logout-------

				.antMatchers(HttpMethod.GET, "/Logout/**").permitAll()

				// .antMatchers(HttpMethod.GET,"/isSessNull*").permitAll()
				.antMatchers("/isSessNull/**").permitAll().antMatchers("/testName/**").permitAll()
				// .antMatchers(HttpMethod.GET,"/userLogout*").permitAll()
				.antMatchers("/userLogout/**").permitAll()
				// .antMatchers(HttpMethod.POST,"/dept/saveSession/**").permitAll()
				.antMatchers("/dept/saveSession/**").permitAll().antMatchers("/dashboard*").permitAll()
				.antMatchers("/profile*").hasAuthority("UPDATE_PROFILE").antMatchers("/usercreate*")
				.hasAuthority("USER_CREATION").antMatchers("/asa/generatelk*").hasAuthority("GENERATE_SA_LICENSE_KEY")
				.antMatchers("/asa/generateSACode*").hasAuthority("GENERATE_SA_CODE").antMatchers("/asa/updatesalk*")
				.hasAuthority("UPDATE_SA_LICENSE_KEY").antMatchers("/asa/userstatus*").hasAuthority("MANAGE_SA")
				.antMatchers("/asa/suspendaua*").hasAuthority("SUSPEND_SA").antMatchers("/asa/resendactivation*")
				.hasAuthority("RESEND_ACCOUNT_ACTIVATION_LINK").antMatchers("/asa/getasalk*")
				.hasAuthority("UPDATE_AUA_LICENSE_KEY").antMatchers("/asa/getksalk*")
				.hasAuthority("UPDATE_KUA_LICENSE_KEY").antMatchers("/cli/testclient*")
				.hasAuthority("UPDATE_KUA_LICENSE_KEY").antMatchers("/aua/gettransactiondetailpage*")
				.hasAuthority("TRANSACTION_SPECIFIC_REPORT").antMatchers("/reports*")
				.hasAnyAuthority("TRANSACTION_SUMMARY_REPORT_SA", "TRANSACTION_SUMMARY_REPORT_AUA").anyRequest()
				.authenticated().and()
				.addFilterBefore(customUsernamePasswordAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class)
				.formLogin().loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password")
				.and().logout().logoutSuccessUrl("/login?logout").logoutUrl("/logout").deleteCookies("JSESSIONID")
				.permitAll().and().exceptionHandling().accessDeniedPage("/403page");
	}

	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		logger.info("csrfTokenRepository");
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	public CaptchaUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
		logger.info("customUsernamePasswordAuthenticationFilter");
		CaptchaUsernamePasswordAuthenticationFilter captchaUsernamePasswordAuthenticationFilter = new CaptchaUsernamePasswordAuthenticationFilter();
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(getFailureHandler());
		captchaUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(getSuccessHandler());
		return captchaUsernamePasswordAuthenticationFilter;
	}

	@Bean
	public AuthenticationFailureHandler getFailureHandler() {
		logger.info("getFailureHandler");
		SpringAuthFailureHandler handler = new SpringAuthFailureHandler();
		handler.setDefaultFailureUrl("/login?authfailed");
		return handler;
	}

	@Bean
	public AuthenticationSuccessHandler getSuccessHandler() {
		logger.info("getSuccessHandler");
		SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
		handler.setDefaultTargetUrl("/home");
		return handler;
	}

	public AuthenticationProvider authProvider() {
		CustomUserDetailsAuthenticationProvider provider = new CustomUserDetailsAuthenticationProvider(encoder(),
				userDetailsService);
		return provider;
	}

	@Bean
	public PasswordEncoder encoder() {
		logger.info("PasswordEncoder");
		return new BCryptPasswordEncoder();
	}

}
