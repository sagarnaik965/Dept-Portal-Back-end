<%@ include file="header.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Signing Preference${list}</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<form action="fetchandupdatesignprefrence" method="get" onsubmit=" return validinput()">
							<label >Select AC code</label>
								<select name='accode'  id="accode" class="form-control" style="width:auto;">
									<!-- 								<option value="selected" selected>selected</option> -->
									<option value="0">select AUA code</option>
									<c:forEach items="${aclist}" var="list">

										<option value="${list.acCode}">${list.acCode}</option>
									</c:forEach>
								</select> 
								          
								          <h4 id="deptname" name="deptname"></h4>  
<!-- 								          <br> -->
	<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}"/>
		 <button type="submit" class="btn btn-success" >Proceed to change Sign Preferences </button>
							</form>

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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script
	src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css"
	href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script>

$('select').on('change', function() {
	 // alert( this.value );
	  var ac=this.value;
	  if(ac==0)
		  return false;
	  //alert(ac);
	  $.ajax({
// 		  url: "getsignprefrencefromac",
		  url:"getdeptnamefromac",
		  data: {"ac":ac},
		  success: function(result){
			  console.log(result);
 	        $("#deptname").html("Department Name :"+result);
	    }});
	  
	});
	function validinput(){
		var value = $('#accode').val();
		//alert(value);
		if(value==0){
				return false;
		}else
		   return confirm('Do you really want to Change Signing prefrence for'+value);
			}
</script>

<%@ include file="footer.jsp"%>