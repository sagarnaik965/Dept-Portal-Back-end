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
					<h4>Transaction Details</h4>
				</div>
				<c:url value="/aua/gettransactiondetail" var="url" />

				<div class="panel-body">
					<%-- 					<form action="${url}" method="get" > --%>
					<div class="col-lg-4"></div>
					<div class="col-lg-4">
						<table style="padding: 12px;">
							<tr>
								<td>
									<div class="form-group">
										<label>Transaction Id<font color="red"> *</font>
										</label> <input type="text" id="txn" name="txn" class="form-control">
										<div class="text-danger" id="errorMessage"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
										<label for="sel1">Select Transaction Type:</label><font
											color="red"> *</font> <select class="form-control" id="type"
											name="type">
											<option value="auth">AUTH</option>
											<option value="otp">OTP</option>
											<option value="kyc">KYC</option>
											<!-- <option value="bfd">BFD</option> -->

										</select>
									</div>
								<td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
										<button onclick="fetchDetails()" class="btn btn-primary" style="margin-left: 30px;">Fetch Details</button>
									</div> <!-- 					</form> -->
								</td>
							</tr>
						</table>
					</div>
					<div class="col-lg-4"></div>
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
					<h4>Transaction Details</h4>
				</div>


				<div class="panel-body" id="tableview">
					<table id="example" width="100%"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>

								<th>Transaction-ID</th>
								<th>Response Code</th>
								<th>Return</th>
								<th>Error Code</th>
								<th>Request Receipt Time</th>
								<th>Response Forward Time</th>


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
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script> 
<script src="${resources}vendor/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resources}vendor/bootstrap/js/moment.min.js"></script>
<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">
<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>
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
			$('#errorMessage').html("Please Enter Valid Transaction ID");
			$('#errorMessage').show();
			$('#nodatafetchview').hide();
			return false;
		} else if (txn.length < 3) {
			$('#errorMessage').html("Please Enter Valid Transaction ID");
			$('#errorMessage').show();
			$('#nodatafetchview').hide();
			return false;
		} else {
			$('#errorMessage').hide();
		}

		$.ajax({
			type : "GET",
			url : "gettransactiondetail",
			data : {
				"txn" : txn,
				"type" : type,
			},
			success : function(data) {
				console.log(data);
				//alert(data.length);
				if (data.length == 0) {
					//alert("data absent");
					$('#tableview').hide();
					$('#nodatafetchview').show();
				} else {
					//alert("data present");
					$('#tableview').show();
					$('#nodatafetchview').hide();
				}
				// 				 $('#example').dataTable().fnDestroy();
				// 				 $('#example').empty();

				var table = $('#example').DataTable({
					"destroy" : true,
					"aaData" : data,
					"aoColumns" : [ {
						"mData" : 'txn'
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
				// 					"scrollX" : true,
				// 					"scrollY" : true,

				});

			},
			error : function(data) {

			}
		});
	}
	$(document).ready(function() {
		$('#tableview').hide();
		$('#nodatafetchview').hide();

	});
	// 		$( "#txn" ).focus(function() {
	// 			 // alert( "Handler for .focus() called." );
	// 			 $('#errorMessage').hide();
	// 			});
</script>
<%@ include file="footer.jsp"%>
