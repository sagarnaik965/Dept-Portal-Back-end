 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>One Time PushBack</title>
<style>
.heading {
	font-size: 20px;
	font-weight: bold;
	font-family: sans-serif;
	padding-top: 7px;
	margin-top: 20px;
	padding-left: 18px;
}
/*set border to the form*/
form {
	box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
	width: 40%;
	margin-left: auto;
	margin-right: auto;
	border-radius:10px
}
/*assign full width inputs*/
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
/*set a style for the buttons*/
button {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
	
	/* 	background-color: #4CAF50;
 */ /* color: white; */
	padding: 14px 20px;
	margin: 8px 0;
	/* border: none; */
	cursor: pointer;
	width: 100%;
}
/* set a hover effect for the button*/
button:hover {
	opacity: 0.8;
}

/*set padding to the container*/
.container {
	padding: 16px;
	margin-left: 50px #wrapper{
}
}
</style>
</head>
<body onload="GenerateCaptcha()">






	<script type="text/javascript">  
        /* Function to Generat Captcha */  
        function GenerateCaptcha() {  
            var chr1 = Math.ceil(Math.random() * 10) + '';  
            var chr2 = Math.ceil(Math.random() * 10) + '';  
            var chr3 = Math.ceil(Math.random() * 10) + '';  
  
            var str = new Array(4).join().replace(/(.|$)/g, function () { return ((Math.random() * 36) | 0).toString(36)[Math.random() < .5 ? "toString" : "toUpperCase"](); });  
            var captchaCode = str + chr1 + ' ' + chr2 + ' ' + chr3;  
            document.getElementById("txtCaptcha").value = captchaCode  
        }  
  
        /* Validating Captcha Function */  
        function ValidCaptcha() {  
        	
            var str1 = removeSpaces(document.getElementById('txtCaptcha').value);  
            var str2 = removeSpaces(document.getElementById('txtCompare').value);  
            if (str1 == str2)
            	{            	 
 	
            	}
            else{
            	alert("Please enter Valid Captcha!") 
            	document.getElementById("Form").reset();
            	GenerateCaptcha();
            	//alert("Captcha entered is incorrect..!")                 	
            }    
            
        }  
  
        /* Remove spaces from Captcha Code */  
        function removeSpaces(string) {  
            return string.split(' ').join('');  
        }  
        
        function mobilevalidate()
        {
        	var x = document.getElementById("exampleInputMobile").value;
        	
        	if(isNaN(x))
        		{
        			alert("please enter proper mobile number")
        			document.getElementById('exampleInputMobile').value = ''
        			
        		}
        	
        	if(x.length !=10)
    		{
    			alert("please enter proper mobile number")
    			document.getElementById('exampleInputMobile').value = ''
    			
    		}        		
        }
        
         function Namevalidate()
        {
        	 var x = document.getElementById('exampleInputNameOfContactPerson').value;        	
        	if(containsNumbers(x))
        		{
        			alert("please enter proper name")
        			document.getElementById('exampleInputNameOfContactPerson').value = ''        			
        		} 	        	    		
        }
         
         function DeptNamevalidate()
         {
         	 var x = document.getElementById('exampleInputDepartmnetName').value;        	
         	if(containsNumbers(x))
         		{
         			alert("please enter proper department name")
         			document.getElementById('exampleInputDepartmnetName').value = ''        			
         		} 	        	    		
         }
         
         
         function containsNumbers(str) {
        	  return /\d/.test(str);
        	}
        
       
    </script>



	<%-- <%  
StringBuffer sb=new StringBuffer();  
for(int i=1;i<=5;i++)  
{  
    sb.append("a");  
}  
String cap=new String(sb);  
%>  
<div >  
<center>  
<h1>Captcha Demo</h1>  
<script type ="text/javascript">  
function validation(){  
	alert('for validation ');
   /*  var c = document.forms ["f1"]["cap1"].value;  
    if(c==null||c=="")  
    {  
       alert ("");  
       return false;  
    }   */
    
}  
</script>  
<form onsubmit="return validation()">  
 <form >  
        <s><i><p style="font-family: verdana" face="casteller"><%=cap%></p></i></s>
     <font face="casteller"><%=cap%></font>
              <div style="background-color: aqua"><h2><s><i><font face="casteller"><%=cap%></font></i></s></h2></div>  
    
       <input type="text" name="cap1" value="" / onblur="return validation()"> 
        <td><input type="hidden" name="cap2" value='d= <%=cap%>' readonly="readonly" ></td>  
     
 
<input type="submit" value="OK" />  
<input type="reset" value="Reset" />  
</form>  
    </center>  
    </div> 
 --%>


	<%@ include file="headerOtv.jsp"%>

	<%-- <c:url value="/resources/images/advlogo.png" var="ceglogo"   /> --%>

	<div id="wrapper" style="text-align: center;">

		<div id="header" style="background-color: white;">

			<div class="container-fluid">
				<%-- <div class="navbar-header pull-left">
					<a class="navbar-brand" href='<c:url value ="/home"/>'> <img
						src="${ceglogo}" alt="CeG Logo" style="height: 66px; width: 94px;">
					</a>
				</div> --%>
			</div>
		</div>

<br>
		<p style="font-family: verdana">Please Enter Activation Details
			sent to you by mail.</p>

		<form action="onetimepushback" method="post" id="Form">



			<div class="form-group">
				<label for="exampleInputNameOfContactPerson">Key ID
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input style="width: 285px;
					margin-top: 25px ;"
					class="form-control" type="text" placeholder="Enter Username"
					name="username" required>
			</div>

			<div class="form-group">
				<label for="exampleInputDepartmnetName">Secret Key&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
					style="width: 285px" type="password" class="form-control"
					placeholder="Enter Password" name="userpass" 
					required>
			</div>
<input type="hidden"
					name="SSO_Id" value="<%=request.getAttribute("SSO_Id")%>">

				

			<br /> 
			
			

			<!-- <input disabled type="button" id="txtCaptcha"
					style="   text-align: center; pointer-events: none;   border: none; font-weight: bold; font-size: 20px; font-family: Modern" />	 -->

		<del>	<input disabled type="button" id="txtCaptcha" 
				style="width: 100px; border: none; float: left ; margin-left: 175px ; font-style:italic; ; font-stretch:extra-expanded; ;" /> 
				</del>
				<img  src="resources/images/refresh.png" style="float: left; margin-top: 7px ;margin-left: 7px "
				alt="GOI Logo" onclick="GenerateCaptcha();">
				
				<br></br>
				<b> Enter the Captcha:</b> <input type="text" id="txtCompare" style="width: 285px" />

			<br></br>
			<button style="width: 85px" type="submit" class="btn btn-default"
				onclick="ValidCaptcha();">Activate</button>
			<br></br>

		
		</form>





		<%-- <form action="onetimepushback" method="post" id="Form">
			<div class="container">
				<label><p style="font-family: verdana">Key ID</p> </label> <input
					type="text" placeholder="Enter Username" name="username"
					value="U001" required> <label><p
						style="font-family: verdana">Secret Key</p> </label> <input
					type="password" placeholder="Enter Password" name="userpass"
					value="U001pwd" required> 
					<input type="hidden"
					name="SSO_Id" value="<%=request.getAttribute("SSO_Id")%>">

				<!--  <div id="txtCaptcha" style="text-align: center; border: none; font-weight: bold; font-size: 20px; font-family: Modern"></div> -->
				<input disabled type="button" id="txtCaptcha"
					style="text-align: center; pointer-events: none; width: 150px; float: left; border: none; font-weight: bold; font-size: 20px; font-family: Modern" />
				<img style="float: center; margin-top: 8px"
					src="resources/images/refresh.png" alt="GOI Logo"
					onclick="GenerateCaptcha();"> <br />
				<p style="font-family: verdana">Enter Captcha</p>
				<input type="text" id="txtCompare" />
				<br /> <br />
				<button type="submit" onclick="ValidCaptcha();">Activate</button>
			</div>

		</form> --%>
	</div>
	<%@ include file="FooterOtv.jsp"%>

</body>
</html>