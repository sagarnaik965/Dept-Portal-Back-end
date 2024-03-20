<%@ include file="header.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
            <div class="row" >
				   <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading text-center" style="padding-top:5px; padding-bottom:5px;">
                            <h4>Signing Preference for AUA code: ${auacode} </h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-8 col-lg-offset-2">
							<form:form role="form"  method="POST" action="updatesigndetails" id="updatepreference" modelAttribute="signdetails" >
                                <table  class="table table-striped table-bordered table-hover" >
                                <tr><td>Request signed token type</td>
                                <td><form:radiobutton path = "requestSignType" name= "requestSignType" value = "HARD" label = "Hard Token" /></td>
                                <td><form:radiobutton path = "requestSignType" name= "requestSignType" value = "SOFT" label = "Soft Token" />
								<label for="requestSignType" class="error"></label></td>
                                </tr>
                                <tr><td>Signing request  </td>
                               <td> <form:radiobutton path = "requestSignDoneAt" name = "requestSignDoneAt" value = "AUA" label = "AUA" /></td>
                                <td><form:radiobutton path = "requestSignDoneAt" name = "requestSignDoneAt" value = "ASA" label = "ASA" />
                                <label for="requestSignDoneAt" class="error"></label></td>
                                </tr>
                                
                                <tr><td>Decryption of e-KYC responce  </td>
                                <td><form:radiobutton path = "decrypeKycDoneAt" name = "decrypeKycDoneAt" value = "AUA" label = "AUA" /></td>
                                <td><form:radiobutton path = "decrypeKycDoneAt" name = "decrypeKycDoneAt" value = "ASA" label = "ASA" />
                                <label for="decrypeKycDoneAt" class="error"></label></td>
                                </tr>
                                
                                <tr><td>Sign e-KYC packet</td>
                                <td><form:radiobutton path = "eKycPacketSign" name = "eKycPacketSign"  value = "true" label = "True" /></td>
                                <td><form:radiobutton path = "eKycPacketSign" name = "eKycPacketSign" value = "false" label = "False" />
                                <label for="eKycPacketSign" class="error"></label></td>
                                </tr>
                                
                                <tr><td>Decryption token type </td>
                                <td><form:radiobutton path = "decrypType" name = "decrypType" value = "HARD" label = "Hard Token" /></td>
                                <td><form:radiobutton path = "decrypType" name = "decrypType" value = "SOFT" label = "Soft Token" />
                                <label for="decrypType" class="error"></label></td>
                                </tr>
                                 <tr><td>Encryption token type</td>
                                <td><form:radiobutton path = "encrypType" name= "encrypType" value = "HARD" label = "Hard Token" /></td>
                                <td><form:radiobutton path = "encrypType" name= "encrypType" value = "SOFT" label = "Soft Token" />
                                <label for="encrypType" class="error"></label></td>
                                </tr>
                                <tr><td>Encryption type</td>
                                <td><form:radiobutton path = "symencrypType" name= "symencrypType" value = "asymetric" label = "Asymetric encryption" /></td>
                                <td><form:radiobutton path = "symencrypType" name= "symencrypType" value = "symetric" label = "Symetric encryption" />
                                <label for="encrypType" class="error"></label></td>
                                </tr>
                                
                                </table> 
                                <input type="hidden"  name="auacode" id="auacode"  value="${auacode }">
                                <input type="hidden"                        
		name="${_csrf.parameterName}"	value="${_csrf.token}"/>
		 <button type="submit" class="btn btn-success">Submit </button>
                              
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
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script>

$('select').on('change', function() {
	  alert( this.value );
	  var ac=this.value;
	  alert(ac);
	  $.ajax({url: "getsignprefrencefromac",
		  data: {"ac":ac},
		  success: function(result){
			  console.log(result);
// 	        $("#div1").html(result);
	    }});
	  
	});

	
	$("#updatepreference").validate({
		rules : {
			requestSignType : {
				required : true
			},
			requestSignDoneAt : {
				required : true
			},
			decrypeKycDoneAt : {
				required : true
			},
			eKycPacketSign : {
				required : true
			},
			decrypType : {
				required : true
			},
			encrypType : {
				required : true
			}
		},
		messages : {
			requestSignType : {
				required : "Please select atleast one value",
			},
			requestSignDoneAt : {
				required : "Please select atleast one value",
			},
			decrypeKycDoneAt : {
				required : "Please select atleast one value",
			},
			eKycPacketSign : {

				required : "Please select atleast one value",
			},
			decrypType : {
				required : "Please select atleast one value",
			},
			encrypType : {
				required : "Please select atleast one value",
			}
		},
	});
</script>
    

<%@ include file = "footer.jsp" %>