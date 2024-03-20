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
					<h4>Department Transaction Status</h4>
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
										<th>Department Name</th>
										<th>Username</th>
										<th>Type</th>
										<th>Transaction Status</th>
										
									</tr>
								</thead>
							
							</table>
							<form action="updatetransactionstatus" id="frm-example" method="post">
							<input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
							<div class="col-md-12 text-center"> 
								<button name="transaction_active" id="transaction_active" value="active" class="btn btn-primary">Activate</button>
								<button name="transaction_deactive" id="transaction_deactive" value="deactive" class="btn btn-primary btn-space">Deactivate</button>
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
	var data =eval('${transactionstatus}');
	console.log(data);
	var table = $('#example').DataTable( {
		  "aaData": data,
		  "aoColumns": [
			{"mData": 'id'},
			{
	               "mData": "deptName",
		            "mRender": function ( data, type, full ) {
		            return "<a href='#' class='link-details'>"+data+"</a>"
		             }
	            },
		    { "mData": "userName"},
		    { "mData": "type"},
		   /*  { "mData": "loginStatus"}, */
		  ],
		  "paging":true,
	        'columnDefs': [
	            {
	               'targets': 0,
	               'checkboxes': {
	                  'selectRow': true
	               }
	            },
	            {
		               'targets': 4,
		               "mData": "loginStatus",
			             "mRender": function ( data, type, full ) {
			            	 if(data == true) {
			            		 return '<center><span class="label label-success">Active</span></center>';
			            	 }else{
			               		return '<center><span class="label label-danger">Inactive</span></center>';
			            	 }
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
	
	$(".checkbox").change(function() {
	    if(this.checked) {
	        alert("heheheheh");
	    }
	});
	 
	  $('#transaction_active, #transaction_deactive').on('click', function(e){
		var rows_selected = table.column(0).checkboxes.selected();
		var rowData = table.rows('.selected').data().toArray();
		console.log(rowData);
	
		var tblData = table.rows('.selected').data();
		var ab=table.row( this.closest('tr') ).data() ;
		console.log(ab);

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
	  	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var headers = {};
		headers[csrfHeader] = csrfToken;
		
	  $("#example tbody").on('click','.link-details', function(){
			var dept_details = table.row( $(this).parents('tr') ).data();
			var data = {};
			data[csrfParameter] = csrfToken;
			data["userName"] = dept_details.userName;
			$.ajax({
		          url: 'deptdetails',
		          type: 'POST',
		          data:data, //'userName='+dept_details.userName,
		          headers:headers,
		          dataType: 'html'
		     }).done(function(data){
			
		   $('#dynamic-content').html(''); 
		    $('#dynamic-content').html(data);
				
		     });
		     $("#DescModal").modal('show');
		});

	
} );

</script>
<div class="modal fade" id="DescModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                 <h3 class="modal-title">Department Details</h3>

            </div>
            <div class="modal-body">
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