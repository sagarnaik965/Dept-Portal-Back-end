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
			<div class="panel panel-default" >
				<div class="panel-heading text-center"  
						style="padding-top: 1px;  border: 1px; height:35px;width:100%; ">
					<h4>KYC Transactions</h4>
				</div>
				<div class="panel-body">
					 		<table id="example" width="100%" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th>Department Name</th>
										<th>Count</th>
									</tr>
								</thead>
							
							</table>	
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<script>


$(document).ready(function() {
    <c:url value="/kyccountdetails" var="kyccountdetails"/>
    $('#example').DataTable( {
        "ajax": {
            "type"   : "GET",
            "url"    : '${kyccountdetails}',
            "dataSrc": ""
          },
        "columns": [
            {"data" : "deptName"},
            {"data" : "count"},
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

<%@ include file = "footer.jsp" %>