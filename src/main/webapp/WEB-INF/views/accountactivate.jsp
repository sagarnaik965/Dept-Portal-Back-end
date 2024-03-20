<%@ include file = "header.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <!--<h1 class="page-header"></h1> -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
            <div class="row" style="padding-top:10px;">
				   <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Update Password</h4>
                        </div>
                        <div class="panel-body">
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
                                    <form:form role="form"  method="POST" name="updateaccountpassword" id="updateaccountpassword" action="updateaccountpassword" modelAttribute="accountactivationdetails">
                                          <input type="hidden"                        
		name="${_csrf.parameterName}"	value="${_csrf.token}"/>
										
										<div class="form-group">
                                            <label>New Password<font color="red">*</font> </label>
                                            <form:password path="newPassword" id="newPassword" name="newPassword" placeholder="New Password" class="form-control"  />
<!--                                             <p class="help-block">Enter New Password.</p> -->
											<span><form:errors path="newPassword" cssClass="error"/></span>
                                        </div>
										
										<div class="form-group">
                                            <label>Confirm New Password<font color="red">*</font> </label>
                                            <form:password path="confirmPassword" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" class="form-control"  />
<!--                                             <p class="help-block">Confirm your new password.</p> -->
											<span><form:errors path="confirmPassword" cssClass="error"/></span>
                                        </div>
                                        <form:input  type="hidden" id="dept" path ="dept" name="dept" value="${user} "  />
                                        <button type="submit" class="btn btn-success">Submit </button>
                                        <button type="reset" class="btn btn-primary">Reset</button>
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
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
	
	<script>
	$( document ).ready( function () {
		
		jQuery.validator.addMethod("validpass", function(value, element) 
				{
			if (/^(?=.*[0-9])(?=.*[!@#$%^&*_\(\)])[a-zA-Z0-9!@#$%^&*_\(\)]{8,12}$/.test(value)) {
		        return true;  
		    } else {
		        return false;  
		    };
		}, "Your password must be at least 8 characters, must contain letters, digits and special characters."); 
		
		$( "#updateaccountpassword" ).validate( {
			rules: {						
				    newPassword:{ 
						required: true,
						minlength:8,
						validpass:true,
				},	
					confirmPassword:{ 
						required: true,
						equalTo: "#newPassword",
				}	
			},
			messages: {
				
				newPassword: {
					required:"Password cannot be blank!",
					minlength:"Minimum 8 characters required!",
				},
				confirmPassword: {
					required:"Password cannot be blank!",
					equalTo: "New Password and Confirm password did not match!",
				}
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
		} );
		
		
	});
	</script>    
	
	
    

<%@ include file = "footer.jsp" %>