<%@ include file="header.jsp"%>
<style>
div {
	border: 0px solid red;
}
</style>
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
					style="padding-top: 1px; border: 1px; height: 35px; width: 100%;">
					<h4>Select Time Period for Report</h4>
				</div>
				<br>
				<div class="panel-body ">
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
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">

							<c:url value="/getreport" var="url" />

							<form:form role="form" method="post" action="${url}"
								onsubmit="return checkdate();">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div>
									<table class="table table-striped table-bordered table-hover">

										<tr>

											<td style="width: 7%;"><input type="radio" name="period"
												value="current" checked /><br>
											<td style="width: 23%;"><label
												style="width: 55px; float: left;">Monthly</label></td>
											<td style="width: 70%;"><label
												style="width: 55px; float: left; color: #797979;">Month</label>
												<select style="width: 167px; float: left;"
												class="form-control" id="selectMonthlyMonth" name="selectMonthlyMonth">
													<option value="JAN">January</option>
													<option value="FEB">February</option>
													<option value="MAR">March</option>
													<option value="APR">April</option>
													<option value="MAY">May</option>
													<option value="JUN">June</option>
													<option value="JUL">July</option>
													<option value="AUG">August</option>
													<option value="SEP">September</option>
													<option value="OCT">October</option>
													<option value="NOV">November</option>
													<option value="DEC">December</option>
											</select> <label
												style="width: 42px; float: left; margin-left: 18px; color: #797979;">Year</label>
												<select style="width: 150px; float: left;"
												class="form-control" id="selectMonthlyYear" name="selectMonthlyYear">
											</select></td>
										</tr>
										<tr>

											<td><input type="radio" name="period" value="threemonth" /><br>
											<td style="width: 23%;"><label
												style="width: 55px; float: left;">Quarterly</label></td>
											<td><label
												style="width: 55px; float: left; color: #797979;">Quarter</label>
												<select style="width: 168px; float: left;"
												class="form-control" id="selectQuarterlyQuarter" name="selectQuarterlyQuarter">
													<option value="JAN">January-March</option>
													<option value="APR">April-June</option>
													<option value="JUL">July-September</option>
													<option value="OCT">October-December</option>
											</select> <label
												style="width: 60px; float: left; padding-left: 20px; color: #797979;">Year</label>
												<select style="width: 150px; float: left;"
												class="form-control" id="selectQuarterlyYear" name="selectQuarterlyYear">
											</select></td>
										</tr>
										<tr>

											<td><input type="radio" name="period" value="sixmonth" /><br>
											<td style="width: 23%;"><label
												style="width: 55px; float: left;">Yearly</label></td>
											<td><label
												style="width: 56px; float: left; color: #797979;">Year</label>
												<select style="width: 167px; float: left;"
												class="form-control" id="selectYearlyYear" name="selectYearlyYear">
											</select></td>
										</tr>
										<tr>

											<td><input id="custom" type="radio" name="period"
												value="custom" /><br>
											<td style="width: 23%;"><label
												style="width: 135px; float: left;">Custom Period</label></td>
											<td><label
												style="width: 56px; float: left; color: #797979;">From</label>
												<input style="float: left; width: 167px;"
												placeholder="From date" class="form-control" name="fromDate"
												id="fromDate" /> <label
												style="width: 56px; float: left; color: #797979; padding-left: 20px;">To</label>
												<input style="float: left; width: 150px;"
												placeholder="To  date" class="form-control" name="toDate"
												id="toDate" /></td>
										</tr>


									</table>
								</div>
								<div class="row">

									<div id="twoYearsWarn" class='text-info' style='display:none;color: blue;margin-left: 14px;'>Report can be generated for maximun range of two years (FROM DATE to TO DATE)</div>

									<p id="errormess" class="text-danger"
										style="margin-left: 14px;"></p>

									<hr>
									<div class="col-lg-12" align="center">
										<button type="submit" class="btn btn-success">Download</button>

										<button type="button" onclick="location.href = '/auaportal/home'"
											class="btn btn-primary">Cancel</button>
									</div>
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
<script type="text/javascript" src="${resources}vendor/bootstrap/js/moment.min.js"></script>
<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">

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
		var c = document.getElementById("custom").checked;

		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var fromDateArr = fromDate.split("-");
		var toDateArr = toDate.split("-");
		var date2 = new Date(toDateArr[2], toDateArr[1], toDateArr[0]);
		var date1 = new Date(fromDateArr[2], fromDateArr[1], fromDateArr[0]);
		
		const diffTime = Math.abs(date2 - date1);
		const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
		
		if (c) {
			//alert("custom");
			if (date1 > date2) {
				$("#errormess")
						.html(
								"<div class='text-danger' style='color:red'>FROM date should be smaller than TO date</div>");
				 $('#errormess').show();
				return false;
			} else if(diffDays>730) 
			{
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
<script src="${resources}vendor/jquery/jquery.validate.min.js"></script>

<script>
	$(document).ready(
			function() {

				var startYear = 2018;
				var d = new Date();
				var currentYear = d.getFullYear();
				console.log(currentYear - startYear);

				var selectMonthlyYear = document
						.getElementById('selectMonthlyYear');
				var selectQuarterlyYear = document
						.getElementById('selectQuarterlyYear');
				var selectYearlyYear = document
						.getElementById('selectYearlyYear');
				var newOption1;
				var newOption2;
				var newOption3;

				for (currentYear; currentYear >= startYear; currentYear--) {
					newOption1 = document.createElement("option");
					newOption1.text = currentYear;
					newOption1.value = currentYear;
					selectMonthlyYear.appendChild(newOption1);

					newOption2 = document.createElement("option");
					newOption2.text = currentYear;
					newOption2.value = currentYear;
					selectQuarterlyYear.appendChild(newOption2);

					newOption3 = document.createElement("option");
					newOption3.text = currentYear;
					newOption3.value = currentYear;
					selectYearlyYear.appendChild(newOption3);
				}

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
<%@ include file="footer.jsp"%>
