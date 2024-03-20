<html lang="en"><head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" type="image/x-icon" href="resources/images/favicon.ico">

<title>Aadhaar Data Vault</title>



<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css"> -->
<link rel="stylesheet" href="resources/css/bootstrap.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css"> 
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link href="resources/css/site.css" rel="stylesheet" type="text/css">


	
<style type="text/css">

html,
body {
	margin:0;
	padding:0;
	height:100%;
}


#wrapper {
	min-height:100%;
	position:relative;
}

#content {
	padding-bottom:80px; /* Height of the footer element */
}
#footer {
	width:100%;
	height:80px;
	position:absolute;
	bottom:0;
	left:0;
}



label.error {
	color: red;
}


.childrows {
	display: none;
}

.wrapper {
    font-size: medium;
    font-style: normal;
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

.wrapper td {
	border: 1px solid #ddd;
	padding: 4px;
	text-align: center;
}

.wrapper th {
	border: 1px solid #ddd;
	padding: 8px;
}

.wrapper tr:hover {
	background-color: #ddd;
}

.wrapper th {
	padding-top: 6px;
	padding-bottom: 6px;
	text-align: center;
	background-color: #3e76b9;
	color: white;
}
</style>
	

<style>

.canvasjs-chart-credit {
   display: none;
}


#imageFB,#imageTw, #imageLn, #imageCd, #imagePwd, #imageOtp, #imageTkn, #imageBio, #imageEmblm, #imageLogo {
	background: url('resources/images/websiteImages.png');
}


.row {
	margin-left: 0px;
	margin-right: 0px;
}

.navbar-default {
	background-color: #3e76b9;
	border-color: #e7e7e7;
	 
}

.navbar-default .navbar-brand {
	color: #595d5f;
}

.navbar-default .navbar-brand:hover, .navbar-default .navbar-brand:focus
	{
	color: #ffffff;
	background-color: #3e76b9;
}

.navbar-default .navbar-text {
	color: #595d5f;
}

.navbar-default .navbar-nav>li>a {
	color: #595d5f;
}

.navbar-default .navbar-nav>li>a:hover, .navbar-default .navbar-nav>li>a:focus
	{
	color:#3e76b9;
}


.navbar-default .navbar-nav>li>a:hover, .navbar-default .navbar-nav>li>a:focus {
   color: #ffffff;
	background-color: #3e76b9;
}

.navbar-default .navbar-nav>li>.dropdown-menu {
	color: #505050;
	background-image: -webkit-linear-gradient(top,#f0f0f0 0,#f8f8f8 100%);
    background-image: linear-gradient(to bottom,#f0f0f0 0,#f8f8f8 100%);
    background-repeat: repeat-x;
}

.navbar-default .navbar-nav>li>.dropdown-menu>li>a {
	color: #505050;
	
}

.navbar-default .navbar-nav>li>.dropdown-menu>li>a:hover,
	.navbar-default .navbar-nav>li>.dropdown-menu>li>a:focus {
	color: #ffffff;
	background-color: #3e76b9;
}


.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus {
    background-image: -webkit-linear-gradient(top,#3e76b9 0,#3e76b9 100%);
    background-image: linear-gradient(to bottom,#3e76b9 0,#3e76b9 100%);
    background-repeat: repeat-x; 
   /*  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff5f5f5', endColorstr='#ffe8e8e8', GradientType=0); */
    background-color: #3e76b9;
}

.navbar-default .navbar-nav>li>.dropdown-menu>li>.divider {
	background-color: #e7e7e7;
}

.navbar-default .navbar-nav .open .dropdown-menu>.active>a,
	.navbar-default .navbar-nav .open .dropdown-menu>.active>a:hover,
	.navbar-default .navbar-nav .open .dropdown-menu>.active>a:focus {
	color: #595d5f;
	
}

.navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:hover,
	.navbar-default .navbar-nav>.active>a:focus {
	color: #595d5f;
	
}

.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:hover,
	.navbar-default .navbar-nav>.open>a:focus {
	color: #ffffff;
	background-color: #3e76b9;
}

.navbar-default .navbar-toggle {
	border-color: #e7e7e7;
}

.navbar-default .navbar-toggle:hover, .navbar-default .navbar-toggle:focus
	{
	background-color: #e7e7e7;
}

.navbar-default .navbar-toggle .icon-bar {
	background-color: #505050;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
	border-color: #505050;
}

.navbar-default .navbar-link {
	color: #505050;
}

.navbar-default .navbar-link:hover {
	color: #ffffff;
}

@media ( max-width : 767px) {
	.navbar-default .navbar-nav .open .dropdown-menu>li>a {
		color: #595d5f;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>li>a:hover,
		.navbar-default .navbar-nav .open .dropdown-menu>li>a:focus {
		color: #595d5f;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>.active>a,
		.navbar-default .navbar-nav .open .dropdown-menu>.active>a:hover,
		.navbar-default .navbar-nav .open .dropdown-menu>.active>a:focus {
		color: #595d5f;
		background-color: #e7e7e7;
	}
}

.line1 {
	text-align: center;
	padding: 0px 10px 0px 10px;
	margin: 0px 0px 0px 0px;
}

.count {
	width: 100%;
	text-align: center;
	font-size: 30px;
	color: #1995bf;
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	height: 1em;
}

.footer {
	background-color: #3e76b9;
	color: #F5F5F5;
}

.line2 {
	font-size: 20px;
	text-align: center;
	width: 100%;
	padding: 5px 10px 0px 15px;
	margin: 0px 10px 5px 0px;
	color: #5c5c5c;
}


* {
	margin: 0;
	padding: 0;
}

div {
	display: block;
}

.clear {
	clear: both;
}

#draggable {
	margin: 0px 0px 0px 0px;
	padding: 15px 5px 5px 5px;
	height: 180px;
	z-index: 1;
	width: 100%;
}

#dashboard {
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
	-webkit-transition: all 1.0s linear;
	transition: all 1.0s linear;
	height: 75px;
}

.loadable {
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
	position: absolute;
	width: 100%;
	margin: 0px 10px 0px 0px;
}

#a {
	-webkit-transform: rotateY(-180deg);
	transform: rotateY(-180deg);
	-webkit-animation: mymovebackA 8s infinite;
	animation: mymovebackA 8s infinite;
}

#b {
	display: block;
	box-sizing: border-box;
	-webkit-animation: mymoveA 8s infinite;
	animation: mymoveA 8s infinite;
}

#c {
	-webkit-transform: rotateX(-180deg);
	transform: rotateX(-180deg);
	-webkit-animation: mymovebackB 8s infinite;
	animation: mymovebackB 8s infinite;
}

#d {
	display: block;
	box-sizing: border-box;
	-webkit-animation: mymoveB 8s infinite;
	animation: mymoveB 8s infinite;
}

/* X directional movement */
/* Chrome, Safari, Opera */
@-webkit-keyframes mymoveA { 
	10% {
		-webkit-transform: rotateX(0deg);
		}
	
	50% {
		-webkit-transform:rotateX(180deg);  
		}
	70%{
		-webkit-transform:rotateX(180deg);  
		}
	100%{
		-webkit-transform:rotateX(0deg);  
		}
}

@-webkit-keyframes mymovebackA { 
	10% {
		-webkit-transform: rotateX(-180deg);
		}
	50%{
		-webkit-transform: rotateX(0deg);  
		}
	70%{
		-webkit-transform: rotateX(0deg);  
		}
	100%{
		-webkit-transform: rotateX(-180deg);  
		}
}

@keyframes mymoveA { 
	10% {
		transform: rotateX(0deg);
		}
	50%{
		transform: rotateX(180deg);  
		}
	70%{
		transform: rotateX(180deg);  
		}
	100%{
		transform: rotateX(0deg);  
		}
}

@keyframes mymovebackA { 
	10% {
		transform: rotateX(-180deg);
		}
	50%{
		transform:rotateX(0deg);  
		}
	70%{
		transform: rotateX(0deg);  
		}
	100%{
		transform:rotateX(-180deg);  
		}
}

/* Y directional movement */
/* Chrome, Safari, Opera */
@-webkit-keyframes mymoveB { 
10% {
	-webkit-transform: rotateY(0deg);
}

50%{
-webkit-transform: rotateY(180deg);  
}
70%{
-webkit-transform:rotateY(180deg);  
}
100%{
-webkit-transform:rotateY(0deg);  
}
}

@-webkit-keyframes mymovebackB { 
10% {
	-webkit-transform: rotateY(-180deg);
}
50%{
-webkit-transform: rotateY(0deg);  
}
70%{
-webkit-transform: rotateY(0deg);  
}
100%{
-webkit-transform:rotateY(-180deg);  
}
}

@keyframes mymoveB { 10% {
	transform: rotateY(0deg);
}
50%{
transform: rotateY(180deg);  
}
70%{
transform:rotateY(180deg);  
}
100%{
transform:rotateY(0deg);  
}
}

@keyframes mymovebackB { 
10% {
	transform: rotateY(-180deg);
}
50%{
transform:rotateY(0deg);  
}
70%{
transform: rotateY(0deg);  
}
100%{
transform:rotateY(-180deg);  
}
} 

/*END OF Newly added CSS*/
.top_head {
	background: #f7f7f7;
	display: block;
	border-bottom: 1px solid #ebebeb;
	height: 30px;
	font-size: 1.2em;
}

.navbar-brand small {
	display: block;
	font-size: 18px;
	padding-top: 7px;
}

.goi-logo {
	/* margin-top: 10px; */
	padding-top: 2px;
	
	/* padding-left: 70px;
	padding-right: 10px;
	border-right-style: solid;
	border-right-color: #c5c5c5;
	border-right-width: 1px; */
}

.ep-logo {
	margin-top: 5px;
	padding: 10px 0px 0px 30px;
}

#ePramaantext {
	padding-top: 7px; 
	padding-left: 18px;
	margin-top: 10px; 
	font-size:30px;
	color:#3e6e2c;
}

@media ( max-width : 480px) {  
	#ePramaantext { 
		display: none;
	}
	#ePramaantext small{ 
		display: none;
	}
}

@media ( min-width : 768px) {
	.row-eq-height {
		display: -webkit-box;
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
		flex-direction: row;
	}
}

@media only screen and (max-width: 980px) {
	#goitext {
		display: none;
	}
	
	#newsTicker {
		padding-top: 10px;
	}
	
	#imageCarousel{
		padding: 0px;
	}
	
	#footerAlign{
		text-align: center;
	}
	#footernavigation{
		text-align: center;
	}
	
	#footerList{
		padding: 5px 0px 0px 0px;
	}
	
	#footerList li{
		display: block;
		content: " ";
	} 
}

@media only screen and (min-width: 601px) {

	#footernavigation li:not(:first-child):before {
	    content: " | ";
	    padding: 0px 3px 0px 3px;
	}
	
	#footerList li{
		display: inline;
	} 
}

.carasouelPadding {
	padding: 0px 0px 0px 10px;
}

.navbar {
	min-height: 32px !important
}

.navbar-fixed-top {
	min-height: 90px;
}

.navbar-nav>li>a {
	padding-top: 0px;
	padding-bottom: 0px;
	/* line-height: 80px; */
}

@media ( max-width : 980px) {
	.navbar-nav>li>a {
		line-height: 20px;
		padding-top: 10px;
		padding-bottom: 10px;
	}
}




.ministry,.ministry:hover {
   color: #595d5f;
}
   

#imageFB {
	background-position: -31px -130px; 
	width: 35px;
	height: 34px;	
}

#imageLn {
	background-position: -121px -130px; 
	width: 34px;
	height: 37px;
}

#imageTw {
	background-position: -214px -129px; 
	width: 37px;
	height: 37px;
}

#imageCd {
	background-position: -11px -223px; 
	width: 316px;
	height: 40px;
}

#imagePwd{
	background-position: -12px -19px; 
	width: 73px;
	height: 47px;
}

#imageOtp {
	background-position: -147px -5px; 
	width: 47px;
	height: 73px;
}

#imageTkn {
	background-position: -267px -3px; 
	width: 49px;
	height: 69px;
}

#imageBio{
	background-position: -389px -1px; 
	width: 66px;
	height: 85px;
}

#imageLogo{
	background-position: -450px -107px; 
	width: 90px;
	height: 75px;
}

#imageEmblm{

}

.footerRight{
	text-align: right;
}


.mainNav a:link, a:HOVER , a:ACTIVE, a:VISITED   {
  text-decoration:  none;
  } 
  
  
  
  <!--  CSS added-->
  
 @media (max-width: 980px) {
    .navbar-header {
        float: none;
    }
    .navbar-left,.navbar-right {
        float: none !important;
    }
    .navbar-toggle {
        display: block;
    }
    .navbar-collapse {
        border-top: 1px solid transparent;
        box-shadow: inset 0 1px 0 rgba(255,255,255,0.1);
    }
    .navbar-fixed-top {
        top: 0;
        border-width: 0 0 1px;
    }
    .navbar-collapse.collapse {
        display: none!important;
    }
    .navbar-nav {
        float: none!important;
        margin-top: 7.5px;
    }
    .navbar-nav>li {
        float: none;
    }
    .navbar-nav>li>a {
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .collapse.in{
        display:block !important;
    }
   .navbar-nav .open .dropdown-menu {
       position: static;
       float: none;
       width: auto;
       margin-top: 0;
       background-color: transparent;
       border: 0;
       -webkit-box-shadow: none;
       box-shadow: none;
    }
}


 .error {
        color: red; 
        /*font-weight: bold;*/
    }
</style>

</head>


<!-- Navigation -->


<body>
<div id="wrapper">

	<div id="header">

	<nav class="navbar navbar-static-top ep-main-header" style="border: 0px;background-color: #fefefe;" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header pull-left">
				  
				<!-- <a class="navbar-brand goi-logo" href="#"><img
					src="images/emblem4.png" /> </a> --> 
				<a class="navbar-brand ep-logo" href="#"> <!-- <img src="images/logo82.png" /> 
					<img id="imageLogo" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"/> -->
					<img src="../../resources/images/Hnet.com-image (4).png" alt="ePramaan Logo"> 
				</a> <a class="navbar-brand" href="#" id="ePramaantext">ADV
					<small style="color:#a7411b;">Aadhaar Data Vault</small>
				</a>

				
			</div>
			
			<div class="pull-right">				
			   <a class="navbar-brand ministry" href="#" id="goitext" style="font-size: 20px;  font-weight: bold;font-family: sans-serif;padding-top: 7px; margin-top: 10px; padding-left: 18px;">
					Ministry of Electronics &amp; Information Technology
					<small style="text-align: right;font-size:17px; font-family: sans-serif;padding-top: 0px;font-weight: lighter;">Government of India</small>
				</a>
				
				<a class="navbar-brand goi-logo ministry" href="#"> 
					<img src="resources/images/Hnet.com-image (4).png" alt="GOI Logo">
				</a>
		 </div>
			
		</div>
		<!-- /.container -->
	</nav>
	
	<nav class="navbar navbar-default" role="navigation" style="border-radius:0px;"> 
	 <div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button> 
	</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
 				<ul class="nav navbar-nav navbar-left" style="font-family: sans-serif;font-weight:normal; text-transform:capitalize;">
					<li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">About Us <b class="caret"></b></a>
						<ul class="dropdown-menu" style="text-transform: none;">
							<li><a href="whatisepramaan.html">What is e-Pramaan</a></li>
							<li><a href="authType.html">e-Authentication types</a></li>
							<li><a href="whyepr.html">Why e-Pramaan</a> </li><li>
							</li><li><a href="asaaua.html">ASA-AUA Services</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Services <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="https://authenticate.epramaan.gov.in/register/loadOptions.do" target="_blank">User Registration</a></li>
							<li><a href="https://department.epramaan.gov.in/registerSPUser.sp" target="_blank">Department Registration</a></li>
							<li><a href="aadhaarValut.html">Aadhaar Data Vault</a></li>
							<li><a href="https://asaaua.epramaan.gov.in" target="_blank">ASA-AUA Registration</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Resources <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<!--<li><a href="presentations.html">Power Point Presentations  </a></li>-->
							<li><a href="pressrelease.html">Press releases </a></li>
							<!-- <li><a href="manuals.html">Manuals</a></li> -->
							<li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manuals <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="usermanuals.html">User Manuals</a></li>
									<li><a href="integrationManuals.html">Integration Manuals</a></li>
                                </ul>
                            </li>
							<li><a href="publications.html">Standards and Specification </a></li>
							<li><a href="publications1.html">Gazette Notification </a></li>
							<li><a href="mou.html">MoU</a></li>
						</ul></li>
					<li><a href="demo.html">Demo</a></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-earphone"></span>
							Contact Us<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="faq.html">FAQ</a></li>
							<li><a href="contactus.html">Contacts</a></li>


						</ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							Login <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="https://authenticate.epramaan.gov.in/" target="_blank">User</a></li>
							<li><a href="https://department.epramaan.gov.in/" target="_blank">Department </a></li>
						</ul></li>
					
				
				</ul> 
				
				<div class="pull-right">
				
			        <form class="navbar-form" role="search" style="padding: 0px;margin-top: 0px;margin-bottom: 0px;">
			       <ul class="nav navbar-nav navbar-left" style="font-family: sans-serif;font-weight:normal; text-transform:capitalize;">			  
			       		<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Choose Language <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="?language=en">English</a></li>
							<li><a href="?language=hi">Hindi</a></li>
							
						</ul></li>	
					</ul>		
					
					
					<div class="input-group">
				            <input type="text" class="form-control" style="height: 30px;" placeholder="Search" name="q">
				            <div class="input-group-btn">
				                <button class="btn" type="submit" style="height: 30px;background-color : #e47d34;"><i class="glyphicon glyphicon-search" style="color: white;"></i></button>
				            </div>
				        </div>
				        </form>
	    			</div>
			</div>
	</nav>
	<div class="container-fluid"> 
		<hr style=" display: block;  border: 0; border-top: 5px solid #3e76b9; padding: 0; margin-top: 5px;margin-bottom: 5px;">
	</div>
</div>
<div id="content">

<div class="container-fluid">
	<div class="row row-eq-height">
		<div class="col-md-8 carasouelPadding" id="imageCarousel">
			<div id="citizenCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#citizenCarousel" data-slide-to="0" class=""></li>
					<li data-target="#citizenCarousel" data-slide-to="1" class="active"></li>
					<li data-target="#citizenCarousel" data-slide-to="2" class=""></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item">
						<!-- <img src="resources/images/banner1.jpg" style="width: 1350px; height: 300px;" data-hasqtip="true"> -->
						<img class="lazy" src="resources/images/aadhar (1).jpg" style="width: 1350px; height: 300px;" data-hasqtip="true">
					</div>
					<div class="item active">
						<!-- <img src="resources/images/banner2.jpg" style="width: 1350px; height: 300px;" data-hasqtip="true"> -->
						<img class="lazy" src="resources/images/1.jpg" style="width: 1350px; height: 300px;" data-hasqtip="true">
					</div>
					<div class="item">
						<!-- <img src="resources/images/banner3.jpg"	style="width: 1350px; height: 300px;" data-hasqtip="true"> -->
						<img class="lazy" src="resources/images/cdac.jpg" style="width: 1350px; height: 300px;" data-hasqtip="true">
					</div>
				</div>
				<!-- Left and right controls -->
				<a class="left carousel-control" href="#citizenCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#citizenCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>

		<div class="col-md-3">

		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#photos">Photos</a></li>
			<li><a data-toggle="tab" href="#videos">Videos</a></li>

		</ul>

		<div class="tab-content">
			<div id="photos" class="tab-pane fade in active">
				<div>
					<div id="citizenCarouselinner" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#citizenCarouselinner" data-slide-to="0" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="1" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="2" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="3" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="4" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="5" class="active"></li>
							<li data-target="#citizenCarouselinner" data-slide-to="6" class=""></li>
							<li data-target="#citizenCarouselinner" data-slide-to="7" class=""></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-1.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-1.jpg" style="">

								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-2.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-2.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-3.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-7.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-4.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-8.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-5.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-5.jpg" style="">
								</a>
							</div>
							<div class="item active">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-6.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-6.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-6.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-7.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="pop"> <!-- <img class="img-thumbnail" src="resources/images/img-6.jpg" > -->
									<img class="img-thumbnail lazy" src="resources/images/img-8.jpg" style="">
								</a>
							</div>
						</div>
						<!-- Left and right controls -->
						<a class="left carousel-control" href="#citizenCarouselinner" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#citizenCarouselinner" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>

				</div>
			</div>
			<div id="videos" class="tab-pane fade">
				<div>
					<div id="citizenCarouselvideos" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#citizenCarouselvideos" data-slide-to="0" class=""></li>
							<li data-target="#citizenCarouselvideos" data-slide-to="1" class="active"></li>
							<li data-target="#citizenCarouselvideos" data-slide-to="2" class=""></li>
							<li data-target="#citizenCarouselvideos" data-slide-to="3" class=""></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item">
								<!-- <a href="//www.youtube.com/embed/TobK0BQkc7c" data-toggle="lightbox"> -->
								<a href="#photos" class="video" data-video="//www.youtube.com/embed/TobK0BQkc7c" data-toggle="modal" data-target="#videoModal"> <!-- <img  class="img-responsive" src="resources/images/UserRegistrationWithoutAadhaar.jpg" > -->
									<img class="img-responsive lazy" src="resources/images/UserRegistrationWithoutAadhaar.jpg" style="">

								</a>
							</div>
							<div class="item active">
								<!-- <a href="//www.youtube.com/embed/YfzuvT_LNRg" data-toggle="lightbox"> -->
								<a href="#photos" class="video" data-video="//www.youtube.com/embed/YfzuvT_LNRg" data-toggle="modal" data-target="#videoModal"> <!-- <img class="img-responsive" src="resources/images/UserRegistrationWithAadhaar.jpg" > -->
									<img class="img-responsive lazy" src="resources/images/UserRegistrationWithAadhaar.jpg" style="">
								</a>
							</div>
							<div class="item">
								<!-- <a href="//www.youtube.com/embed/eeOR00q4UVY" data-toggle="lightbox"> -->
								<a href="#photos" class="video" data-video="//www.youtube.com/embed/eeOR00q4UVY" data-toggle="modal" data-target="#videoModal"> <!-- <img class="img-responsive" src="resources/images/RoleManagement.jpg" > -->
									<img class="img-responsive lazy" src="resources/images/RoleManagement.jpg" style="">
								</a>
							</div>
							<div class="item">
								<a href="#photos" class="video" data-video="//www.youtube.com/embed/veXZGknayD0" data-toggle="modal" data-target="#videoModal"> <!-- <img class="img-responsive" src="resources/images/DepartmentRegistration.jpg" >  -->
									<img class="img-responsive lazy" src="resources/images/DepartmentRegistration.jpg" style="">
								</a>
							</div>
						</div>
						<!-- Left and right controls -->
						<a class="left carousel-control" href="#citizenCarouselvideos" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#citizenCarouselvideos" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>

				</div>

			</div>
		</div>
	</div>

	</div>
</div>
<!-- /.container fluid-->
<div class="container-fluid" style="padding-bottom: 10px;">
	<!-- <div class="row"> -->
	<div class="col-md-8" style="padding-right: 0px;">
		<!-- <div class="row"> -->
		<div class="col-md-12" style="padding-left: 0px; padding-right: 0px;">
			<div class="panel panel-default">
				<div class="panel-heading" style="background-image: -webkit-linear-gradient(top, #f0f0f0 0, #f8f8f8 100%); background-image: linear-gradient(to bottom, #f0f0f0 0, #f8f8f8 100%); background-repeat: repeat-x;">
					<b style="color: #3e6e2c;">e-Pramaan</b> is a uniform standard
					based national e-Authentication framework to authenticate users of
					various government services in a safe and secure manner for
					accessing services through desktop as well as mobile. &nbsp; <a href="whatisepramaan.html" style="color: #3e76b9">More info</a>
				</div>
			</div>



		</div>

		<div class="col-md-4" style="padding-top: 0px; padding-left: 0px;">
			<div class="panel" style="-webkit-box-shadow: none; box-shadow: none; border: 2px solid #e47d34;">
				<div class="panel-heading" style="color: #e47d34;">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-line-chart fa-4x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<div id="dashboard" class="shadow">
								<!-- <div class="widget-inner loadable" id="c" onclick="location.href='listtotaldept';" style="cursor: pointer;"> -->
								<div class="widget-inner loadable" id="c" onclick="location.href='deshtotaldeptcount';" style="cursor: pointer;">
									<div class="hugeM1" id="totalDept">315</div>
									<div class="hugeM3">
										Total Departments
									</div>
								</div>
								<div class="widget-inner loadable" id="d" onclick="location.href='listtotaltrans';" style="cursor: pointer;">
									<div class="hugeM1" id="totalTrans">241407445</div>
									<div class="hugeM3">
										Total Transactions
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>


		<div class="col-md-4" style="padding-top: 0px; padding-left: 0px;">
			<div class="panel" style="-webkit-box-shadow: none; box-shadow: none; border: 2px solid #d9534f;">
				<div class="panel-heading" style="color: #d9534f;">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-institution fa-4x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<div id="dashboard" class="shadow">
								<div class="widget-inner loadable" id="a" onclick="location.href='listasadept';" style="cursor: pointer;">
									<div class="hugeM1" id="asaauaDept">66</div>
									<div class="hugeM3">
										ASA-AUA Services
									</div>
								</div>

								<div class="widget-inner loadable" id="b" onclick="location.href='listeprdept';" style="cursor: pointer;">
									<div class="widget-inner loadable" id="b" style="cursor: pointer;">
										<div class="hugeM1" id="eprDept">249</div>
										<div class="hugeM3">
											e-Pramaan Services
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="col-md-4" style="padding-top: 0px; padding-right: 5px; padding-left: 0px;">
			<div class="panel" style="-webkit-box-shadow: none; box-shadow: none; border: 2px solid #3e6e2c;">
				<div class="panel-heading" style="color: #3e6e2c;">
					<div class="row">
						<div class="col-xs-2">
							<i class="fa fa-bar-chart-o fa-4x"></i>
						</div>
						<div class="col-xs-10 text-right">
							<div id="dashboard" class="shadow">
								<div class="loadable" id="c" onclick="location.href='listasatrans';" style="cursor: pointer;">
									<div class="hugeM1" id="asaauaTrans">239188526</div>
									<div class="hugeM3">
										ASA-AUA Transactions
									</div>
								</div>
								<div class="loadable" id="d" onclick="location.href='listeprtrans';" style="cursor: pointer;">
									<div class="hugeM1" id="eprTrans">2218919</div>
									<div class="hugeM3">
										e-Pramaan Transactions
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>


		<!--  </div> -->
		<!-- </div> -->
	</div>

	<div class="col-md-4" style="height: 180px;">
		<div class="panel panel-white" style="border: 0; box-shadow: none;">
			<div class="panel-heading" style="padding: 0px 0px 0px 0px;">

				<div class="row">
					<div class="col-xs-11 text-right">
						<div id="dashboard" class="shadow">
							<div class="loadable" id="c">
								<div class="line2">
									Aadhaar Authentication Requests
								</div>
								<div id="chartContainer" style="height: 170px;"><div class="canvasjs-chart-container" style="position: relative; text-align: left; cursor: auto;"><canvas class="canvasjs-chart-canvas" width="690" height="170" style="position: absolute;"></canvas><canvas class="canvasjs-chart-canvas" width="690" height="170" style="position: absolute;"></canvas><div class="canvasjs-chart-toolbar" style="position: absolute; right: 1px; top: 1px;"></div><div class="canvasjs-chart-tooltip" style="position: absolute; height: auto; box-shadow: rgba(0, 0, 0, 0.1) 1px 1px 2px 2px; z-index: 1000; display: none; border-radius: 5px;"><div style=" width: auto;height: auto;min-width: 50px;line-height: auto;margin: 0px 0px 0px 0px;padding: 5px;font-family: Calibri, Arial, Georgia, serif;font-weight: normal;font-style: italic;font-size: 14px;color: #000000;text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);text-align: left;border: 2px solid gray;background: rgba(255,255,255,.9);text-indent: 0px;white-space: nowrap;border-radius: 5px;-moz-user-select:none;-khtml-user-select: none;-webkit-user-select: none;-ms-user-select: none;user-select: none;} "> Sample Tooltip</div></div><a class="canvasjs-chart-credit" style="outline:none;margin:0px;position:absolute;right:3px;top:156px;color:dimgrey;text-decoration:none;font-size:10px;font-family:Lucida Grande, Lucida Sans Unicode, Arial, sans-serif" tabindex="-1" target="_blank" href="http://canvasjs.com/">CanvasJS.com</a></div></div>
							</div>
							<div class="loadable" id="d">
								<div class="line2">
									Total Authentication Requests
								</div>
								<div id="chartContainer2" style="height: 170px;"><div class="canvasjs-chart-container" style="position: relative; text-align: left; cursor: auto;"><canvas class="canvasjs-chart-canvas" width="690" height="170" style="position: absolute;"></canvas><canvas class="canvasjs-chart-canvas" width="690" height="170" style="position: absolute;"></canvas><div class="canvasjs-chart-toolbar" style="position: absolute; right: 1px; top: 1px;"></div><div class="canvasjs-chart-tooltip" style="position: absolute; height: auto; box-shadow: rgba(0, 0, 0, 0.1) 1px 1px 2px 2px; z-index: 1000; display: none; border-radius: 5px;"><div style=" width: auto;height: auto;min-width: 50px;line-height: auto;margin: 0px 0px 0px 0px;padding: 5px;font-family: Calibri, Arial, Georgia, serif;font-weight: normal;font-style: italic;font-size: 14px;color: #000000;text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);text-align: left;border: 2px solid gray;background: rgba(255,255,255,.9);text-indent: 0px;white-space: nowrap;border-radius: 5px;-moz-user-select:none;-khtml-user-select: none;-webkit-user-select: none;-ms-user-select: none;user-select: none;} "> Sample Tooltip</div></div><a class="canvasjs-chart-credit" style="outline:none;margin:0px;position:absolute;right:3px;top:156px;color:dimgrey;text-decoration:none;font-size:10px;font-family:Lucida Grande, Lucida Sans Unicode, Arial, sans-serif" tabindex="-1" target="_blank" href="http://canvasjs.com/">CanvasJS.com</a></div></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- /.row -->
	<!-- </div> -->

</div>
<a id="MainContent" name="MainContent"></a>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-9  text-center" style="font-size: 16px; color: #d9534f;">
			<h3 style="text-transform: uppercase;">e-Pramaan Offers you</h3>
			<p>
				<img src="resources/images/Hnet.com-image (5).jpg">
			</p>
		</div>
	</div>
</div>
<br>

<div class="row">
	<div class="col-md-9">
		<div class="col-md-3 col-sm-6">
			<div class="panel panel-default text-center" style="border: none !important;">
				<span class="fa-stack fa-4x"> <i class="fa fa-circle-thin fa-stack-2x text-success"></i> <img id="imagePwd" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7">
				</span>
				<div class="panel-body">
					<h4>Password</h4>
					<dl>
						<dd style="text-align: justify;">
							Login using username or Aadhaar Supports text and Image Passwords
							<a href="authType.html" style="color: #3e76b9">Read more</a>
						</dd>
					</dl>
				</div>
			</div>
		</div>

		<div class="col-md-3 col-sm-6">
			<div class="panel panel-default text-center" style="border: none !important;">
				<span class="fa-stack fa-4x"> <i class="fa fa-circle-thin fa-stack-2x" style="color: #e47d34;"></i>
					<img id="imageOtp" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7">
				</span>
				<div class="panel-body">
					<h4>OTP</h4>
					<dl>
						<dd style="text-align: justify;">
							E-mail,SMS &amp; Mobile App based OTP.Network independent OTP and
							authentication &nbsp;<a href="authType.html" style="color: #3e76b9">Read more</a>
						</dd>
					</dl>

					<!-- <a href="#" class="btn" style="background-color : #e47d34;border-color:#e47d34;color:#ffffff;">Learn More</a> -->
				</div>
			</div>
		</div>


		<div class="col-md-3 col-sm-6">
			<div class="panel panel-default text-center" style="border: none !important;">
				<span class="fa-stack fa-4x"> <i class="fa fa-circle-thin fa-stack-2x text-info"></i> <img id="imageTkn" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7">
				</span>
				<div class="panel-body">
					<h4>Digital Certificate</h4>
					<dl>
						<dd style="text-align: justify;">
							User Validation via Digital Signature Certificates obtained from
							Licensed CAs. &nbsp;<a href="authType.html" style="color: #3e76b9">Read more</a>
						</dd>
					</dl>
					<!-- <a href="#" class="btn btn-info">Learn More</a> -->
				</div>
			</div>
		</div>

		<div class="col-md-3 col-sm-6">
			<div class="panel panel-default text-center" style="border: none !important;">
				<span class="fa-stack fa-4x"> <i class="fa fa-circle-thin fa-stack-2x text-danger"></i> <img id="imageBio" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7">
				</span>
				<div class="panel-body">
					<h4>Biometric</h4>
					<dl>
						<dd style="text-align: justify;">
							Iris and Fingerprint based Authentication Compliance with Aadhaar
							&nbsp;<a href="authType.html" style="color: #3e76b9">Read
								more</a>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	
</div>
<form method="post" action="details.html" id="formmonth">
	<input type="hidden" name="monthData" id="monthData" value="">
</form>
</div>
<div id="footer"> 


<div class="container-fluid">
		
		<div class="row footer" style="padding-top: 5px;">
			<div class="col-md-3" id="footerAlign" style="height: 40px;">
					<img class="img-rounded" id="imageCd" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" align="left">

			</div>
		
			<div id="footernavigation" class="col-md-7 text-center" style="padding-top:12px;">

				<ul id="footerList" style="margin-bottom: 0px;"> 
					<li onclick="location.href='sitemap.html';" style="cursor: pointer;">Sitemap</li>
					<li onclick="location.href='help.html';" style="cursor: pointer;">Help</li>
					<li onclick="location.href='disclaimer.html';" style="cursor: pointer;">Disclaimer</li>
					<li onclick="location.href='copyright.html';" style="cursor: pointer;">Copyright Policy</li>
					<li onclick="location.href='hyperlinking.html';" style="cursor: pointer;">Hyperlink Policy</li>
					<li onclick="location.href='privacy.html';" style="cursor: pointer;">Privacy Policy</li>
					<li onclick="location.href='terms.html';" style="cursor: pointer;">Terms &amp; Conditions</li>
				</ul>

			</div>
			
				<!-- Footer -->
			<div class="col-md-2 footerRight" style="padding-top:7px;" id="footerAlign">

				<ul class="list-inline" style="margin-bottom: 0px;">
					<li style="padding-bottom: 0px;"><a href="#"><img id="imageFB" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"></a></li>
					<li style="padding-bottom: 0px;"><a href="#"><img id="imageLn" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"></a></li>
					<li style="padding-bottom: 0px;"><a href="#"><img id="imageTw" src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"></a></li>
				</ul>
				


			</div>
		
		</div>
		<div class="row footer">
			<hr style="margin-top: 5px; margin-bottom: 5px;">
			<div class="col-md-3 text-left" id="footerAlign">
			Visitors: <span id="visited">2431162</span> 
			</div>
			
			<div class="col-md-6 text-center" id="footerAlign">
			Copyright © 2017 C-DAC Mumbai. All Rights Reserved.
				
			</div>
			<div class="col-md-3 text-right" id="footerAlign">
				Last updated on : 23/03/2017
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
	</div>

    

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
<!-- <script src="resources/js/jquery.min.js"></script> -->
<script src="/resources/js/jquery-3.5.1.min.js;epssid=EC494A6CE0856C5D78045C730DEAE074"></script>
<script src="resources/js/jquery.bootstrap.newsbox.min.js" type="text/javascript"></script>

		    
	

 <!-- <script type="text/javascript" src="//cdn.jsdelivr.net/jquery.lazy/1.7.4/jquery.lazy.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/canvasjs/1.7.0/jquery.canvasjs.min.js"></script> -->

 <script type="text/javascript" src="resources/js/jquery.lazy.min.js"></script>
 <script src="resources/js/bootstrap.min.js"></script> 
 <script src="resources/js/jquery.canvasjs.min.js"></script>
 <!-- <script src="resources/js/jquery.min.js"></script> -->
 


<script type="text/javascript">

<!-- Code for Image Pop Up-->
    $(function () {
    	   alert("desh ajax");
    	$(".video").click(function () {
            var theModal = $(this).data("target"),
            videoSRC = $(this).attr("data-video"),
            videoSRCauto = videoSRC;
            $(theModal + ' iframe').attr('src', videoSRCauto);
            $(theModal + ' button.close').click(function () {
                $(theModal + ' iframe').attr('src', '');
            });
         });

    	$('.pop').on('click', function() {
    		$('.imagepreview').attr('src', $(this).find('img').attr('src'));
    		$('#imagemodal').modal('show');   
    	});
    	
         $("#demo3").bootstrapNews({
            newsPerPage: 4,
            autoplay: true,
            onToDo: function () {
                //console.log(this);
            }
        });
         
    });
<script type="text/javascript">
    $(document).ready(function() {z
    	 alert("desh ajax");
   	    $('.navbar a.dropdown-toggle').on('click', function(e) {
   	        var $el = $(this);
   	        var $parent = $(this).offsetParent(".dropdown-menu");
   	        $(this).parent("li").toggleClass('open');

   	        if(!$parent.parent().hasClass('nav')) {
   	            $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
   	        }

   	        $('.nav li.open').not($(this).parents("li")).removeClass("open");

   	        return false;
   	    });
   	    
   	    initialize();
   	});
   	
    
    		 var monthData = [];
    		
   		function initialize() {
   		    $('.lazy').Lazy();
   		
   		    var dps = [];
   		    var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
   		     
   		    
   			if($('#chartContainer2').length && $('#chartContainer').length) {
   			  
   			  CanvasJS.addColorSet("customColor",
   				                [//colorSet Array
   				                "#3e76b9"
   				                ]); */
   			  
   				             alert("desh ajax");
   			   $.ajax({
   			    type: 'GET',
   			    url: 'count',
   			    //dataType: 'json',
   			    success: function(data) { 
   			    	 var countData = data;
   			    	data = data.aadhaarMonthlyCount;
   			    	alert("desh ajax");
   			      	for (var i = 0; i < data.length; i++) {
   			      		
   				     var tempMonth = months[parseInt(data[i].monthlyAadhaarCount.yearMonth.substring(5, 7)) - 1];
   			      	  
   			      	  monthData.push({
   			      		  num:tempMonth,
   			      		  month:data[i].monthlyAadhaarCount.yearMonth
   			      		  });
   			      	  
   			    	  dps.push({
   			          label: tempMonth,
   			          y: Number(data[i].monthlyAadhaarCount.transCount)
   			        }); 
   			      } 

   			    	 $('#visited').text(countData.visited);  
   					$('#asaauaDept').text(countData.asaauadept); 
   					$('#asaauaTrans').text(countData.asaauatrans);
   					
   					$('#eprDept').text(countData.eprdept); 
   					$('#eprTrans').text(countData.eprtrans);
   					
   					$('#totalDept').text(countData.totaldept); 
   					$('#totalTrans').text(countData.totaltrans);

   			      	
   				    var chart = new CanvasJS.Chart("chartContainer2", {
   				    theme : "theme2",//theme1
   				    colorSet: "customColor",
   					axisY:{
   				        labelFontSize: 10
   				      },
   				      axisX:{
   				          labelFontSize: 10
   				        },
   			        
   			        data: [{
   			          type: "column",
   			          click : onClick,
   			          dataPoints: dps
   			        } ]
   			      });
   				    
   				   
   				    
   			      chart.render();
   			      
   			      
   			      var chart1 = new CanvasJS.Chart("chartContainer", {
   					theme : "theme2",//theme1
   					colorSet: "customColor",
   					axisY:{
   				        labelFontSize: 10
   				      },
   				      axisX:{
   				          labelFontSize: 10
   				        },
   					
   					data : [ {
   							type : "column",
   							click : onClick,
   							dataPoints : dps
   						}]
   					});

   					chart1.render();

    
   			    }
   			  
   			  }); 
   			  			  
   			} else {
   				
   				alert("desh ajax");
   				 $.ajax({
   					    type: 'GET',
   					    url: 'count',
   					    dataType: 'json',
   					    success: function(data) {
   					    	alert("Magan");
   					    	$('#visited').text(data.visited); 
   					    	$('#totalDept').text(data.totaldept); 
   					    }
   				 
   				 });
   				
   			}
   			
   			}
   		
   		
   		setInterval(initialize, 40000);
   			 


   		function onClick(e) {
   			var month =  monthData[parseInt(e.dataPoint.x)].month;
   			document.getElementById('monthData').value = month;
   			document.getElementById("formmonth").submit();
   			
   		}

   		
   		$(document).ready(
   				function() {

   					$(document).delegate('*[data-toggle="lightbox"]', 'click',
   							function(event) {
   								event.preventDefault();
   								$(this).ekkoLightbox();
   					});
   				});
   		
   			function refreshcaptcha() {
   		       var image = document.getElementById("captchaImage");
   		       image.setAttribute("src", "captcha.png?"+(new Date()).getTime());
   		   }
   </script>
<span style="position: absolute; left: 0px; top: -20000px; padding: 0px; margin: 0px; border: none; white-space: pre; line-height: normal; font-family: monospace, &quot;Courier New&quot;, Courier; font-size: 10px; font-weight: bold; display: none;">Mpgyi</span></body></html>