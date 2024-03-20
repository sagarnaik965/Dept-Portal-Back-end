<!DOCTYPE html>
<%@page import="org.springframework.web.filter.ShallowEtagHeaderFilter"%>
<%-- <%@ page contentType="text/html;charset=UTF-8"%>
 --%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html >
<%
//START : Fix for Action Spoofing (Clickjacking)
response.addHeader("X-FRAME-OPTIONS", "DENY");
//END : Fix for Action Spoofing (Clickjacking)	 
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", -1);
response.setHeader("X-Content-Type-Options", "nosniff");
response.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=strict");
/*  response.setHeader("Content-Security-Policy", "default-src 'self'"); */
response.setHeader("x-xss-protection", "1");
response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
response.setHeader("X-Download-Options", "noopen");
response.setHeader("X-Content-Security-Policy",
		"default-src 'self'; script-src 'self' cdnjs.cloudflare.com maxcdn.bootstrapcdn.com cdn.jsdelivr.net ajax.googleapis.com; style-src 'self' maxcdn.bootstrapcdn.com cdnjs.cloudflare.com; img-src 'self'; font-src cdnjs.cloudflare.com");
%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" type="image/x-icon"
	href="resources/images/advlogo2.ico" />

<title>Aadhaar Data Vault</title>

</head>








<div id="footer"  style="position: fixed;bottom: 0px; width: 100%;"> 
<div class="container-fluid">
	
		
		<div class="row footer" style="padding-top: 5px;">
			
		
			
			
			
		
		</div>
		<div class="row footer">
			<hr style="margin-top: 5px; margin-bottom: 5px;">
			<div class="col-md-3 text-left" id="footerAlign">
			<!-- Visitors: <span id="visited">2431162</span>  -->
			</div>
			
			<div class="col-md-6 text-center" id="footerAlign">
			Copyright Policy @ 2023 C-DAC Mumbai. All Rights Reserved.
				
			</div>
			<div class="col-md-3 text-right" id="footerAlign">
				<!-- Last updated on : 23/03/2017 -->
			</div>
			
		</div>
		
		<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">              
		      <div class="modal-body">
		      	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		        <img src="" class="imagepreview" style="width: 100%;">
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		      <div class="modal-content">
		        <div class="modal-body">
		          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
		          <iframe width="100%" height="350" src="" frameborder="0" allowfullscreen=""></iframe>
		        </div>
		      </div>
    </div>
  </div>
		
	</div>
	</div>
	</html>
	