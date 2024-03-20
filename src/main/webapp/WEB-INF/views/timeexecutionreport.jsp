<%@ include file="header.jsp"%>
<title>Time executtion</title>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<!-- /.row -->
	<script>
function goBack() {
    window.history.back();
}
</script>
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default" >
				<div class="panel-heading text-center"  
						style="padding-top: 1px;  border: 1px; height:35px;width:100%; ">
					<h4> Transaction Execution Time Details for ${deptName}  </h4>
				</div>
				<div class="panel-body">
	
				
	
					 		<table id="example" width="100%" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
<!-- 										<th>Dept</th> -->
										<th rowspan="2">Time</th>
										<th colspan="4">Number of Transactions</th>
										</tr>
										<tr>
										<th>Demo Auth</th>
										<th>OTP</th>
										<th>e-KYC</th>
										<th>BFD</th>
										
										
									</tr>
								</thead>
							
							</table>	
											<c:choose>
    <c:when test="${type.equals('failure')}">
    <form action="errorsummarycount" method="get" id="frm" name="frm">
    <input type="hidden" name="pfromDate" id="pfromDate" value="${pfromDate}">
    <input type="hidden" name="ptoDate" id="ptoDate" value="${ptoDate}">
    <input type="hidden" name="period" id="period" value="${period}">
    <input type="hidden" name="deptId" id="deptId" value="${deptId}">
    <input type="button" value ="Click to download summary report " class="btn btn-primary" onclick="formsubmit()" >
    </form>
    </c:when>    
    
</c:choose>
			<!-- /.panel-body -->
			
		</div>
		<!-- /.panel -->
	</div>
<!-- 	<button onclick="ab()">click</button> -->
</div>
<!-- /.row -->
</div>
<div class="row" style="padding-top: 10px;">
		<div class="col-lg-5">
		</div>
		<div class="col-lg-2 center">
	
		<button type="button" class="btn btn-primary" onclick="goBack()">Back</button>
		
		</div>
		<div class="col-lg-5">
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
<script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>

<script>

function formsubmit(){
// 	alert("check for from sunmit funcvrion");
	 $("#frm").submit();
}
$(document).ready(function(){
	
	
	var data =eval('${list2}');
	if(data.length == 1){
	alert("No record found");	
	}
	
	var  deptName='${deptName}';
	var  ac='${ac}';
	//var ac=eval('${list2}');
	console.log(data);
	var table = $('#example').DataTable( {
		  "aaData": data,
		  "aoColumns": [
			{"mData": 'timePeriod'},
			{ "mData": "authCount"},
			{"mData": 'otpCount'},
			{"mData": 'kycCount'},
			{ "mData": "bfdCount"},
			{ "mData": "deptId"},
			
		  ],
		  "paging":   false,
		  "searching": false,
		  "order": [[ 5, "asc" ]],
		  "columnDefs": [
	            {
	                "targets": [ 5 ],
	                "visible": false,
	                "searchable": false,
	            },
	        ],
	         
	         dom: 'Bfrtip',
	         buttons: [
	             {
	                 extend: 'excelHtml5',
	                 text: 'Download',
	                 title: 'Execution Time Report for '+deptName+'-'+ac
	             },
	         ]
		});
	

});


</script>

<%@ include file = "footer.jsp" %>