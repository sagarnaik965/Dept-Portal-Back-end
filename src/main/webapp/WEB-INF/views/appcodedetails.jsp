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
					<h4>APPLICATION CODE WISE DETAILS</h4>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${not empty errorMessage}">
							<div class="alert alert-danger" role="alert">
								${errorMessage}</div>

						</c:when>
						<c:when test="${not empty successMessage}">
							<div class="alert alert-success" role="alert">
								${successMessage}</div>

						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
					<form action="/asa/appcodedetails" id="frm-example" method="post">
					<!-- <input type="checkbox"  name="name1" value="vechile1">
					<input type="checkbox" id="id2"  value="vechile2"> -->
						<table id="example" width="100%"
							class="table table-striped table-bordered table-hover">
							<thead>
							<tr>
								<th>
							 		Application Code <select id="schemecode" name="schemecode" onchange="GetAllDetails(this.value);" onload="GetAllDetails(this.value);" >
							 		<!-- <option value="Select">Select</option> -->
							 		<c:forEach var="appcode" items="${appcode}" >
							 		<option value="${appcode.auaCode}" id="auacode">${appcode.auaCode}</option>
								 	</c:forEach>
							 		</select >
							 	</th>
							</tr>
						</thead>
						</table>
					</form>
					
					<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-10" >
			<table class="box table table-hover table-condensed table-bordered w-auto" style="width:85%" >
				<tr style="border: 1px solid #000000;">
					<!-- <th style="color: #3e76b9;" class="sc">Kind of Authentication</th> -->
					<th class="text-center" style="color: #3e76b9;" class="sc"  bgcolor="#AFD765" >Mode of Operations
					</th>
					<th class="text-center" class="sc"  bgcolor="#AFD765" width="20%"><a style="color: #3e76b9;">Yes</a></th>
					<th class="text-center" class="sc"  bgcolor="#AFD765" width="20%"><a style="color: #3e76b9;">No</a></th>
					<th class="text-center" class="sc" style="color: #3e76b9"  bgcolor="#AFD765" width="20%"><a style="color: #3e76b9;">Total</a></th>
				</tr>
				<tbody>
					<tr>
						<th class="th-color" width="20%">Store UID</th>
						<td class="text-center"><span id="successstoreuid">1,00,00,000</span></td>
						<td class="text-center"><span id="failurestoreuid">1,00,00,000</span></td>
						<td class="text-center"><span id="totalstoreuid">1,00,00,000</span></td>
					</tr>
					<tr>
						<th width="20%">Get Reference Number</th>
						<td class="text-center"><span id="successgetrefnum">1,00,00,000</span></td>
						<td class="text-center"><span id="failuregetrefnum">1,00,00,000</span></td>
						<td class="text-center"><span id="totalgetrefnum">1,00,00,000</span></td>
					</tr>
					<tr>
						<th width="20%">Get UID</th>
						<td class="text-center"><span id="successgetuid">1,00,00,000</span></td>
						<td class="text-center"><span id="failuregetuid">1,00,00,000</span></td>
						<td class="text-center"><span id="totalgetuid">1,00,00,000</span></td>
					</tr>
					<tr>
						<th width="20%">Activate</th>
						<td class="text-center"><span id="sucessactivate">1,00,00,000</span></td>
						<td class="text-center"><span id="failureactivate">1,00,00,000</span></td>
						<td class="text-center"><span id="totalactivate">1,00,00,000</span></td>
					</tr>
					<tr>
						<th width="20%">Deactivate</th>
						<td class="text-center"><span id="successdeactivate">1,00,00,000</span></td>
						<td class="text-center"><span id="failuredeactivate">1,00,00,000</span></td>
						<td class="text-center"><span id="totaldeactivate">1,00,00,000</span></td>
					</tr>
				</tbody>
			</table>
			<div class="col-md-12 text-center">
                <button type="button" onclick="location.href = '/home'" class="btn btn-primary">Back</button>
        	</div>
		</div>
		<div class="col-lg-1"></div>
	</div>

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
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="resources/vendor/metisMenu/metisMenu.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>

<script src="resources/dist/js/canvasjs.min.js"></script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script src="resources/dist/js/Chart.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js">
	
<script src="resources/dist/js/sb-admin-2.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7/js/sb-admin-2.min.js"></script>

<%-- commented for iocl --%>

<script>

$(document)
.ready(
		function GetAllDetails(value){
			//debugger;
			
			//alert("In document ready"+value);
			var value=$("#schemecode").prop("selectedIndex", 0).val();
			var urlWithVartotal ="/asa/acwiseTotaltrans?acCode=" + value ;
			var urlWithVarsuccess ="/asa/acwiseYestrans?acCode=" + value ;
			var urlWithVarfail ="/asa/acwiseNotrans?acCode=" + value ;
					  $.ajax({
						type : "get",
						url : urlWithVartotal,
						success : function(data) {
							$('#totalstoreuid').html(data.struid);
							$('#totalgetuid').html(data.getuid);
							$('#totalgetrefnum').html(data.getrefnum);
							$('#totalactivate').html(data.activate);
							$('#totaldeactivate').html(data.deactivate);
						}
					});
					$.ajax({
						type : "get",
						url :urlWithVarsuccess ,
						success : function(data) {
							$('#successstoreuid').html(data.struid);
							$('#successgetuid').html(data.getuid);
							$('#successgetrefnum').html(data.getrefnum);
							$('#sucessactivate').html(data.activate);
							$('#successdeactivate').html(data.deactivate);
						}
					});
					 $.ajax({
						type : "get",
						url :urlWithVarfail,
						success : function(data) {
							//alert(data.struid);
							$('#failurestoreuid').html(data.struid);
							$('#failuregetuid').html(data.getuid);
							$('#failuregetrefnum').html(data.getrefnum);
							$('#failureactivate').html(data.activate);
							$('#failuredeactivate').html(data.deactivate);
						}
					});
		});
function GetAllDetails(value) {
					//alert("acCode"+value);

				//debugger;
				var urlWithVartotal ="/asa/acwiseTotaltrans?acCode=" + value ;
				var urlWithVarsuccess ="/asa/acwiseYestrans?acCode=" + value ;
				var urlWithVarfail ="/asa/acwiseNotrans?acCode=" + value ;
						  $.ajax({
							type : "get",
							url : urlWithVartotal,
							success : function(data) {
								$('#totalstoreuid').html(data.struid);
								$('#totalgetuid').html(data.getuid);
								$('#totalgetrefnum').html(data.getrefnum);
								$('#totalactivate').html(data.activate);
								$('#totaldeactivate').html(data.deactivate);
							}
						});
						$.ajax({
							type : "get",
							url :urlWithVarsuccess ,
							success : function(data) {
								$('#successstoreuid').html(data.struid);
								$('#successgetuid').html(data.getuid);
								$('#successgetrefnum').html(data.getrefnum);
								$('#sucessactivate').html(data.activate);
								$('#successdeactivate').html(data.deactivate);
							}
						});
						 $.ajax({
							type : "get",
							url :urlWithVarfail,
							success : function(data) {
								//alert(data.struid);
								$('#failurestoreuid').html(data.struid);
								$('#failuregetuid').html(data.getuid);
								$('#failuregetrefnum').html(data.getrefnum);
								$('#failureactivate').html(data.activate);
								$('#failuredeactivate').html(data.deactivate);
							}
						});
					}
</script>
<%@ include file="footer.jsp"%>
