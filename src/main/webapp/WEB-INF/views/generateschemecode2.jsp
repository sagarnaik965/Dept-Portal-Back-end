<%@ include file="header.jsp"%>
<meta name="description" content="This is the description for this page" />
<sec:csrfMetaTags />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<!-- /.row -->
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Generate License Key</h4>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert">
								${errorMessage}</div>

						</c:when>
						<c:when test="${not empty successMessage}">
							<div class="alert alert-success" role="alert">
								${successMessage}</div>

						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
					<%-- <div>
						<form action="generateschemecode2">
							 Scheme Code <select id="schemecode" name="schemecode">
							 <c:forEach var="appcode" items="${appcode}" >
							 	<option value="${appcode.auaCode}">${appcode.auaCode}</option>
							 </c:forEach>
							 	</select>
							 	
							 <div class="col-md-12 text-center">
							<button name="login_active" id="login_active" value="active"
								class="btn btn-success">Generate</button>
							<!-- <button name="login_deactive" id="login_deactive"
								value="deactive" class="btn btn-primary btn-space">Cancel</button> -->
							<button type="button" onclick="location.href = '/home'"
								class="btn btn-primary">Cancel</button>
						</div>
						</form>
					</div> --%>
			<!-- /.panel -->
		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->





<!-- jQuery -->
<c:url value="/resources/" var="resources" />
<script src="${resources}vendor/jquery/jquery.min.js"></script> 
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script> 
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script>
<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>
<script src="${resources}vendor/bootstrap/js/bootstrap-toggle.min.js"></script>
<link href="${resources}vendor/bootstrap/css/bootstrap-toggle.min.css" rel="stylesheet" />
<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">
<script src="${resources}vendor/bootstrap/js/jquery-ui.js"></script>
<link href="${resources}vendor/bootstrap/css/jquery-ui.css" rel="stylesheet" type="text/css">