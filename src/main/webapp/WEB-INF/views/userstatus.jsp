<%@ include file="header.jsp"%>
<meta name="description" content="This is the description for this page" />
<sec:csrfMetaTags />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Application Status</h4>
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
					<form action="updateloginstatus" id="frm-example" method="post">
						<table id="example" width="100%"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th colspan="4" class="text-center">Information</th>
									<th colspan="4" class="text-center">Operations</th>
								</tr>
								<tr>
									<th></th>
									<th>Application Name</th>
									<th>Username</th>
									<th>Access Code</th>
									<!-- <th>Validity</th> -->
									<th>Login</th>
									<th>AUTH</th>
									<th>OTP</th>
									<th>EKYC</th>


								</tr>
							</thead>

						</table>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="col-md-12 text-center">
							<button name="login_active" id="login_active" value="active"
								class="btn btn-success">Update</button>
								<button type="reset" onclick="window.location.reload()"
									class="btn btn-primary">Cancel</button>
						</div>

					</form>

				</div>
				<!-- /.panel-body -->
			</div>
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

<script type="text/javascript">
var data = eval('${userstatus}');

</script>

	<script type="text/javascript" src="${resources}js/user_status.js"></script>

<div class="modal fade" id="DescModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">X</button>
				<h3 class="modal-title">Department Details</h3>

			</div>
			<div class="modal-body">
				<div id="dynamic-content"></div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<%@ include file="footer.jsp"%>
