<%@ include file="header.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
		<br>
	<button type="button" class="btn btn-outline btn-info" onclick="goBack()" >Back</button>
	<script>
function goBack() {
    window.history.back();
}
</script>
<br>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4> Transactions :  ${type}</h4>
				</div>
				<div class="panel-body">
					<table id="example" width="100%"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								
								<th>Department/AUA Name</th>
								<th>Successful Transaction</th>
								<th>Failure Transaction</th>
								<th>Total Transaction</th>

							</tr>
						</thead>

					</table>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<!-- /.col-lg-12 -->
	</div>
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
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css" rel="stylesheet" />
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var data =eval('${count}');
	console.log(data);
	var table = $('#example').DataTable( {
		  "aaData": data,
		  "aoColumns": [
			{"mData": 'deptName'},
			{"mData": 'successCount'},
			{"mData": 'failureCount'},
		    { "mData": "totalCount"},
			 
		  ],
		  "paging":true,
		});
} );

</script>


<%@ include file="footer.jsp"%>