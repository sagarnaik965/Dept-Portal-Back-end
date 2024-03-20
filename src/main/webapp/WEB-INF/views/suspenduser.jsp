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
					<h4>Application Status</h4>
				</div>
				<div class="panel-body">
					 <c:choose>
								    <c:when test="${not empty errorMessage}">
									    <div class="alert alert-danger" role="alert">
									    	${errorMessage}
									    </div>
									    
								    </c:when>
								       <c:when test="${not empty successMessage}">
									    <div class="alert alert-success" role="alert">
									    	${successMessage}
									    </div>
									    
								    </c:when>
								    <c:otherwise>
								      
								    </c:otherwise>
								</c:choose>
							 
							<table id="example" width="100%" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th></th>
										<th>Application Name</th>
										<th>Username</th>
										<th>Status</th>
										
									</tr>
								</thead>
							
							</table>
							<form action=updateSuspendStatus id="frm-example" method="post">
							<input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
							<div class="col-md-12 text-center"> 
								<button name="login_active" id="login_active" value="active" class="btn btn-primary">Activate</button>
								<button name="login_deactive" id="login_deactive" value="deactive" class="btn btn-primary btn-space">Deactivate</button>
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
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css" rel="stylesheet" />
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>  -->
<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>

<script type="text/javascript">
var data = eval('${userstatus}');
</script>

	<script type="text/javascript" src="${resources}js/suspend_user.js"></script>

<div class="modal fade" id="DescModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                 <h3 class="modal-title">Department Details</h3>

            </div>
            <div class="modal-body">
                 <!-- <h5 class="text-center">Hello. Below is the descripton and/or requirements for hiring consideration.</h5> -->
                  <div id="dynamic-content">
                  </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%@ include file = "footer.jsp" %>
