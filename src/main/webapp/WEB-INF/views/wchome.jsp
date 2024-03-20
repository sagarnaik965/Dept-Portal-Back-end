
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="page-wrapper" style="padding-top: 10px;">
<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading text-center"
						style="padding-top: 5px; padding-bottom: 5px;">
						<h4>Online Web Client</h4>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-8">
																	<!-- Reloder -->
<div id="preloader">
	<div id="status"></div>
</div>


<!-- Page Content -->
<div class="container" id="online">
	<input type="hidden" id="firstDropDownJsp"
		value="${firstDropDownValueController}"> 
		<input type="hidden"
		id="secondDropDownJsp" value="${secondDropDownValueController}">
	<input type="hidden" id="radioValue" value="${radioValue}"> <input
		type="hidden" id="showotptextbox" value="${otpauthmsg}">


	<!-- Hidden fields for .properties file -->

	<%-- 	<h1>${propMap.get("af_env")}</h1> --%>
	<!-- 	Auth biometric -->
	<input type="hidden" id="af_env" value="${propMap.get('af_env')}">
	<input type="hidden" id="af_biometricType"
		value="${propMap.get('af_biometricType')}"> <input
		type="hidden" id="af_fcount" value="${propMap.get('af_fcount')}">
	<input type="hidden" id="af_biometricPosition"
		value="${propMap.get('af_biometricPosition')}">


	<!-- 	Auth IRIS -->
	<input type="hidden" id="ai_env" value="${propMap.get('ai_env')}">
	<input type="hidden" id="ai_biometricType"
		value="${propMap.get('ai_biometricType')}"> <input
		type="hidden" id="ai_fcount" value="${propMap.get('ai_fcount')}">
	<input type="hidden" id="ai_biometricPosition"
		value="${propMap.get('ai_biometricPosition')}">


	<!-- 	Auth Face -->
	<input type="hidden" id="aface_env" value="${propMap.get('aface_env')}">
	<input type="hidden" id="aface_biometricType"
		value="${propMap.get('aface_biometricType')}"> <input
		type="hidden" id="aface_fcount" value="${propMap.get('aface_fcount')}">
	<input type="hidden" id="aface_biometricPosition"
		value="${propMap.get('aface_biometricPosition')}">


	<!-- 	KYC Finger -->
	<input type="hidden" id="kf_env" value="${propMap.get('kf_env')}">
	<input type="hidden" id="kf_ver" value="${propMap.get('kf_ver')}">
	<input type="hidden" id="kf_type" value="${propMap.get('kf_type')}">
	<input type="hidden" id="kf_rc" value="${propMap.get('kf_rc')}">
	<input type="hidden" id="kf_lr" value="${propMap.get('kf_lr')}">
	<input type="hidden" id="kf_de" value="${propMap.get('kf_de')}">
	<input type="hidden" id="kf_pfr" value="${propMap.get('kf_pfr')}">
	<input type="hidden" id="kf_biometricType"
		value="${propMap.get('kf_biometricType')}"> <input
		type="hidden" id="kf_count" value="${propMap.get('kf_count')}">
	<input type="hidden" id="kf_biometric_positions"
		value="${propMap.get('kf_biometric_positions')}">

	<!-- 	KYC IRIS -->
	<input type="hidden" id="ki_env" value="${propMap.get('ki_env')}">
	<input type="hidden" id="ki_ver" value="${propMap.get('ki_ver')}">
	<input type="hidden" id="ki_type" value="${propMap.get('ki_type')}">
	<input type="hidden" id="ki_rc" value="${propMap.get('ki_rc')}">
	<input type="hidden" id="ki_lr" value="${propMap.get('ki_lr')}">
	<input type="hidden" id="ki_de" value="${propMap.get('ki_de')}">
	<input type="hidden" id="ki_pfr" value="${propMap.get('ki_pfr')}">
	<input type="hidden" id="ki_biometricType"
		value="${propMap.get('ki_biometricType')}"> <input
		type="hidden" id="ki_count" value="${propMap.get('kf_count')}">
	<input type="hidden" id="ki_biometric_positions"
		value="${propMap.get('ki_biometric_positions')}">

	<!-- 	KYC FACE -->
	<input type="hidden" id="kface_env" value="${propMap.get('kface_env')}">
	<input type="hidden" id="kface_ver" value="${propMap.get('kface_ver')}">
	<input type="hidden" id="kface_type"
		value="${propMap.get('kface_type')}"> <input type="hidden"
		id="kface_rc" value="${propMap.get('kface_rc')}"> <input
		type="hidden" id="kface_lr" value="${propMap.get('kface_lr')}">
	<input type="hidden" id="kface_de" value="${propMap.get('kface_de')}">
	<input type="hidden" id="kface_pfr" value="${propMap.get('kface_pfr')}">
	<input type="hidden" id="kface_biometricType"
		value="${propMap.get('kface_biometricType')}"> <input
		type="hidden" id="kface_count" value="${propMap.get('kface_count')}">
	<input type="hidden" id="kface_biometric_positions"
		value="${propMap.get('kface_biometric_positions')}">


	<div class="row">
		<!-- Blog Entries Column -->
		<!-- Left side -->


		<div class="col-md-6">
			<!-- Blog Post -->
			<div class="panel panel-default">
				<!-- ONLINE OFFLINE RADIO -->
				<div class="panel-heading text-center" id="radiobtn">
					<span class="radio">
					<div class="row">
					<div class="col-md-6"> 
					<input type="radio" id="authradio"
						value="AUTHENTICATION" name="radio-group" checked="checked"
						onclick="showauth();" > <label for="test1">AUTHENTICATION</label> </div>
						<div class="col-md-6" style="text-align: initial;">
						<input type="radio" id="kycradioi" value="KYC" name="radio-group"
						onclick="showkyc();"> <label for="test2">KYC</label></div>
						</div>
					</span>
				</div>

				<!-- Online Drop down -->
				<div class="panel-body dropdown"
					style="padding-top: 7px; padding-bottom: 5px;">


					<div class="dropdown col-xs-12 ">
						<div class="panel-body mb-3 dropdownonline" id="authdropdown">
							<div class="dropdown col-xs-12" class="firstDropdown"
								id="firstDropdown" style="padding-bottom: 12px;">
								<select class="form-control" name="DemoOtpBio" id="DemoOtpBio"
									onchange="changecatOnline(this.value); categoryChangeAuth();">
									<option value="DEMOGRAPHIC" selected>DEMOGRAPHIC</option>
									<option value="OTP">OTP</option>
									<option value="BIOMETRIC">BIOMETRIC</option>
								</select>
							</div>
							<div class="dropdown col-xs-12">
								<select class="form-control categoryOnline"
									name="categoryOnline" id="categoryOnline"
									onchange="changecatsubOnline(this.value); ">
									<option value="PERSONAL INFORMATION" selected id="pipapfa">PERSONAL INFORMATION</option>
									<option value="PERSONAL ADDRESS" id="pipapfa">PERSONAL ADDRESS</option>
									<option value="PERSONAL FULL ADDRESS" id="pipapfa">PERSONAL FULL ADDRESS</option>
								</select>
							</div>
						</div>

						<div class="panel-body mb-3 dropdownonline" id="kycdropdown">
							<div class="dropdown col-xs-6" class="firstDropdown"
								id="firstDropdown">
								<select class="form-control" name="DemoOtpBio" id="BioOtp"
									onchange="changecatOnlinek(this.value);  categoryChangeKyc(); ">
									<option value="OTP" selected>OTP</option>
									<option value="BIOMETRIC">BIOMETRIC</option>
								</select>
							</div>
							<div class="dropdown col-xs-6" id="kycfif">
								<select class="form-control categoryOnlinek"
									name="categoryOnlinek" id="categoryOnlinek"
									onchange="changecatsubOnlinek(this.value); categoryChangeOnlinek();">
									<option value="FINGERPRINT" selected>FINGERPRINT</option>
									<option value="IRIS">IRIS</option> 
									 <option value="FACE">FACE</option> 
								</select>
							</div>
						</div>
					</div>
					<!-- OnlineDropbown -->
				</div>
				<!-- OnlineDropbown -->
			</div>
			<div class="blockdemoauthpipapfa" id="blockdemoauthpipapfa">

				<!-- Personal Information -->
				<div class="panel panel-default" id="personalInformation">
					<p class="panel-heading text-center">
						<strong>PERSONAL INFORMATION</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<c:url value="/client/authpirequest" var="authPiUrl" />
							<form class="text-center border border-light p-6"
								action="${authPiUrl }" method="post" autocomplete="off">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<!-- Name -->
								<input type="text" name="aadharnumber" id="aadharnumberPI" 
									class="form-control mb-2 aa" 
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');" required
									onchange="aadhaar_vid_content(this.value);">

								<!-- Name -->
								<input type="name" name="name" id="name"
									class="form-control mb-2" placeholder="Name" required>

								<!-- Sign in button -->
								<button class="btn btn-primary btn-block my-2" type="submit"
									id="submitbtnPI">SUBMIT</button>

							</form>
						</div>
					</div>
				</div>
				<!-- Personal Information End -->

				<!-- 				Personal Address -->
				<div class="panel panel-default" id="personalAddress">

					<p class="panel-heading text-center">
						<strong>PERSONAL ADDRESS</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<c:url value="/client/authparequest" var="authPAUrl" />
							<form class="text-center border border-light p-6"
								action="${authPAUrl }" method="post" autocomplete="off">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="text" name="aadharnumber" id="aadharnumberPA"
									class="form-control mb-2 aa"
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');" required
									onchange="aadhaar_vid_content(this.value);"> <input
									type="address" id="addressDist" name="addressDist"
									placeholder="District Name" class="form-control mb-4"
									required="required" />

								<!-- Sign in button -->
								<button class="btn btn-primary btn-block my-2" type="submit"
									id="submitbtnPA" onclick="showpadata();">SUBMIT</button>
							</form>
						</div>
					</div>
				</div>
				<!-- Personal Addresss End -->

				<!-- 				Personal Full Address -->
				<div class="panel panel-default" id="personalFullAddress">
					<p class="panel-heading text-center">
						<strong>PERSONAL FULL ADDRESS</strong>
					</p>


					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
						<c:url value="/client/authpfarequest" var="authPFAUrl" />
							<form class="text-center border border-light p-6"
								action="${authPFAUrl }" method="post" autocomplete="off">

								<!-- 					<p class="h4 mb-4">Personal Information</p> -->


								<!-- Name -->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<input type="text" name="aadharnumber" id="aadharnumberPFA"
									class="form-control mb-2 aa"
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');" required
									onchange="aadhaar_vid_content(this.value);">

								<textarea type="address" id="addressDist" name="addressDist"
									placeholder="Full Address" class="form-control mb-4"></textarea>
								<!-- Sign in button -->
								<button class="btn btn-primary btn-block my-2" type="submit"
									id="submitbtnPFA">SUBMIT</button>

							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- PIPAPFA END -->

			<div class="otp" id="otp">
				<!-- Demo auth OTP  -->
				<div class="panel panel-default" id="otprequest" class="otprequest">
					<p class="panel-heading text-center">
						<strong>OTP AUTHENTICATION</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
						<c:url value="/client/otprequest" var="authOTPUrl" />
							<form class="text-center border border-light p-6"
								action="${authOTPUrl }" method="post" autocomplete="off">
								<div id='sign-in' class='login-setup-section'>
									<div class="form-group login-label">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<input type="text" name="aadharnumber" id="aadharnumberOtp"
											class="form-control mb-2 aa"
											placeholder="Aadhaar Number / Virtual ID" maxlength="16"
											oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											required onchange="aadhaar_vid_content(this.value);">
									</div>
									<button type="submit"
										class="btn btn-primary btn-block my-2 request-otp"
										id="submitbtnOtp">Get OTP</button>
								</div>
							</form>

						</div>
					</div>
				</div>

				<!-- AUTH OTP  -->
				<div class="panel panel-default" id="authotp">
					<p class="panel-heading text-center">
						<strong>OTP AUTHENTICATION</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<c:url value="/client/otpauth" var="otpauth" />
							<form class="text-center border border-light p-6"
							      action="${otpauth}" method="post">
								<div id='sign-in' class='login-setup-section'>
									<div class="form-group login-label">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
										<input type="text" name="pinvalue" id="otpvalue"
											class="form-control mb-2" placeholder="Enter OTP"
											maxlength="6" pattern="[0-9]{6}"
											oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											required onchange="validate_otp_input(this.value);">
									</div>
									<button type="submit"
										class="btn btn-primary btn-block my-2 request-otp"
										id='request-otp'>SUBMIT</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- AUTH OTP End -->

				<!-- KYC OTP Request  -->
				<div class="panel panel-default" id="otpkycrequest">
					<p class="panel-heading text-center">
						<strong>OTP KYC</strong>
					</p>



					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
						<c:url value="/client/otpkycrequest" var="KYC_OTPUrl" />
							<form class="text-center border border-light p-6"
								action="${KYC_OTPUrl }" method="post" autocomplete="off">
								<div id='sign-in' class='login-setup-section'>
									<div class="form-group login-label">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<input type="text" name="aadharnumber" id="aadharnumberKycOtp"
											class="form-control mb-2 aa"
											placeholder="Aadhaar Number / Virtual ID" maxlength="16"
											oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											required onchange="aadhaar_vid_content(this.value);">
									</div>
									<button type="submit"
										class="btn btn-primary btn-block my-2 submitbtnKycOtp"
										id="submitbtnKycOtp">Get OTP</button>
								</div>
							</form>

						</div>
					</div>
				</div>
				<!-- KYC OTP End -->


				<!-- kyc OTP  -->
				<div class="panel panel-default" id="kycotp">
					<p class="panel-heading text-center">
						<strong>OTP KYC</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							 <c:url value="/client/otpkyc" var="otpkyc" />
							 <form class="text-center border border-light p-6" action="${otpkyc}"
								method="post">
								<div id='sign-in' class='login-setup-section'>
									<div class="form-group login-label">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<input type="text" name="pinvalue" id="pinvalueotpkyc"
											class="form-control mb-2" placeholder="Enter OTP"
											maxlength="6"
											oninput="this.value=this.value.replace(/[^0-9]/g,'');"
											required onchange="validate_otp_input(this.value);">
									</div>
									<button type="submit"
										class="btn btn-primary btn-block my-2 btnotpkyc" id='btnotpkyc'>SUBMIT</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- KYC OTP End -->

			</div>
			<!-- 			OTP END -->

			<div class="biometric" id="biometric">
				<!-- Finger print authentication -->
				<div class="panel panel-default" id="fingerauth">
					<p class="panel-heading text-center">
						<strong>FINGERPRINT AUTHENTICATION</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<c:url value="/client/biometric" var="authfingerprint" />
							<form class="text-center border border-light p-6"
							      action="${authfingerprint}" method="post" id="authf" autocomplete="off">


								<img src="<c:url value="/resources/wc/images/fingerpirnt.gif" />"
									alt="fingerprint" height="110px">
								<!-- Sign in button -->
									 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									 <input type="hidden" name="aadharnumber" value="" id="uid2" />
									 <input type="hidden" name="catagory" value="" id="cat2" />
									<input type="text" name="aadharnumber" id="anauthfin"
									class="form-control mb-2 aa"
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');"
									onchange="aadhaar_vid_content(this.value);" required="required" />

								<input type="hidden" name="biometricResponse" 
									id="biometricResponse" />
					<button class="btn btn-primary btn-block my-2" type="submit"
						id="btnauthfin" onclick="return validateAuthFingerprint(); ">CAPTURE</button>
							</form>
						</div>
					</div>
				</div>

				<!-- Finger print authentication End -->

				<!-- IRIS authentication -->
				<div class="panel panel-default" id="irisauth">
					<p class="panel-heading text-center">
						<strong>IRIS AUTHENTICATION</strong>
					</p>


					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<form class="text-center border border-light p-6"
								action="authiris" method="post" autocomplete="off"> 


								<img src="<c:url value="/resources/wc/images/eye.gif" />"
									alt="iris" height="110px"> <input type="hidden"
									name="biometricResponse" value="" id="biometricResponse" /> <input
									type="text" name="aadharnumber" id="anauthiris"
									class="form-control mb-2 aa"
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');"
									onchange="aadhaar_vid_content(this.value);" required="required" />
								<!-- Sign in button -->
								<button class="btn btn-primary btn-block my-2" type="submit"
									id="btnauthiris" onclick="return validateAuthIris();">CAPTURE</button>

							</form>
						</div>
					</div>
				</div>
				<!-- IRIS authentication End -->

				<!-- FACE authentication -->
				<div class="panel panel-default" id="faceauth">
					<p class="panel-heading text-center">
						<strong>FACE AUTHENTICATION</strong>
					</p>
					<div class="panel-body namefieldtextbox"
						style="padding-top: 7px; padding-bottom: 5px;">
						<div class="containerinfo">
							<form class="text-center border border-light p-6"
								action="authface" method="post" autocomplete="off">


								<img src="<c:url value="/resources/wc/images/face.gif" />"
									alt="fingerprint" height="110px">
								<!-- Sign in button -->

								<input type="text" name="aadharnumber" id="anauthface"
									class="form-control mb-2 aa"
									placeholder="Aadhaar Number / Virtual ID" maxlength="16"
									oninput="this.value=this.value.replace(/[^0-9]/g,'');" required
									onchange="aadhaar_vid_content(this.value);"> <input
									type="hidden" name="biometricResponse" value=""
									id="biometricResponse" />

								<button class="btn btn-primary btn-block my-2" type="submit"
									id="btnauthface" id="btnauthface"
									onclick="return validateAuthFace();">CAPTURE</button>

							</form>
						</div>
					</div>
				</div>
				<!-- FACE authentication End -->




				<div class="blockbiometrickyc" id="blockbiometrickyc">
					<!-- Finger print authentication -->
					<div class="panel panel-default" id="fingerkyc">
						<p class="panel-heading text-center">
							<strong>FINGERPRINT KYC</strong>
						</p>


						<div class="panel-body namefieldtextbox"
							style="padding-top: 7px; padding-bottom: 5px;">
							<div class="containerinfo">
								<c:url value="/client/biometric" var="kycfingerprint" />
								<form class="text-center border border-light p-6"
								      action="${kycfingerprint}" method="post" autocomplete="off">


									<img src="<c:url value="/resources/wc/images/fingerpirnt.gif" />"
										alt="fingerprint" height="110px">
									<!-- Sign in button -->
										 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										 <input type="hidden" name="aadharnumber" value="" id="uid2" />
										 <input type="hidden" name="catagory" value="" id="cat2" />
										<input type="text" name="aadharnumber" id="ankycfin"
										class="form-control mb-2 aa"
										placeholder="Aadhaar Number / Virtual ID" maxlength="16"
										oninput="this.value=this.value.replace(/[^0-9]/g,'');"
										onchange="aadhaar_vid_content(this.value);"
										required="required"> <input type="hidden"
										name="biometricResponse" value="" id="biometricResponse" />

									<button class="btn btn-primary btn-block my-2" type="submit"
										id="btnkycfin" onclick="return validateKycFinger();">CAPTURE
										FINGERPRINT</button>

								</form>
							</div>
						</div>
					</div>
					<!-- Finger print authentication End -->

					<!-- IRIS authentication -->
					<div class="panel panel-default" id="iriskyc">
						<p class="panel-heading text-center">
							<strong>IRIS KYC</strong>
						</p>


						<div class="panel-body namefieldtextbox"
							style="padding-top: 7px; padding-bottom: 5px;">
							<div class="containerinfo">
								<form class="text-center border border-light p-6"
									action="kyciris" method="post" autocomplete="off">


									<img src="<c:url value="/resources/wc/images/eye.gif" />"
										alt="fingerprint" height="110px">
									<!-- Sign in button -->


									<input type="text" name="aadharnumber" id="ankyciris"
										class="form-control mb-2 aa"
										placeholder="Aadhaar Number / Virtual ID" maxlength="16"
										oninput="this.value=this.value.replace(/[^0-9]/g,'');"
										onchange="aadhaar_vid_content(this.value);"
										required="required"> <input type="hidden"
										name="biometricResponse" value="" id="biometricResponse" />

									<button class="btn btn-primary btn-block my-2" type="submit"
										id="btnkyciris" onclick="return validateKycIris();">CAPTURE
										IRIS</button>

								</form>
							</div>
						</div>
					</div>
					<!-- IRIS authentication End -->

					<!-- FACE authentication -->
					<div class="panel panel-default" id="facekyc">
						<p class="panel-heading text-center">
							<strong>FACE KYC</strong>
						</p>
						<div class="panel-body namefieldtextbox"
							style="padding-top: 7px; padding-bottom: 5px;">
							<div class="containerinfo">
								<form class="text-center border border-light p-6"
									action="kycface" method="post" autocomplete="off">


									<img src="<c:url value="/resources/wc/images/face.gif" />"
										alt="fingerprint" height="110px"> <input type="text"
										name="aadharnumber" id="ankycface" class="form-control mb-2 aa"
										placeholder="Aadhaar Number / Virtual ID" maxlength="16"
										oninput="this.value=this.value.replace(/[^0-9]/g,'');"
										onchange="aadhaar_vid_content(this.value);"
										required="required"> <input type="hidden"
										name="biometricResponse" value="" id="biometricResponse" />

									<!-- Sign in button -->
									<button class="btn btn-primary btn-block my-2" type="submit"
										id="btnkycface" onclick="return validateKycFace();">CAPTURE
										FACE</button>

								</form>
							</div>
						</div>
					</div>
					<!-- FACE authentication End -->
				</div>

			</div>
			<!-- Biometric auth End -->


		</div>
		<!-- Sidebar Widgets Column -->
		<!-- Right side -->
		<div class="col-md-6" id="righthalf" class="righthalf">
			<div id="result" class="result">
				<c:if test="${not empty demoauth.getStatusMessage()}">
					<div class="panel panel-default tp-1" id="resultdiv">
						<h6 class="panel-heading text-center">
							<strong> ${secondDropDownValueController} RESULT </strong>
							<c:if test="${not empty demoauth.getStatusMessage()}">
								<c:if test="${not empty demoauth.getStatusMessage()}">
									<c:if test="${demoauth.getStatusMessage() == 'Success'}">
										<i class="fa fa-check-circle" aria-hidden="true"></i>
									</c:if>
									<c:if test="${demoauth.getStatusMessage() == 'Failure'}">
										<i class="fa fa-times-circle" aria-hidden="true"></i>
									</c:if>
								</c:if>
							</c:if>
						</h6>
						<div class="panel-body common">
							<c:if test="${not empty demoauth.getBase64Image()}">
								<img src="data:image/jpeg;base64,${demoauth.getBase64Image()}"
									style="height: 100px;" />
							</c:if>
							<c:if test="${not empty demoauth.getMaskedAadhaarNumber()}">
								<p id="result">Aadhaar Number :
									${demoauth.getMaskedAadhaarNumber()}</p>
							</c:if>
							<c:if test="${not empty demoauth.getMaskedVirtualID()}">
								<p id="result">Virtual ID : ${demoauth.getMaskedVirtualID()}</p>
							</c:if>
							<!-- otp message -->
							<c:if test="${not empty otpauthmsg}">
								<p id="otpmsg">${otpauthmsg}</p>
							</c:if>



							<c:if test="${not empty demoauth.getAadhaarHolderName()}">
								<p id="result">Name : ${demoauth.getAadhaarHolderName()}</p>
							</c:if>


							<c:if test="${not empty demoauth.getGender()}">
								<p id="result">Gender: ${demoauth.getGender() }</p>
							</c:if>

							<c:if test="${not empty demoauth.getDob()}">
								<p id="result">Dob: ${demoauth.getDob()}</p>
							</c:if>

							<!-- Landmark  -->
							<c:if test="${not empty demoauth.getLm()}">
								<p id="result">LM: ${demoauth.getLm()}</p>
							</c:if>

							<!-- LOC -->
							<c:if test="${not empty demoauth.getLoc()}">
								<p id="result">Loc: ${demoauth.getLoc()}</p>
							</c:if>

							<!-- House -->
							<c:if test="${not empty demoauth.getHouse()}">
								<p id="result">House: ${demoauth.getHouse()}</p>
							</c:if>

							<!-- Street -->
							<c:if test="${not empty demoauth.getSubStreet()}">
								<p id="result">Street: ${demoauth.getSubStreet()}</p>
							</c:if>

							<c:if test="${not empty demoauth.getVtc()}">
								<p id="result">Village/Town/City: ${demoauth.getVtc()}</p>
							</c:if>

							<!-- Post office -->
							<c:if test="${not empty demoauth.getPo()}">
								<p id="result">Post Office: ${demoauth.getPo()}</p>
							</c:if>

							<c:if test="${not empty demoauth.getSubDist()}">
								<p id="result">Subdist: ${demoauth.getSubDist()}</p>
							</c:if>

							<c:if test="${not empty demoauth.getDist()}">
								<p id="result">Dist: ${demoauth.getDist()}</p>
							</c:if>


							<c:if test="${not empty demoauth.getPersonalFullAddress()}">
								<p id="result">Address: ${demoauth.getPersonalFullAddress()}</p>
							</c:if>


							<c:if test="${not empty demoauth.getErrorCode()}">
								<p id="result">Error Code : ${demoauth.getErrorCode()}</p>
							</c:if>
							<c:if test="${not empty demoauth.getErrorMessage()}">
								<p id="result">Error Message : ${demoauth.getErrorMessage()}</p>
							</c:if>
							<c:if test="${empty otpauthmsg}">

								<c:if test="${not empty demoauth.getTransactionID()}">
									<p id="result">Transaction Id :
										${demoauth.getTransactionID()}</p>
								</c:if>

								<c:if test="${not empty demoauth.getTimeStamp()}">
									<p id="result">Time Stamp : ${demoauth.getTimeStamp()}</p>

								</c:if>
							</c:if>
						</div>

					</div>
				</c:if>

			</div>
			<!-- 			result end -->

			<br>
			<div class="alert alert-warning messages" id="messages"></div>
			<div class="alert alert-warning messages" id="errormessages"></div>

		</div>


	</div>
</div>
<div class="container" id="offlinesection">
	<br>
	<h2>OFFLINE DATA</h2>
</div>
							
							</div>
						</div>
					</div>
					
				</div>
			</div>
	</div> 


</div> -->




<!-- /.row -->
<!-- /.container -->
<!-- jQuery -->
<c:url value="/resources/" var="resources" />
<script src="${resources}vendor/jquery/jquery.min.js"></script> 
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script> 
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
 <script src="${resources}dist/js/sb-admin-2.js"></script>
<c:url value="/resources/wc/js/default.js" var="default1" />
<script src="${default1 }"></script>
<c:url value="/resources/wc/js/biometric.js" var="biometric" />
<script src="${biometric }"></script> 
  <%@ include file="footer.jsp"%> 
