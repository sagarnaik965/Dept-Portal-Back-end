<%@ include file="header.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12 text-center">
			<!--<h1 class="page-header"></h1> -->
			<span class="label label-warning ">Note AUA code can be updated only once through portal, if you want to update is again kindly contact Help-desk </span>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<!-- /.row -->
	<c:choose>
		<c:when test="${empty checkcode.acCode}"> 								
	<div id="before" class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default" >
				<div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
					<h4>Update AUA Code</h4>
				</div>
				<div class="panel-body ">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
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
							<c:url value="/aua/updateaccode" var="url" />


							<form:form role="form" method="POST" action="${url}"
								modelAttribute="auacode" name="acupdateform" id="acupdateform">
								<input type="hidden"                       
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
							<div class="form-group">
								<label>AUA Code </label>
								<form:input path="acCode" placeholder="Enter AUA Code"
									class="form-control"  name="acCode" id="acCode" />
								<span><form:errors path="acCode" cssClass="error" /></span>
							</div>
						<button type="submit" class="btn btn-success">Submit</button>
						<button type="button" onclick="location.href = '/portal/home'" class="btn btn-primary">Cancel</button>
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
 </c:when>
 <c:otherwise>
								   
<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default" >
				<div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
					<h4>AUA Code</h4>
				</div>
				<div class="panel-body ">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
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
							


							<form:form role="form" method="POST"
								modelAttribute="auacode" >
								
							<div class="form-group">
								<label>AUA Code </label>
								<form:input path="acCode" placeholder="Enter Ac Code"
									class="form-control" required="true"  readonly="true"/>
								<span><form:errors path="acCode" cssClass="error" /></span>
							</div>
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
	<script>
	 $( document ).ready( function () {
			
		 jQuery.validator.addMethod("blankvalid", function(value, element) 
					{
					if (/^\S+$/.test(value)) {
			        return true;  
			    } else {
			        return false;  
			    };
			}, "AUA code cannot contain blank space!"); 
		 
		$("#acupdateform").validate( {
			rules: {
				acCode:{
					required: true,
					blankvalid:true,
					},
				
				},
		    messages: {
		    	acCode:{
					required: "Enter AUA Code ",
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
		}); 
		
		</script>
		    
	


<%@ include file = "footer.jsp" %>