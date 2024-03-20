<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@page import="java.io.*"%>


<!DOCTYPE html>
<!-- saved from url=(0092)/resources/index.html# -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="#" />
<title>Demo</title>

	<c:url value="/resources/wc/vendor/bootstrap/css/bootstrap.min.css" var="bootsrapmin" />
		<c:url value="/resources/wc/css/blog-home.css" var="blog_home" />
	
<link
	href="${bootsrapmin}"
	rel="stylesheet">

<link href="${blog_home }"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home"><strong>ONLINE DEMO</strong></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="true" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="navbar-collapse collapse show" id="navbarResponsive"
				style="">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="home">Home
							<span class="sr-only">(current)</span>
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
					id="week" checked onClick="location.href='/webclient/home'"/> 
					<label
					for="week" class="switch-label switch-label-off">ONLINE</label> 
					<input
					type="radio" class="switch-input" name="view" value="month"
					id="month" onClick="location.href='/webclient/offlinehome'"/> <label for="month"
					class="switch-label switch-label-on">OFFLINE</label> <span
					class="switch-selection"></span>
			</div>
		</div>
	</nav>