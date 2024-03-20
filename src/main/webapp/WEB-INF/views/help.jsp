<%@ page import="java.lang.String" %>


	
	
	<div class="container text-content">
	 <%
   String acsUrl = (String) request.getAttribute("urlReact");
	 System.out.print(" from JSP "+acsUrl);
  %>
	Logging IN..
	
	   <meta http-equiv = "refresh" content = "1; url =<%=acsUrl%>" />

	</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>



	