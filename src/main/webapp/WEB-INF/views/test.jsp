<%@ include file="header.jsp"%>


<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12 text-center">
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>TestSigning Preference</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="row" style="padding-top: 10px;">
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div class="panel-heading text-center"
											style="padding-top: 5px; padding-bottom: 5px;">
											<h4>New_Generation List</h4>
										</div>
										<div class="panel-body">
											<table id="example" width="100%"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>NEW_UDC</th>
														<th>NEW_Vendor Code</th>
													</tr>
												</thead>

											</table>
										</div>
										<!-- /.panel-body -->
									</div>
									<!-- /.panel -->
								</div>
							</div>

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

</div>

<!-- jQuery -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css"
	href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<script>

$(document).ready(function() {

	 
	<c:url value="/aua/getkunallist" var="kunal"/>
	    $('#example').DataTable( {
	        "ajax": {
	            "type"   : "GET",
	            "url"    : '${kunal}',
	            "dataSrc": ""
	          },
	        "columns": [
	            {"data" : "udc"},
	            {"data" : "vendorCode"},
	           ],
	        "columnDefs": [
	               {"targets": 0, 
	            		"className": "text-center",},
	               {"targets": 1, 
	                		"className": "text-center",}
	        ]  
	           
	    } );
} );

	

</script>

<%@ include file="footer.jsp"%>