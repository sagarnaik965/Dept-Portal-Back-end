<%@ include file = "header.jsp" %>
        <div id="page-wrapper" style="padding-top:10px;">
            <div class="row">
                <div class="col-lg-12">
                   <!-- <h1 class="page-header"></h1> -->
                           
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <c:choose>
<c:when test="${empty auacode.acCode}"> 

<div class="row">
		<div class="col-lg-8 col-lg-offset-2" style="padding-top: 10px;">
			<div class="alert alert-danger text-center">
	        	Please Update AUA code First!
	        </div>
		</div>
		<!-- /.col-lg-12 -->
	</div>	


</c:when>
<c:otherwise>
			<c:if test="${not empty currentlk}">
			<div class="row">
			  <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Currently  Active KUA Licence Key</h4>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
						<div class="row">
                                <div class="col-lg-8 col-lg-offset-1">
                                <style>
                                td, th {
  white-space: nowrap;
  overflow: hidden;
}
                                </style>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Licence Key</th>
                                        <th>Expiry Date</th>
                                        <th>Client Licence Key</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="odd gradeX">
                                        <td>${currentlk.lk}</td>
                                        <td>${currentlk.validTill}</td>
                                        <td>${currentlk.clk}</td>
                                    </tr>
                                    
                                   
                                </tbody>
                            </table>
							</div>
							</div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
           </div>
            <!-- /.row -->
             </c:if> 	
                 <div class="row" >
                <div class="col-lg-12" >
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Update KUA Licence Key</h4>
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
                                <c:url value="/aua/updatekualk" var="url"/>
                                    <form:form role="form" method="POST" action="${url}" modelAttribute="updatekualk">
                                    <input type="hidden"                        6
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
                                        <div class="form-group">
                                            <label>Licence key<font color="red"> *</font> </label>
                                            <form:input path="lk" placeholder="Licence key " class="form-control" required="true"/>
                                           <span><form:errors path="lk" cssClass="error"/></span>
                                        </div>
                                        				
										<div class="form-group">
											<label>Expiry Date<font color="red"> *</font> </label>
											<div class='input-group date' id='datetimepicker1'>
											<form:input path="validTill" id="validTill" name="validTill" placeholder="Expiry date" class="form-control" />
											</div>
											<span><form:errors path="validTill" cssClass="error"/></span>
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
                <!-- /.col-lg-12 -->
            </div>
            </c:otherwise>
</c:choose>
								      
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->


    
    
     <!-- jQuery -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css">
	
	<script type="text/javascript">
		$(function() {
			$('input[name="validTill"]').daterangepicker({
				locale: {
					  format: 'DD-MM-YYYY'
				    },
				singleDatePicker: true,
				showDropdowns: true	,
				minDate:new Date()
			})
		});
</script>
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
		}, "Licence key cannot contain blank space! "); 
		
		jQuery.validator.addMethod("validlicence", function(value, element) 
				{
				if (/(^([a-zA-Z0-9-_]){8,80}$)/.test(value)) {
		        return true;  
		    } else {
		        return false;  
		    };
		}, "Please enter valid Licence key!");
		
		var input = document.getElementById('validTill');

		input.value = '';
		
		$( "#updatekualk" ).validate( {
			rules: {
				lk:{ 
					required: true,
					blankvalid:true,
					validlicence:true,
					
				},
				validTill :{
					required: true,
				}
			},
			messages: {
				lk: {
					required:"Licence key cannot be blank!",
					
				},
				validTill : {
					required:"Date cannot be blank!",
					
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
			},
			highlight: function ( element, errorClass, validClass ) {
				$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
			},
			unhighlight: function (element, errorClass, validClass) {
				$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
			}
		} );
	});

	$("input").focus(function(){
			 $("#e1").hide();
			 $("#e2").hide();
		});

	
	
	
	</script>

<%@ include file = "footer.jsp" %>