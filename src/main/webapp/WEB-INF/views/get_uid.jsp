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
                            <h4>Get UID Number</h4>
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
                                    <form:form role="form"  method="POST" action="getuid" id="getuid" modelAttribute="getuid">
                                    <input type="hidden"                       
		name="${_csrf.parameterName}"	value="${_csrf.token}"/>
                                        <div class="form-group">
                                            <label>Enter Reference Number<font color="red">*</font> </label>
                                            <input type="text" name="getUID" id="getUID" placeholder="Reference Number" class="form-control"/>
											<span><form:errors path="storeuid" cssClass="error"/></span>
                                        </div>
                                        <div align="center">
										<button style="margin-right:90px" tpe="submit" class="btn btn-primary">Get UID Number</button>
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
