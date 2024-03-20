<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verify Biometric</title>
<script type="text/javascript"
	src="<c:url value="/resources/wc/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/wc/js/validation.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/wc/js/protocolcheck.js"/>"></script>
	
	<c:url value="/auth/verifyAuthBiometric.do" var="verifyStatus"/>
	
	<c:url value="/auth/authenticateBiometric.do" var="restService"/>
</head>
<c:choose>
<c:when test="${sessionScope.userSessionToken!=null}">
<script src="<c:url value="/resources/wc/js/biometriciris.js"/>"></script>
<script>
	var requiredFieldMsg = '<spring:message code="error.required_field.message"/>';
	var workingMsg = '<spring:message code="user.bio.processing"/>';
	var signOutMsg = '<spring:message code="user.bio.signout"/>';
	var norespDownload = '<spring:message code="user.bio.noresponse.download"/>';
	var downloadComplete = '<spring:message code="user.bio.download.complete"/>';
	var launch = '<spring:message code="user.bio.launch"/>';
	var count = 0;
  	function validate()
    {
  		var env='PP';
  		var authType=document.getElementById("authType").value;
  		console.log(authType);
    	capture(env,authType);
    	
    	document.getElementById("retry").style.display = 'none';
    	document.getElementById("scan").style.display = 'block';
    	
    }
  	
  	 function onSuccessCapture(response)
     {
     	document.getElementById("biometricResponse").value=response;
    	document.getElementById("bioauth").action ="../auth/authenticateBiometric.do";
    	document.getElementById("bioauth").submit();
     }

     function onFailureCapture(response)
     {
     	var val = new String(response);
     	if(val.indexOf("Capture Failed / TimeOut")!=-1)
     		document.getElementById("errormessages").innerHTML ="Capture Failed. retry for biometric authentication.";
     	else
     		document.getElementById("errormessages").innerHTML =val;
    	document.getElementById("scan").style.display = 'none';
     	document.getElementById("retry").style.display = 'block';
     }

</script>
<c:url value="/auth/verifyBiometric.do" var="action"/>
</c:when>
<c:otherwise>
<c:url value="/auth/loadUserBiometric.do" var="action"/>
</c:otherwise>
</c:choose>
<body onload="validate();">
	
	<h2 align="center"><spring:message code="user.bio.message" /></h2>
	
	<!-- action="${action}" modelAttribute="userAuthForm" -->
	<form:form method="POST" action="${action}" modelAttribute="userAuthForm"
		 autocomplete="off" id="bioauth">
	<input type="hidden" name="serviceId" value="${fn:escapeXml(requestScope.serviceId)}"/>
	<input type="hidden" name="requestId" id="requestId" value="${fn:escapeXml(requestScope.requestId)}"/>  
	<input type="hidden" name="samlRequestId" value="${fn:escapeXml(requestScope.samlRequestId)}"/>
	<input type="hidden" name="actionName" value="${action}"/>
	<input type="hidden" name="biometricResponse" value="" id="biometricResponse"/>
	<input type="hidden" name="aadhaar" value="${fn:escapeXml(sessionScope.userSessionToken.aadhaarNumber)}" id="aadhaar"/>
	<input type="hidden" id="authType" name="authType" value="${authType}"/>
	
		<form:hidden path="authSubLevel.subLevelId"
			value="${fn:escapeXml(authSubLevel.subLevelId)}" />
		<form:hidden path="authSubLevel.subLevelName"
			value="${fn:escapeXml(authSubLevel.subLevelName)}" />
		<table class="outerTable">

			<tr>
				<td>

					<table class="infoTable">

						<tr>
							<td><form:errors cssClass="dispfailuremsg"
									element="div" /> 
						</tr>
						<c:choose>
							<c:when
								test="${sessionScope.userSessionToken==null}">
								<tr>
									<td width="40%" align="right"><spring:message
											code="user.username" />&nbsp; /&nbsp;<spring:message
											code="user.aadhaar" /></td>
									<td width="60%"><form:input path="username"
											id="user.username" autocomplete="off" />&nbsp;<form:errors path="username"
											cssClass="error" /></td>
											<form:hidden path="noUserInSession" value="true"/>
								</tr>
								
								<tr>
							<td align="right"><spring:message code="user.captcha" /></td>
								
							<td><form:input path="j_captcha_response" />
								<form:errors path="j_captcha_response" cssClass="error" /></td>
							
						</tr>
						
						
								<tr>
								
								<tr><td></td><td><span class="dispmsg"><spring:message
										code="user.captcha.msg" /></span></td></tr>	
						<tr>
							<td></td>
							<td><div>
							<spring:url value="/other/captcha.do"
									var="loadCaptchaAction" />
							<img id="captchaImage" src="${loadCaptchaAction}">&nbsp;&nbsp;<div class="refreshCaptcha"  onclick="reloadCaptcha(this)"></div></div></td>
						
						</tr>
						<td></td>
							<td><input type="submit" value=" Submit "
								name="bioSubmit" /></td>
						</tr>
							</c:when>
							<c:otherwise>
								<form:hidden path="username" value="${userAuthForm.username}" />
							
						<tr>
							<!-- width="40%" align="left" colspan="2" -->
						<tr id="displayMsg"><td width="40%" align="left" colspan="2">
							<div id="scan">
								
						    	<div align="center"> 
						    	<h2 id="msg"><span id="messages"><font size='3px'><%-- <spring:message	code="user.bio.clientdetect.message" /> --%> </font></span></h2>
						    	<img id="showMsg"  alt="/resources/wc/images/36.gif" src="/resources/wc/images/36.gif" ></div>
						    
					    	</div>
							<div id="retry">
								 	
								 	<div class="row">
								 	<div class="col-sm-4"></div>
						    		<div class="col-sm-4"><h2 id="msg"><span id="errormessages">  <font size='3px'> <%-- <spring:message	code="user.bio.clientdetect.message" /> --%> </font></span></h2></div>
						    		<div class="col-sm-4"></div>
						    		</div>
						    		<div class="row">
						    		<div class="col-sm-4"></div>
						    		<div class="col-sm-2">
						    			<input   type="button" value='<spring:message	code="user.bio.tryagain.btn" />' id="retrybtn" name="retry" onclick="validate();" class="btn btn-primary btn-block" style="padding: 8px 8px; letter-spacing: 1px; height:38px;margin: 0 auto; "/>
						    		</div>
						    		<div class="col-sm-2">
						    		<input   type="button" value='<spring:message	code="user.bio.cancel.btn" />' id="cancelbtn" name="cancel" onclick="location.href='/spServices/loadServices.do';" class="btn btn-default btn-block" style="padding: 8px 8px; letter-spacing: 1px; height:38px;margin: 0 auto; "/>
						    		</div>
						    		<div class="col-sm-4"></div>
						    		</div>
						    </div>
						    	 <%-- <iframe style="display: none;" src='epramaanbio://${fn:escapeXml(pageContext.request.serverName)}:4002${fn:escapeXml(restService)}?aadhaarNo=${fn:escapeXml(sessionScope.userSessionToken.aadhaarNumber)}&requestId=${fn:escapeXml(requestScope.requestId)}'></iframe> --%> 
						    
					    	
							</td>
							
						</tr>
    	 			</c:otherwise>
						</c:choose>
					</table>
				</td>
			</tr>
		</table>

	</form:form>


		<script type="text/javascript">
	 $(document).ready(function () {
            $("#troubleshoot").click(function (e) {
				//alert("On click button ");	
                ShowDialog(true);
                e.preventDefault();
            });
  
            $("#cancleFeedbackBtn").click(function (e) {
                HideDialog();
                e.preventDefault();
            });  
		  $("#btnClose").click(function (e) {
						HideDialog();
						e.preventDefault();
					}); 
        }); 
        function ShowDialog(modal) {
            $("#overlay").show();
            $("#dialog").fadeIn(300);
  
            if (modal) {
                $("#overlay").unbind("click");
            }
            else {
                $("#overlay").click(function (e) {
                    HideDialog();  
                });
            }
        } 
        function HideDialog() {
            $("#overlay").hide();
            $("#dialog").fadeOut(300);
        }      
    </script>  
</body>
</html>