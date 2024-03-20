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
	        	Please Update AUA code First
	        </div>
		</div>
		
	</div>	


</c:when>
<c:otherwise>
			<c:if test="${not empty list}">
			<div class="row">
			  <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Currently  Active Certificate Details</h4>
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
<%--                                 <h3>check this one${list.}</h3> --%>
                            <table width="100%" class="table table-striped table-bordered table-hover dtr-inline" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Type</th>
                                        <th>Certificate Identifier</th>
                                        <th>Valid Till</th>
                                    </tr>
                                </thead>
                                <tbody>
<!-- 												<tr class="odd gradeX"> -->
													<c:forEach items="${list}" var="obj">
														<tr class="odd gradeX">
														
															<td> <c:out value="${obj.type}" /></td>
															<td> <c:out value="${obj.certificateIdentifier}" /></td>
															<td> <c:out value="${obj.validTill}" /></td>
														</tr>
													</c:forEach>
<!-- 												</tr> -->


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
                            <h4>Upload New Certificate </h4>
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
                                <c:url value="/aua/uploadcertificate?${_csrf.parameterName}=${_csrf.token}" var="url"/>
                                    <form:form role="form" method="POST" action="${url}" enctype="multipart/form-data" id="uploadcert" name="uploadcert" >
<table>
<tr>
                                        <div class="form-group">
     
                                       <label>Certificate Path<font color="red"> *</font> </label>
<input  class="form-control" type="text" id="certpath" name="certpath">

								<div class="form-group">
									 <label for="sel1">Certificate Type:</label><font
										color="red"> *</font> <select class="form-control" id="type"
										name="type" style="width:auto;">
										<option value="sign">SIGN</option>
										<option value="decrypt">Decrypt</option>
										<option value="symmetric_encrypt">Symmetric Encrypt</option>
									</select>
								</div>

								<div class="form-group">
									<label>Certificate Alias<font color="red"> *</font>
									</label>
									<input type="text" id="certalias" name="certalias" class="form-control">
								</div>
								<div class="form-group">
									<label>Certificate Password<font color="red"> *</font>
									</label>
									<input type="text" id="certpass" name="certpass" class="form-control">
								</div>
								<div class="form-group">
									<label>Upload Certificate <font color="red"> *</font>
									</label>
									<input type="file" id="cert" name="cert" multiple="multiple" class="form-control" accept=".pfx , .crt">
								</div>
								<div class="form-group">
									<label>Certificate Identifier<font color="red"> *</font>
									</label>
									<input type="text" id="certIdentifier" name="certIdentifier"  class="form-control">
								</div>
										<div class="form-group">
											<label>Expiry Date<font color="red"> *</font> </label>
											<div class='input-group date' id='datetimepicker1'>
											<input  name="validTill" id="validTill" placeholder="Expiry date" class="form-control"  />
											</div>
										</div>						
                                        <button type="submit" class="btn btn-success">Submit</button>
                                       <button type="button" onclick="location.href = '/krdh/home'" class="btn btn-primary">Cancel</button>
                                    </form:form>
                                    
                                    </table>
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
				showDropdowns: true,
				minDate:new Date(),
				
				
				
			});
		});
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
	<script>
	 $( document ).ready( function () {
 		jQuery.validator.addMethod("alphabetonly", function(value, element){
 		    if (/(^([a-zA-Z *]){3,60}$)/.test(value)) {
 		        return true;  //
 		    } else if(/\S/.test(value)){
 		    	return true;
 		    	
 		    }else {
 		        return false;  
 		    };
 		}, "Only alphabets allowed"); 

	 		jQuery.validator.addMethod("namevalidation", function(value, element){
	 		    if (/(^([a-zA-Z *])$)/.test(value)) {
	 		        return true; //(^([a-zA-Z *]){5,30}$) 
	 		    } else {
	 		        return false;  
	 		    };
	 		}, "Special Characters and numericals not allowed"); 
		  //(^([a-zA-Z *]){3,10}$)
		  
		  jQuery.validator.addMethod("deignationvalidation", function(value, element){
	 		    if (/(^([a-zA-Z0-9 *]){3,100}$)/.test(value)) {
	 		        return true;  
	 		    } else {
	 		        return false;  
	 		    };
	 		}, "Enter Valid Designation");
		  
		  //blankvalidation
		jQuery.validator.addMethod("blankvalidation", function(value, element){
	 		    if (/\S/.test(value)) {
	 		        return true;  
	 		    } else {
	 		        return false;  
	 		    };
	 		}, "Multiple blank space not allowed");
		jQuery.validator.addMethod("namenumericals", function(value, element){
 		    if (/\S/.test(value)) {
 		        return true;  
 		    } else {
 		        return false;  
 		    };
 		}, "Multiple blank space not allowed");

	 $( "#uploadcert" ).validate( {
			rules: {
				
				certpath:{
					required: true,
					blankvalidation:true,
				},
				type:{
					required: true,
					blankvalidation:true,
				},
				cert:{
					required: true,
				},
				certalias:{
					required: true,
					blankvalidation:true,
				},
				certIdentifier:{
					required: true,
					blankvalidation:true,
				},
				certpass:{
					required: true,
					blankvalidation:true,
				},
				
				
			},
			messages: {
				certpath:{
					required: " Path  cannot be blank!",
					
				},
				type:{
					required: "Type cannot be blank!",
					
				},
				cert:{
					required: "Certificate not uploaded!",
					
				},
				certalias:{
					required: "Alias cannot be blank!",
					
				},
				certIdentifier:{
					required: "Identifier cannot be blank!",
					
				},
				certpass:{
					required: "Password cannot be blank!",
					
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
			
		});
 });

	</script>
<%@ include file = "footer.jsp" %>