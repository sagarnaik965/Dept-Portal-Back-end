<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<!-- saved from url=(0092)/resources/index.html# -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- <meta http-equiv="refresh" content="30" />  --> 
<link rel="shortcut icon" href="#" /> 
<title>OFFLINE DEMO</title>
<link
	href="<c:url value="/resources/wc/vendor/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/wc/fonts/Lato-Bold.ttf" />"
	rel="stylesheet">
<link href="<c:url value="/resources/wc/css/offline-home.css" />"
	rel="stylesheet">

</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="offlinehome"><strong> OFFLINE DEMO </strong></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="true" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="navbar-collapse collapse show" id="navbarResponsive"
				style="">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="/resources/index.html#">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/resources/index.html#">About</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/resources/index.html#">Services</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/resources/index.html#">Contact</a></li>
				</ul>


			</div>

			<div class="switch" id="onlineOffline" class="onlineOffline">
				<input type="radio" class="switch-input" name="view" value="week"
					id="week" onClick="location.href='/webclient/home'"/> <label
					for="week" class="switch-label switch-label-off"><strong>ONLINE</strong></label> <input
					type="radio"  class="switch-input" name="view" value="month"
					id="month" checked onclick="showOffline();"> <label for="month"
					class="switch-label switch-label-on"><strong>OFFLINE</strong></label> <span
					class="switch-selection"></span>
			</div>
		</div>
	</nav>