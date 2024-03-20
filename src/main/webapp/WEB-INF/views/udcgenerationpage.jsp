<%@ include file="header.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12 ">
			
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<c:choose>
		<c:when test="${empty auacode.acCode}"> 								
	
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2" style="padding-top: 10px;">
			<div class="alert alert-danger text-center">
	        	Please Update AUA code before generating UDC 
	        </div>
		</div>
		<!-- /.col-lg-12 -->
	</div>
</c:when>
								    <c:otherwise>
								      
	<!-- /.row -->
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Generate UDC </h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							 <c:choose>
								    <c:when test="${not empty errorMessage}">
									    <div id="e1" class="alert alert-danger" role="alert">
									    	${errorMessage}
									    </div>
									    
								    </c:when>
								       <c:when test="${not empty successMessage}">
									    <div id="e2" class="alert alert-success" role="alert">
									    	${successMessage}
									    </div>
									    
								    </c:when>
								    <c:otherwise>
								      
								    </c:otherwise>
								</c:choose>
							<c:url value="/aua/generateudc" var="url" />


							<form:form role="form" method="POST"  name="udcform" id="udcform" action="${url}"
								modelAttribute="udc" >
								<input type="hidden"                        
										name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<div class="form-group">
								<label>Vendor Code </label>
								<form:input path="vendorCode" placeholder="Enter Vendor Code" value=""
									class="form-control" id="vendorcode" name="vendorcode" maxlength="10" />
<!-- 								<p class="help-block">Enter Address</p> -->
								<span><form:errors path="vendorCode" cssClass="error" /></span>
							</div>
								
						<button type="submit" class="btn btn-success">Submit</button>
						
						<button type="button" onclick="location.href = '/krdh/home'" class="btn btn-primary">Cancel</button>
						</form:form>
						

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
<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>UDC List</h4>
				</div>
				<div class="panel-body">
					 		<table id="example" width="100%" class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th>UDC</th>
										<th>Vendor Code</th>
									</tr>
								</thead>
							
							</table>	
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
</div>
</c:otherwise>
</c:choose>
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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script>

$(document).ready(function() {

	jQuery.validator.addMethod("blankvalid", function(value, element) 
			{
			if (/^\S+$/.test(value)) {
	        return true;  
	    } else {
	        return false;  
	    };
	}, "Vendor code  cannot contain blank space "); 
	
	 jQuery.validator.addMethod("vendorcodevalidation", function(value, element){
		    if (/(^([a-zA-Z0-9]){3,10}$)/.test(value)) {
		        return true;  
		    } else {
		        return false;  
		    };
		}, "Special Characters not allowed");
	  
	  //blankvalidation
	
	$("#udcform").validate( {
		
		rules :{
			vendorCode :{ 
				required:true,
				minlength:3,
				blankvalid:true,
				vendorcodevalidation:true,
			},				
			
			},
	    messages : {
	    	vendorCode :{
	    		required:"Vendor Code cannot be blank!",
				minlength: "Minimum 3 character required!",
				},
			
			},
	  
			errorElement: "span",
			errorPlacement: function ( error, element ) {
				// Add the `help-block` class to the error element
				console.log(error+" "+element );
				//error.addClass( "help-block" );

				if ( element.prop( "type" ) === "checkbox" ) {
					error.insertAfter( element.parent( "label" ) );
				} else {
					error.insertAfter( element );
				}
			}
	});
	
	<c:url value="/getudclist" var="udclisturl"/>
	    $('#example').DataTable( {
	        "ajax": {
	            "type"   : "GET",
	            "url"    : '${udclisturl}',
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

	$("input").focus(function(){
			 $("#e1").hide();
			 $("#e2").hide();
		});

</script>
	
		
		    
<%@ include file = "footer.jsp" %>