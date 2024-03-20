<%@ include file="header.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<!--<h1 class="page-header"></h1> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- 	/.row -->

	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Transaction Details Reports</h4>
				</div>
			<%-- 	<c:url value="/aua/gettransactiondetail" var="url" /> --%>
 
				<div class="panel-body">
					<%-- 					<form action="${url}" method="get" > --%>
					<div class="col-lg-12">
						<c:choose>
								<c:when test="${not empty errorMessage}">
									<div class="alert alert-danger" id="e1" role="alert">
										${errorMessage}</div>

								</c:when>
							</c:choose>

					
								<table class="table table-striped table-bordered table-hover">

										<tr>

											<td><label style="align-content: ">Transactional Specific Report</label></td>
											<td><input type="text" id="txn" name="txn" placeholder="Enter Transaction ID" class="form-control" >
											</td>
											<td><select class="form-control" id="type" name="type">
											<option value="auth">AUTH</option>
											<option value="otp">OTP</option>
											<option value="kyc">KYC</option>
											</select>
											</td>
											<td><button onclick="fetchDetails()" class="btn btn-primary" style="margin-left: 30px;">Fetch  Details</button>
											</td>
										</tr>
										<tr>
											<c:url value="/getcsvreport" var="url" />
													<form:form role="form" method="post" action="${url}" onsubmit="return checkdate();">
											<td style="width: 23%;"><label>Datewise CSV Report</label></td>
											<td><label
												style="width: 56px; float: left; color: #797979;">From</label>
												<input style="float: left; width: 167px;"
												placeholder="From date" class="form-control" name="fromDate"
												id="fromDate" /> <label
												style="width: 56px; float: left; color: #797979; padding-left: 20px;">To</label>
												<input style="float: left; width: 150px;"
												placeholder="To  date" class="form-control" name="toDate"
												id="toDate" />
											</td>
											<td><select class="form-control" id="type1" name="type">
											<option value="auth">AUTH</option>
											<option value="otp">OTP</option>
											<option value="kyc">KYC</option>
											</select>
											</td>
											<td><button  class="btn btn-primary" style="margin-left: 30px;">Download CSV</button></td>	
											</form:form>
										</tr>


									</table>
					
					
											</div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
	<div class="row" style="padding-top: 10px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading text-center"
					style="padding-top: 5px; padding-bottom: 5px;">
					<h4>Transaction Specific Details</h4>
				</div>


				<div class="panel-body" id="tableview1" >
					<table id="example" width="100%"
						class="table table-striped table-bordered table-hover text-center">
						<thead>
							<tr>

								<th>Txn-Id</th>
								<th>SA</th>
								<th>ACTN</th>
								<th>Code</th>
								<th>Return</th>
								<th>Error</th>
								<th>Receipt Time</th>
								<th>Response Time</th>


							</tr>
						</thead>

					</table>

				</div>
								<div class="panel-body" id="tableview2" >
					<table id="example2" width="100%"
						class="table table-striped table-bordered table-hover text-center">
						<thead>
							<tr>

								<th>Txn-Id</th>
								<th>SA</th>
								<th>ACTN</th>
								<th>Auth ACTN</th>
								<th>Code</th>
								<th>Return</th>
								<th>Error</th>
								<th>Auth Error</th>
								<th>Receipt Time</th>
								<th>Response Time</th>


							</tr>
						</thead>

					</table>

				</div>
				
				<div class="panel-body" id="nodatafetchview">
					<lable class="text-info">No records found</lable>

				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->

</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->






<!-- jQuery -->
<c:url value="/resources/" var="resources" />
 <script src="${resources}vendor/jquery/jquery.min.js"></script> 
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

<!-- Bootstrap Core JavaScript -->
 <script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script> 
<!-- <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<!-- Metis Menu Plugin JavaScript -->
 <script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script> -->

<!-- Custom Theme JavaScript -->
 <script src="${resources}dist/js/sb-admin-2.js"></script> 
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script> -->


<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> -->
<script type="text/javascript" src="${resources}vendor/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resources}vendor/bootstrap/js/moment.min.js"></script>

<!-- <script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"> -->

<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">

<!-- <script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css" rel="stylesheet" />
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script> -->

<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>

<!-- <script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script> -->

<script src="${resources}vendor/bootstrap/js/dataTables.buttons.min.js"></script>
<script src="${resources}vendor/bootstrap/js/buttons.flash.min.js"></script>
<script src="${resources}vendor/bootstrap/js/jszip.min.js"></script>
<script src="${resources}vendor/bootstrap/js/pdfmake.min.js"></script>
<script src="${resources}vendor/bootstrap/js/vfs_fonts.js"></script>
<script src="${resources}vendor/bootstrap/js/buttons.html5.min.js"></script>
<script src="${resources}vendor/bootstrap/js/buttons.print.min.js"></script>


<script>
	function fetchDetails() {
		//alert($('#type').val());

		var txn = $('#txn').val();
		var type = $('#type').val();
		console.log(txn);
		//alert(txn);

		var regExp = new RegExp(/^\S+$/);

		if (!regExp.test(txn)) {
			alert("Please Enter Valid Transaction ID");
			$('#errorMessage').html("Please Enter Valid Transaction ID");
			$('#errorMessage').show();
			$('#nodatafetchview').hide();
			return false;
		} else if (txn.length < 3) {
			alert("Please Enter Valid Transaction ID");
			$('#errorMessage').html("Please Enter Valid Transaction ID");
			$('#errorMessage').show();
			$('#nodatafetchview').hide();
			return false;
		} else {
			$('#errorMessage').hide();
		}

		$.ajax({
			type : "GET",
			url : "aua/gettransactiondetail",
			data : {
				"txn" : txn,
				"type" : type,
			},
			success : function(data) {
				console.log(data);
				//alert(data.length);
				if (data.length == 0) {
						$('#tableview1').hide();
						$('#tableview2').hide();
						$('#nodatafetchview').show();
					
				} else {
					//alert("data present");
					if(type=="kyc"){
						$('#tableview2').show();
						$('#tableview1').hide();
						$('#nodatafetchview').hide();
						}else{
							$('#tableview1').show();
							$('#tableview2').hide();
							$('#nodatafetchview').hide();
						}
					
					
				}
				

						if(type=="kyc"){
							console.info('in kyc');
							var table = $('#example2').DataTable({
								"destroy" : true,
								"aaData" : data,
								"aoColumns" : [ 
									{
										"mData" : 'txn'
									},{
										"mData" : "sa"
									},{
										"mData" : "actn"
									},{
										"mData" : "auth_actn"
									}, {
										"mData" : "responseCode"
									}, {
										"mData" : "ret"
									}, {
										"mData" : 'kyc_err'
									},{
										"mData" : 'auth_err'
									}
									, {
										"mData" : "requestReceiptTime"
									}, {
										"mData" : "responseReceiptTime"
									},

								],
								"paging" : true,
								'order' : [ [ 1, 'asc' ] ],
							
							});
						}else{

							var table = $('#example').DataTable({
								"destroy" : true,
								"aaData" : data,
								"aoColumns" : [ {
									"mData" : 'txn'
								},{
									"mData" : "sa"
								},{
									"mData" : "actn"
								}, {
									"mData" : "responseCode"
								}, {
									"mData" : "ret"
								}, {
									"mData" : 'errorCode'
								}, {
									"mData" : "requestReceiptTime"
								}, {
									"mData" : "responseReceiptTime"
								},

								],
								"paging" : true,
								'order' : [ [ 1, 'asc' ] ],
							
							});
							
							}

				


			},
			error : function(data) {

			}
		});
	}
	$(document).ready(function() {
		$('#tableview').hide();
		$('#nodatafetchview').hide();

	});
</script>
<script>
	var sdate;

	 	$('input[type="radio"]').click(function() 
	 			{
	 		 $('#errormess').hide();
	 if ($(this).attr('id') == 'custom') {

	 $('#twoYearsWarn').show();
	 $("#twoYearsWarn").css('color', 'blue');
	 }

	 else {
	 $('#twoYearsWarn').hide();

	 }
	 }); 

	$(function() {
		$('input[name="fromDate"').daterangepicker({
			singleDatePicker : true,
			showDropdowns : true,
			locale : {
				format : 'DD-MM-YYYY'
			},
			maxDate : new Date(),

		})
	});
	$(function() {
		$('input[name="toDate"').daterangepicker({
			singleDatePicker : true,
			showDropdowns : true,
			locale : {
				format : 'DD-MM-YYYY'
			},
			maxDate : new Date(),

		})

	});

	function checkdate() 
	{
		$("#twoYearsWarn").css('color', 'blue');
		var a = document.getElementById('fromDate').value
		var b = document.getElementById('toDate').value
		
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var fromDateArr = fromDate.split("-");
		var toDateArr = toDate.split("-");
		var date2 = new Date(toDateArr[2], toDateArr[1], toDateArr[0]);
		var date1 = new Date(fromDateArr[2], fromDateArr[1], fromDateArr[0]);
		
		const diffTime = Math.abs(date2 - date1);
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
		
		if (true) {
			//alert("custom");
			if (date1 > date2) {
				console.log("date > date2")
				alert("To date should be greater than From date");
				$("#errormess")
						.html(
								"<div class='text-danger' style='color:red'>FROM date should be smaller than TO date</div>");
				 $('#errormess').show();
				return false;
			} else if(diffDays>4) 
			{	console.log("dayrange greater than 5 days")
				alert("Day range should be 5 days");
				$("#twoYearsWarn").css('color', 'red');
				 $('#errormess').hide();
				return false;
			}else {
				return true;
			}

		} else {
			return true;
		}
	}
	</script>


<%@ include file="footer.jsp"%>
