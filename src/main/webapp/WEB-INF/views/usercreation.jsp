<%@ include file="header.jsp"%>

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
					<h4>User Creation</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<c:choose>
								<c:when test="${not empty errorMessage}">
									<div id="e1" class="alert alert-danger" role="alert">
										${errorMessage}</div>

								</c:when>
								<c:when test="${not empty successMessage}">
									<div id="e2" class="alert alert-success" role="alert">
										${successMessage}</div>

								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>
							<c:url value="/aua/adduser" var="url" />
							<form:form role="form" method="POST" action="${url}"
								modelAttribute="adduser" id="adduser">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group">
									<label>Application Name <font color="red">*</font>
									</label>
									<form:input path="deptName" placeholder="Application Name"
										class="form-control" required="true"
										style="text-transform:uppercase" name="deptName" id="deptName" />
									<span><form:errors cssClass="error" path="deptName" /></span>
								</div>
								<div class="form-group">
									<label>Contact Person Email Id <font color="red">*</font>
									</label>
									<form:input path="email" placeholder="EMAIL ID"
										class="form-control" type="email" name="email" id="email" />

									<!-- <p class="help-block" style="color:#337ab7">Activation mail will be sent to this
									Email Id</p> -->
									<span><form:errors cssClass="error" path="email" /></span>
								</div>
								
								<div class="form-group">
									<label>Description <font color="red">*</font>
									</label>
									<form:input path="desc" placeholder="DESC"
										class="form-control" type="desc" name="desc" id="desc" />

									<!-- <p class="help-block" style="color:#337ab7">Activation mail will be sent to this
									Email Id</p> -->
									<span><form:errors cssClass="error" path="desc" /></span>
								</div>
								<div align="center">
									<button style="margin-right:90px" type="submit" class="btn btn-success">Submit</button>
										<button type="reset" onclick="window.location.reload()"
									class="btn btn-primary">Cancel</button>
								</div>
							</form:form>

						</div>

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
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


<!-- jQuery -->
<c:url value="/resources/" var="resources" />
<script src="${resources}vendor/jquery/jquery.min.js"></script>
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script>
<script src="${resources}vendor/jquery/jquery.validate.min.js"></script>
<script src="${resources}js/user_creation.js"></script>
<%@ include file="footer.jsp"%>
