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
					<h4>Transactions Details Report </h4>
				</div>
				<c:url value="/aua/gettransactionreportpage" var="url" />

				<div class="panel-body">
					<form action="${url}" method="get" onsubmit="return checkdate();">
						<table class="table">
							<tr>

								<td><div class="form-group">
										<label for="sel1">Select Transaction Type:</label><font
											color="red"> *</font> <select class="form-control"
											id="transactiontype" name="transactiontype">
											<option value="demoauth">AUTH Using DEMO</option>
											<option value="demobio">AUTH Using BIO</option>
											<option value="demootp">AUTH Using OTP</option>
											<option value="kycbio">KYC Using BIO</option>
											<option value="kycotp">KYC Using OTP</option>
											<option value="otp">OTP</option>
											<option value="bfd">BFD</option>
										</select>
									</div></td>
								<td><div class="form-group">
										<label for="sel1">Select Type:</label><font color="red">
											*</font> <select class="form-control" id="responsetype"
											name="responsetype">
											<option value="success">Success</option>
											<option value="failure">Failure</option>
											<option value="both">Any</option>
										</select>
									</div></td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
										<label for="sel1">Select Number of Transactions:</label><font
											color="red"> *</font> <select class="form-control"
											id="records" name="records">
											<option value="100">100</option>
											<option value="200">200</option>
											<option value="300">300</option>
											<option value="400">400</option>
											<option value="500">500</option>
										</select>
									</div>
								</td>
								<td>
								<span class="label label-info"> Kindly contact help desk if u need records more than 500</span>
								</td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
										<label>From Date<font color="red"> *</font>
										</label>
										<div class='input-group date' id='datetimepicker1'>
											<input name="fromDate" id="fromDate"
												 class="form-control" />

										</div>

									</div>
								</td>
								<td>
									<div class="form-group">
										<label>To Date<font color="red"> *</font>
										</label>
										<div class='input-group date' id='datetimepicker2'>
											<input name="toDate" id="toDate" 
												class="form-control" />
										</div>


									</div>
								</td>
							</tr>
							<tr>
							<td colspan="2">
								<div class="row">
									<p id="dateErrorMessage" class="text-danger"></p>
								</div>
								</td>
							</tr>
						</table>
						<button type="submit"   class="btn btn-success">Download CSV</button>
						<button type="button" onclick="location.href = '/krdh/home'"
							class="btn btn-primary">Cancel</button>

					</form>


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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css">
<script type="text/javascript">

	$(function() {
		$('input[name="fromDate"]').daterangepicker({
			locale : {
				format : 'DD-MM-YYYY'
			},
			singleDatePicker : true,
			showDropdowns : true,
			maxDate : new Date(),

		});
		$('input[name="toDate"]').daterangepicker({
			locale : {
				format : 'DD-MM-YYYY'
			},
			singleDatePicker : true,
			showDropdowns : true,
			maxDate : new Date(),

		});
	});
	function checkdate() {

		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var fromDateArr = fromDate.split("-");
		var toDateArr = toDate.split("-");
		var date2 = new Date(toDateArr[2], toDateArr[1], toDateArr[0]);
		var date1 = new Date(fromDateArr[2], fromDateArr[1], fromDateArr[0]);

		if (date1 > date2) {
			$("#dateErrorMessage")
					.html(
							"<div class='text-danger'>FROM date should be smaller than TO date</div>");
			return false;
		} else {
			return true;
		}

	}

	$(document).ready(function() {

		$("#updateksalk").validate({
			rules : {
				fromDate : {
					required : true,
				},
				toDate : {
					required : true,
				}
			},
			messages : {
				fromDate : {
					required : "Please Pick Date ",
				},
				toDate : {
					required : "Please Pick Date ",
				},

			},
			errorElement : "span",
			errorPlacement : function(error, element) {
				console.log(error + " " + element);
				if (element.prop("type") === "checkbox") {
					error.insertAfter(element.parent("label"));
				} else {
					error.insertAfter(element);
				}
			},

		});
	});
</script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>

<%@ include file="footer.jsp"%>