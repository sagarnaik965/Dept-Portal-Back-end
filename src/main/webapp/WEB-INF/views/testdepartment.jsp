<%@ include file="header.jsp"%>
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
					<h4>Update Information ${test }</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<c:choose>
								<c:when test="${not empty errorMessage}">
									<div class="alert alert-danger" id="e1" role="alert">
										${errorMessage}</div>

								</c:when>
								<c:when test="${not empty successMessage}">
									<div class="alert alert-success" id="e2" role="alert">
										${successMessage}</div>

								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>
							<c:url value="/aua/updateinfo123" var="url" />
							<h4>Contact details</h4>
							<form:form role="form" method="GET" action="${url}"
							modelAttribute="profile" name="updateinfo" id="updateinfo" > <input
								type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<div class="form-group">
								<label>UPDATE MOBILE NO<font color="red"> *</font>
								</label>
								<form:input path="cd_mobile" placeholder="enter mobile no"
									class="form-control" maxlength="30" />
								<span><form:errors path="cd_mobile" cssClass="error" /></span>
							</div>
							<button type="submit" class="btn btn-success">Submit</button>
						</form:form>
 
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
		</div>
		<!-- /.row -->
	</div>


</div>

</div>
<!-- /#wrapper -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css" rel="stylesheet" />
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>
<%@ include file="footer.jsp"%>