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
					<h4>Update Profile</h4>
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
							<c:url value="/aua/updateprofile" var="url" />
							<h4>Contact details</h4>

							<form:form role="form" method="POST" action="${url}"
								modelAttribute="profile" name="updateprofile" id="updateprofile">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group">
									<label>Person Name<font color="red"> *</font>
									</label>
									<form:input path="cd_name" placeholder="Contact Person"
										class="form-control" maxlength="100" />
									<span><form:errors path="cd_name" cssClass="error" /></span>
								</div>
									<div class="form-group">
									<label>Designation <font color="red"> *</font> </label>
									<form:input path="cd_designation"  id="cd_designation" name="cd_designation" placeholder="Designation"
										class="form-control"  maxlength="100"  />
								</div>
								<div class="form-group">

									<label>Mobile number<font color="red"> *</font>
									</label>
									<form:input path="cd_mobile" placeholder="Mobile Number"
										class="form-control " maxlength="12" />


									<p class="help-block">Exclude Country code</p>
									<span><form:errors path="cd_mobile" cssClass="error" /></span>
								</div>


								<div class="form-group">
									<label>Office/Landline number<font color="red">
											*</font>
									</label>
									<form:input path="cd_phone" placeholder="Landline Number"
										class="form-control" maxlength="16" />

									<p class="help-block">Enter digits only</p>
									<span><form:errors path="cd_phone" cssClass="error" /></span>
								</div>
								<div class="form-group">
									<label>Address<font color="red"> *</font>
									</label>
									<form:textarea path="addressLine" placeholder="Address"
										class="form-control" />
									<span><form:errors path="addressLine" cssClass="error" /></span>
								</div>

								<div class="form-group">
									<label>City <font color="red"> *</font>
									</label>
									<form:input path="city" placeholder="City" class="form-control"
										maxlength="60" />

									<span><form:errors path="city" cssClass="error" /></span>
								</div>


								<div class="form-group">
									<label>Pincode <font color="red"> *</font>
									</label>
									<form:input path="pincode" placeholder="Pincode"
										class="form-control" maxlength="6" />

									<span><form:errors path="pincode" cssClass="error" /></span>
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
	<c:url value="/resources/js/user_update.js" var="updateProfile" />
 <script src="${updateProfile }"></script> 

<%@ include file="footer.jsp"%>
