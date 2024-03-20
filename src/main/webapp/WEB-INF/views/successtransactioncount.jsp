<%@ include file="header.jsp"%>

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
<!-- asf -->
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default" >
				<div class="panel-heading text-center"  
						style="padding-top: 1px;  border: 1px; height:35px;width:100%; ">
					<h4>Department Wise Successful Transactions Details </h4>
				</div>
				<div class="panel-body">
					 		<table id="examplex" width="100%" class="table table-striped table-bordered table-hover" >
						<colgroup>
							<col ></col>
							<col ></col>
							<col ></col>
							<col ></col>
							<col  class="bg-danger" ></col>
							<col ></col>
							<col ></col>
							<col ></col>
							<col  class="bg-danger"></col>
							<col ></col>
							<col class="bg-warning"></col>
							<col></col>
						</colgroup>
						<thead style="background-color: #AFD765;">
									<tr>

										<th colspan="6" class="text-center" >Mode of Operations </th>
										<!-- <th colspan="4" class="text-center">eKYC</th>
											<th rowspan="2" class="text-center">BFD</th>
										<th rowspan="2" class="text-center">Total</th> -->
										
										<th rowspan="2" ></th>
									</tr>
									<tr>
									<th>AppName</th>
									<th>Struid</th>
									<th>Getuid</th>
									<th>GetRefNum</th>
									<th>Activate</th>
									<th>Deactivate</th>
									<th>Total</th>
<!-- 									<th>Total</th> -->
									</tr>
								</thead>
												
							</table>	
			<!-- /.panel-body -->
			
		</div>
		<!-- /.panel -->
	</div>
<!-- 	<button onclick="ab()">click</button> -->
</div>
<!-- /.row -->
</div>

<!-- acssz -->
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>

<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>

<script>


$(document).ready(function() {
    <c:url value="/getsuccesscount" var="getsuccesscount"/>
    
var groupColumn = 0;
<c:url value="/getsuccesscount1" var="getsuccesscount1"/>
var table = $('#examplex').DataTable({
	"ajax": {
        "type"   : "GET",
        "url"    : '${getsuccesscount1}',
        "dataSrc": ""
      },
	"columns": [
   	   {"data" : "appName"},
       {"data" : "strUidCount"},
       {"data" : "getRefNumCount"},
       {"data" : "getUidCount"},
       {"data" : "activateCount"},
       {"data" : "deactivateCount"},
       {"data" : "total"},
      ],
    "columnDefs":[ {
		"targets" : 0,
		"visible": false
	}, {
		"targets" : 1,
		"className" : "text-center",
	}, {
		"targets" : 2,
		"className" : "text-center",
	}, {
		"targets" : 3,
		"className" : "text-center",
	}, {
		"targets" : 4,
		"className" : "text-center",
	}, 
	{
		"targets" : 5,
		"className" : "text-center",
	}, {
		"targets" : 6,
		"className" : "text-center",
	}, {
		"targets" : 7,
		"className" : "text-center",
	}
	,{
				'targets' : 8,
		"mData" : "appName",
		"mRender" : function(data, type, full) {
			return "<a  href='transactioncountdownload?type=successful&appName="+data+"'>View Details</a>";
		}
	} ],
    "order": [[ groupColumn, 'asc' ]],
    "displayLength": 25,
    "drawCallback": function ( settings ) {
        var api = this.api();
        var rows = api.rows( {page:'current'} ).nodes();
        var last=null;

        api.column(groupColumn, {page:'current'} ).data().each( function ( group, i ) {
            if ( last !== group ) {
                $(rows).eq( i ).before(
                    '<tr class="group info"><td colspan="12">Department Name:'+group+'</td></tr>'
                );

                last = group;
            }
        } );
    }
} );
} );

</script>
<%@ include file = "footer.jsp" %>