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
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Resend Account Activation link</h4>
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
										<th>Email Id</th>
										
									</tr>
								</thead>
							
							</table>
							<form action="resendactivationlink" id="frm-example" method="post">
							<input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
							<div class="col-md-12 text-center"> 
								<button name="resendEmail" id="resendEmail" value="Resend Email" class="btn btn-primary">Resend Email</button>
<!-- 								<button name="login_deactive" id="login_deactive" value="deactive" class="btn btn-primary btn-space">Deactivate</button> -->
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

<c:url value="/resources/" var="resources" />
<script src="${resources}vendor/jquery/jquery.min.js"></script>
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script> 
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script>
<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
 





<script type="text/javascript">
$(document).ready(function(){
	var data =eval('${userstatus}');
	console.log(data);
	var table = $('#example').DataTable( {
		  "aaData": data,
		  "aoColumns": [
			{"mData": 'id'},
			  {
	               "mData": "deptName",
// 		            "mRender": function ( data, type, full ) {return "<a href='#' class='link-details'>"+data+"</a>"}
	            },
		    { "mData": "userName"},
		    { "mData": "emailId"},
		  ],
		  "paging":true,
	        'columnDefs': [
	            {
	               'targets': 0,
	               'checkboxes': {
	                  'selectRow': true
	               }
	            },
	            { "width": "20%", "targets": 3 },
	            {"className": "dt-center", "targets": "_all"} 

	            
	         ],

	         'select': {
	            'style': 'multi'
	         },
	         'order': [[1, 'asc']]
	         
		});
			  $('#resendEmail').on('click', function(e){
		  var rows_selected = table.column(0).checkboxes.selected();
		
		  $.each(rows_selected, function(index, rowId){
		        $('#frm-example').append(
			             $('<input>')
			                .attr('type', 'hidden')
			                .attr('name', 'users')
			                .attr('id', 'users')
			                .val(rowId)
			         );
		  });
		  
		  if($('#users').val() != null){
			  $('#frm-example').submit();
		  }else{
			  alert("Please select atleast 1 department.");
			  return false;
		  }
	  
	  });
} );

</script>

<%@ include file = "footer.jsp" %>
