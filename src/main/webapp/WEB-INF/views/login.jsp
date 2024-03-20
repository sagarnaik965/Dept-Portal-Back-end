<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="expires" content="0">
<meta http-equiv="expires" content="Tue, 14 Mar 2018 12:45:26 GMT">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<title>Aadhaar Data vault</title>

<!-- Bootstrap Core CSS -->
<link href="resources/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="resources/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="resources/css/login.css" rel="stylesheet">

<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<link rel="shortcut icon" type="image/x-icon"
	href="resources/images/advlogo2.ico" />

</head>

<body>
	<c:url value="/resources/images/cdac.jpg" var="ceglogo" />
	<div id="wrapper">

		<br>
		<br /> <br>
		<br />

		<%-- <div>
			<%@ include file="header.jsp"%>
			
		</div> --%>


		<%-- 	<div id="header" style="background-color: white;">
			<nav class="navbar navbar-static-top ep-main-header"
				style="border: 0px; background-color: #fefefe; min-height: 60px"
				role="navigation">
				<div class="container-fluid">
					<div class="navbar-header pull-left">
						<a class="navbar-brand" href="#"> <img src="${ceglogo}"
							alt="CeG Logo" style="height: 60px;">
						</a>
					</div>
				</div>
			</nav>
			<div class="container-fluid"
				style="padding-left: 0px; padding-right: 0px;">
				<hr
					style="display: block; border: 0; border-top: 5px solid #3e76b9; padding: 0; margin-top: 5px; margin-bottom: 5px;">
			</div>
		</div>
 --%>








		<div class="container" id="main">
			<div class="row">
				<!-- <div>
					<a href='/'>Back</a>
				</div> -->
				<div class="col-md-6 col-md-offset-3">
					<c:choose>
						<c:when test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert"
								style="margin-top: 3%;">${errorMessage}</div>
							<div class=" panel panel-default " style="margin-bottom: 10%;">
						</c:when>
						<c:when test="${not empty successMessage}">
							<div class="alert alert-success" role="alert"
								style="margin-top: 3%;">${successMessage}</div>
							<div class=" panel panel-default" style="margin-bottom: 10%;">
						</c:when>
						<c:otherwise>
							<div class="panel panel-default"
								style="margin-top: 3%; margin-bottom: 15%;">
						</c:otherwise>
					</c:choose>
					<div class="panel-heading">
						<h3 class="panel-title">Time Out. . </h3>
					</div>
					<div class="panel-body">
<%-- 
						<form role="form" method="POST" action="login" name="loginform"
							id="loginform">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<fieldset>
								<div class="form-group">
									<input type="text" name="username" id="username"
										autocomplete="on" placeholder="Username" maxlength="25"
										class="form-control" />
								</div>

								<div class="form-group">
									<input type="password" name="password" maxlength="25"
										autocomplete="on" id="password" class="form-control"
										placeholder="Password" />
								</div>
							<!-- 	<div class="form-group">
									<select class="form-control" id="tenant" name="tenant">
										<option value="test">Test Environment</option>
										<option value="preproduction">Pre Production
											Environment</option>
										<option value="production">Production Environment</option>
									</select>
								</div> -->


								<div class="form-group">
									<img id="captcha_id" alt="Captcha" src="captcha.png" /> <a
										href="#"
										onclick="document.getElementById('captcha_id').src='captcha.png?'+(new Date().getTime());">Refresh
										Captcha</a> <br> <input type="text" name="captcha"
										id="captcha" class="form-control" maxlength="25"
										placeholder="Captcha" style="padding-top: 5px;" />
									<p class="help-block">Enter text shown in the image above</p>
								</div>
								<input type="submit" value="Login" />
								<br></br>
								
								<!-- <a
										     href='<c:url value ="/forgetpassword"/>'>Forgot Password?</a> -->
							</fieldset>
						</form> --%>
						<form action="http://advservice.epramaan.gov.in/department/Demo">
								
    <input type="submit" value="Login Again" />
</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/vendor/jquery/jquery.min.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/vendor/metisMenu/metisMenu.min.js"></script>
	<script src="resources/dist/js/sb-admin-2.js"></script>
	<script src="resources/vendor/jquery/jquery.validate.min.js"></script>
	<script src="resources/js/login.js"></script>
	<%-- <%@ include file="footer.jsp"%> --%>