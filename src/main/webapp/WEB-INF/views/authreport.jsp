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
						style="padding-top: 1px;  border: 1px; height:35px;width:100%; background-color:#555; ">
					<h4><font color="white">OTP Transaction Status </font></h4>
				</div>
				<div class="panel-body">
					 		<table id="example" width="100%" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th>Dept</th>
										<th>Count</th>
									</tr>
								</thead>
							
							</table>	
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<div>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
  <script src="http://cdn.oesmith.co.uk/morris-0.4.1.min.js"></script>
	<div id="line-example"></div>
	<br>
	<div id="bar-example"></div>
	
	</div>
</div>
<!-- /.row -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.4.2/css/buttons.dataTables.min.css"></script>
<script src="https://cdn.datatables.net/buttons/1.4.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.4.2/js/buttons.html5.min.js"></script>
<script>


$(document).ready(function() {
    <c:url value="/otpcountdetails" var="otpcountdetails"/>
    $('#example').DataTable( {
    	
        "ajax": {
            "type"   : "GET",
            "url"    : '${otpcountdetails}',
            "dataSrc": "",
            
          },
          
          
        "columns": [
            {"data" : "deptId"},
            {"data" : "count"},
           ],
        "columnDefs": [
               {"targets": 0, 
            		"className": "text-center",},
               {"targets": 1, 
                		"className": "text-center",}
        ] ,
        "initComplete":function( settings, json){
           // console.log(json);
            drawLineGraph(json);
            // call your function here
        },
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf'
        ]
          
    } );

    
} );
function drawLineGraph(data)
{
	//console.log(data);
	Morris.Line({
		  element: 'line-example',
		  data: data,
		  parseTime: false,
		  xkey: 'deptId',
		  ykeys: ['count'],
		  labels: ['Total Transaction'],
		  
		});
	

</script>

<%@ include file = "footer.jsp" %>