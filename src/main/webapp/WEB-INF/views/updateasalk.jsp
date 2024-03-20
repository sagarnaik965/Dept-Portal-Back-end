<%@ include file="header.jsp"%>
<div id="page-wrapper" style="padding-top: 10px;">
	<div class="row">
		<div class="col-lg-12">
			<!-- <h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<c:if test="${not empty currentlk}">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading text-center"
						style="padding-top: 5px; padding-bottom: 5px;">
						<h4>Currently Active AUA Licence Key</h4>
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-8 col-lg-offset-2">
								<style>
									td, th {
										white-space: nowrap;
										overflow: hidden;
									}
									</style>
								<table width="100%"
									class="table table-striped table-bordered table-hover dtr-inline"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Licence Key</th>
											<th>Expiry Date</th>
										</tr>
									</thead>
									<tbody>
										<tr class="odd gradeX">
											<td>${currentlk.licenseKey}</td>
											<td>${currentlk.validTill}</td>
										</tr>


									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</c:if>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Update AUA Licence Key</h4>
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
							<c:url value="/aua/updateasalk" var="url" />
							<form:form role="form" method="POST" action="${url}"
								modelAttribute="updateasalk" id="updateasalk">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group">
									<label>Licence key<font color="red"> *</font>
									</label>
									<form:input path="licenseKey" placeholder="Licence key " maxlength="64"
										class="form-control" name="licenseKey" id="licenseKey" />
									<!--                                            <p class="help-block">Enter AUA licence key.</p> -->
									<span><form:errors path="licenseKey" cssClass="error" /></span>
								</div>

								<div class="form-group">
									<label>Expiry Date<font color="red"> *</font>
									</label>
									<div class='input-group date' id='datetimepicker1'>
										<form:input path="validTill" name="validTill" id="validTill"
											placeholder="Expiry date" class="form-control" />
									</div>
									<span><form:errors path="validTill" cssClass="error" /></span>
								</div>



								<button type="submit" class="btn btn-success">Submit</button>
								<!-- <button type="button" onclick="location.href = '/portal/home'"
									class="btn btn-primary">Cancel</button> -->
									<button type="reset" onclick="window.location.reload()"
									class="btn btn-primary">Cancel</button>
							</form:form>
						</div>

					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
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
<script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"> -->
<script type="text/javascript" src="${resources}vendor/bootstrap/js/moment.min.js"></script>
<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">
<script type="text/javascript" src="${resources}vendor/jquery/jquery.validate.min.js"></script>
<c:url value="/resources/js/" var="jsfile" />
<script type="text/javascript" src="${jsfile}update_asaLK.js"></script>

<%@ include file="footer.jsp"%>
