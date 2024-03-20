<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<meta name="description" content="">
<meta name="author" content="">

<title>Webservice HomePage</title>
<style>

#footer div a {
	color: white;
}

#footer div a.focus {
	color: white;
}

#footer div {
	background-color: #337ab7;
/* 	 	position:relative;  */
/* 	padding: 0 0 1px 0; */
/* 	position: fixed; */
/* / 	bottom: 0px;  */
/* 	left: 0px; */
/* 	right: 0px; */
/*  	margin-bottom: 0px;  */
}
html, body {
    height: 100%;
}
.wrapper {
    min-height: 100%;
    height: 100%;
    margin: 0 auto -55px; /* the bottom margin is the negative value of the footer's height */
}
#footer, .push {
    height: 55px; /* .push must be the same height as .footer */
}

/* #b1 div { */
/* 	background-color: #aaaaaa; */
/* } */
#footer .row {
/* 	margin-right: 0px; */
}
</style>
<!-- Bootstrap Core CSS -->
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.css"
	rel="stylesheet">

<!-- Custom CSS -->
<!-- <link href="resources/dist/css/sb-admin-2.css" rel="stylesheet"> -->
<link
	href="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7/css/sb-admin-2.min.css"
	rel="stylesheet">



<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>

<body>
<!-- 	Images Link for 1,2,3  -->
	<c:url value="/resources/images/ceg-logo-big.jpg" var="ceglogo1" />
	<c:url value="/resources/images/3.png" var="ceglogo3" />
	<c:url value="/resources/images/1.png" var="ceglogo2" />
	<div id="wrapper" class="wrapper">
		<div id="header" style="background-color: white;">
			<nav class="navbar navbar-static-top ep-main-header"
				style="border: 0px; background-color: #fefefe; min-height: 60px"
				role="navigation">
				<div style="border: 0px solid red" class="container-fluid">
					<div  class="navbar-header pull-left">
						<img src="${ceglogo1}" alt="CeG Logo" style="height: 60px;">
					</div>
					<div class="navbar-header pull-right">
					<img src="${ceglogo2}" alt="CeG Logo" style="height: 60px;">
					</div>


				</div>
				<!-- /.container -->
			</nav>
			<div class="container-fluid"
				style="padding-left: 0px; padding-right: 0px;">
				<hr style="display: block; border: 0; border-top: 5px solid #3e76b9; padding: 0; margin-top: 5px; margin-bottom: 5px;">
			</div>
		</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!--Header end -->
		<!--     	body begin -->

<div class="container">

		<div class="row" id="b1" style="border: 0px solid red;height: 250px" >
			<div style="border: 0px solid red" class="col-md-4">
<!-- 			first -->
			<c:url value="/resources/images/1.png" var="ceglogo" />
			
<%-- 			<img src="${ceglogo}" alt="CeG Logo" style="padding: 1px 1px 1px 1px; height: 250px "> --%>
			
			</div>
			<div style="border: 0px solid red" class="col-md-4">
			
<!-- 			second -->
			
			<img src="${ceglogo3}" alt="CeG Logo" style="padding: 1px 1px 1px 1px;height: 250px">
			
			</div>
			<div style="border: 0px solid red" class="col-md-4">
<!-- 			third -->
<label style="">Server Time :</label>
<p id="st" ></p>

			<c:url value="/resources/images/2.png" var="ceglogo" />
<%-- 			<img src="${ceglogo}" alt="CeG Logo" style="padding: 1px 1px 1px 1px; height: 250px"> --%>
			</div>
			
		</div>
		
		
	
<!-- <div class="container"> -->
<div class="row">
  <div class="jumbotron">
    <h1 class="text-center">KRDH ASA-AUA Webservice</h1>      
<!--     <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing responsive, mobile-first projects on the web.</p> -->
  </div>
     
</div>
	</div>	
<!-- </div> -->
		<!-- body end -->
		<!-- footer begin -->
<script >
$(document).ready(function(){
var jsVar= "${serverTime}";
//alert(jsVar);
var d = new Date(parseInt(jsVar));
//alert(d);
document.getElementById("st").innerHTML = d;
//alert(d);
//alert(new Date(parseInt(jsVar)+10000));

var updateTime=parseInt(jsVar);
function refreshDate()
{
	//updateTime=jsVar
	var d = new Date(parseInt(updateTime)+1000);
	//alert(d);
	updateTime=updateTime+1000;

	document.getElementById("st").innerHTML = d;
	
	}


setInterval(refreshDate, 1000);
});


</script>
<div class="push"></div>
</div>

	<div id="footer" class="container-fluid" style="border: 0px solid red" >
	
<!-- 		<div class="row"> -->
<!-- 			<div class="col-md-12 text-center"></div> -->
<!-- 		</div> -->
<!-- 		<div class="row" > -->
			<div class="col-sm-2  " style="border: 0px solid red; height: 50px" ></div>
			<div class="col-sm-8  text-center" style="height: 50px";>
				<p style="color: white;;">Copyright © 2018 C-DAC Mumbai. All
					Rights Reserved.</p>
			</div>
			<div class="col-sm-2  text-center " style="height: 50px" ></div>
<!-- 		</div> -->
</div>


<!-- footer end -->
</body>
</html>







