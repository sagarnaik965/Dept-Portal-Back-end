<%@ include file="header.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
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
					<h4>Department Wise Failure Transactions Details </h4>
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
							<col></col>
							<col class="bg-warning"></col>
							<col ></col>
						</colgroup>
						<thead style="background-color: #AFD765;">
									<tr>

										<th colspan="6" class="text-center" >Demo Authentication </th>
										<th colspan="4" class="text-center">eKYC</th>
										<th rowspan="2" class="text-center">BFD</th>
										<th rowspan="2" class="text-center">Total</th>
										<th rowspan="2"></th>
									</tr>
									<tr>
									<th>deptName</th>
									<th>Bulk</th>
									<th>OTP</th>
									<th>Finger</th>
									<th>Iris</th>
									<th>Sub-Total</th>
									<th>OTP</th>
									<th>Finger</th>
									<th>Iris</th>
									<th>Sub-Total</th>
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
/* 	var data = "";
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "getudclist",
		data : JSON.stringify(data),
		dataType : 'json',
		success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	  */
	  
	// console.log(data);
	
    <c:url value="/getfailurecount" var="getfailurecount"/>
    $('#example').DataTable( {
        "ajax": {
            "type"   : "GET",
            "url"    : '${getfailurecount}',
            "dataSrc": ""
          },
        "columns": [
        	 {"data" : "deptName"},
            {"data" : "authCount"},
            {"data" : "kycCount"},
            {"data" : "otpCount"},
            {"data" : "bfdCount"},
            {"data" : "total"},
  ],
         
	
	"columnDefs" : [ {
				"targets" : 0,
				"className" : "text-center",
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
			}, {
				"targets" : 5,
				"className" : "text-center",
			}, {
				'targets' : 6,
				"mData" : "deptId",
				"mRender" : function(data, type, full) {
					return "<a  href='transactioncountdownload?type=failure&deptid="+data+"'>View Details</a>";
					//return data;
				}
			} ]

		});

		$('#example tbody').on('click', '#button', function() {
			var data = table.row($(this).parents('tr')).data();
			alert(data[0]);
		});
		
var groupColumn = 0;
<c:url value="/getfailurecountnew" var="getfailurecountnew"/>
var table = $('#examplex').DataTable({
	"ajax": {
        "type"   : "GET",
        "url"    : '${getfailurecountnew}',
        "dataSrc": ""
      },
	"columns": [
   	   {"data" : "deptName"},
       {"data" : "demoauth"},
       {"data" : "demootp"},
       {"data" : "demofinger"},
       {"data" : "demoiris"},
       {"data" : "demototal"},
       {"data" : "kycotp"},
       {"data" : "kycfinger"},
       {"data" : "kyciris"},
       {"data" : "kyctotal"},
       {"data" : "bfd"},
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
	}, {
		"targets" : 8,
		"className" : "text-center",
	}, {
		"targets" : 9,
		"className" : "text-center",
	}, {
		"targets" : 10,
		"className" : "text-center",
	}, {
		"targets" : 11,
		"className" : "text-center",
	}, {
				'targets' : 12,
		"mData" : "deptId",
		"mRender" : function(data, type, full) {
			return "<a  href='transactioncountdownload?type=failure&deptid="+data+"'>View Details</a>";
			//return data;
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
	});
</script>

<%@ include file = "footer.jsp" %>