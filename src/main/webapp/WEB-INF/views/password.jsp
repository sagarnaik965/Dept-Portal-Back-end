<%@ include file = "header.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <!--<h1 class="page-header"></h1> -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
            <div class="row" style="padding-top:10px;">
				   <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Update Password</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-8 col-lg-offset-2">
                                 <c:choose>
								    <c:when test="${not empty errorMessage}">
									    <div class="alert alert-danger" id="e1" role="alert">
									    	${errorMessage}
									    </div>
									    
								    </c:when>
								       <c:when test="${not empty successMessage}">
									    <div class="alert alert-success" id="e2" role="alert">
									    	${successMessage}
									    </div>
									    
								    </c:when>
								    <c:otherwise>
								      
								    </c:otherwise>
								</c:choose>
                                    <form:form role="form"  method="POST" action="updatepassword" id="updatepassword" modelAttribute="updatePassword">
                                    <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}"/>
                                        <div class="form-group">
                                            <label>Old Password<font color="red">*</font> </label>
                                            <form:password path="oldPassword" name="oldPassword" id="oldPassword" placeholder="Old Password" class="form-control"/>
											<span><form:errors path="oldPassword" cssClass="error"/></span>
                                        </div>
										
										<div class="form-group">
                                            <label>New Password<font color="red">*</font> </label>
                                            <form:password path="newPassword" id="newPassword" name="newPassword" placeholder="New Password" class="form-control" />
											<span><form:errors path="newPassword" cssClass="error"/></span>
                                        </div>
										
										<div class="form-group">
                                            <label>Confirm New Password<font color="red">*</font> </label>
                                            <form:password path="confirmPassword" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" class="form-control"/>
											<span><form:errors path="confirmPassword" cssClass="error"/></span>
                                        </div>
                                        <div align="center">
										<button style="margin-right:90px" tpe="submit" class="btn btn-success">Submit</button>
                                        <button type="reset" class="btn btn-primary">Reset</button>
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
 <script src="${resources}js/update_password.js"></script> 
<%@ include file = "footer.jsp" %>
