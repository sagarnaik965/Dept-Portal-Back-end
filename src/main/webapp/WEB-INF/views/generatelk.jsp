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
					<h4>Generate License Key</h4>
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
					<form action="generatesalk" id="frm-example" method="post">
						<table id="example" width="100%"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>
									<th>Application Name</th>
									<th>Access Code</th>
									<!-- <th>License Key</th> -->
									<th>License Key Validity</th>
								</tr>
							</thead>

						</table>

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="col-md-12 text-center">
							<button name="login_active" id="login_active" value="active"
								class="btn btn-success">Generate</button>
							<!-- <button name="login_deactive" id="login_deactive"
								value="deactive" class="btn btn-primary btn-space">Cancel</button> -->
							<button type="button" onclick="location.href = '/home'"
								class="btn btn-primary">Cancel</button>
						</div>

					</form>

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
<c:url value="/resources/" var="resources" />
<script src="${resources}vendor/jquery/jquery.min.js"></script> 
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script> 
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script>
<script src="${resources}vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${resources}vendor/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${resources}vendor/datatables-responsive/dataTables.responsive.js"></script>
<script src="${resources}vendor/bootstrap/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="${resources}vendor/bootstrap/css/dataTables.checkboxes.css" rel="stylesheet" />
<script src="${resources}vendor/bootstrap/js/dataTables.checkboxes.min.js"></script>
<script src="${resources}vendor/bootstrap/js/bootstrap-toggle.min.js"></script>
<link href="${resources}vendor/bootstrap/css/bootstrap-toggle.min.css" rel="stylesheet" />
<script type="text/javascript" src="${resources}vendor/bootstrap/js/daterangepicker.js"></script>
<link rel="stylesheet" href="${resources}vendor/bootstrap/css/daterangepicker.css">
<script src="${resources}vendor/bootstrap/js/jquery-ui.js"></script>
<link href="${resources}vendor/bootstrap/css/jquery-ui.css" rel="stylesheet" type="text/css">


<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var data = eval('${userstatus}');
						//console.log(data);
						var table = $('#example')
								.DataTable(
										{

											"aaData" : data,
											"aoColumns" : [
													{
														"mData" : 'id',
														'name' : 'dept_id'
													},
													{
														"mData" : "deptName",
														"mRender" : function(
																data, type,
																full) {
															//return "<a href='#' class='link-details'>"
															//		+ data
															//		+ "</a>"
																     return "<a>"+data+"</a>"
														}
													}, {
														"mData" : "auaCode"
													},
											/* {
												"mData" : "auaCode",
												"className" : "text-center"

											}, */],

											/* ,"columnDefs": [ {
											    className: 'dt-body-center',
											     targets:   2,
											     defaultContent: "<button>Click!</button>"
											 } ]  */

											'columnDefs' : [
													{

														'targets' : 0,
														'checkboxes' : {
															'selectRow' : true
														}
													},
													{
														"targets" : 3,
														"mData" : "validTill",
														"mRender" : function(
																data, type,
																full) {

															if (data == null) {
																return '<input disabled="disabled"  style="width:98px;font-size:14px"  autocomplete="off" class="form-control " name="validTill" placeholder="Vaild Till" />';
															} else {

																var date = new Date(
																		data);
																var day = date
																		.getDate();

																if (day < 10)
																	day = '0'
																			+ day;

																var month = date
																		.getMonth() + 1;
																if (month < 10)
																	month = '0'
																			+ month;
																var yyyy = date
																		.getFullYear();
																var stringDate = day
																		+ '/'
																		+ month
																		+ '/'
																		+ yyyy;
																data = stringDate;

																return '<input disabled="disabled"  style="width:98px;font-size:14px"  autocomplete="off" class="form-control " name="validTill" placeholder="Vaild Till" value='+data+' />';
															}
														}

													},
													/* {
														"width" : "20%",
														"targets" : 4
													},
													{
														"width" : "100px",
														"targets" : 3
													}, */
													{
														"className" : "dt-center",
														"targets" : "_all"
													}

											],
											"paging" : false,

											'select' : {
												'style' : 'multi'
											},
											'order' : [ [ 1, 'asc' ] ],

											"initComplete" : function(settings,
													json) {
												$('input[name="validTill"')
														.datepicker(
																{
																	changeMonth : true,
																	changeYear : true,
																	dateFormat : 'dd/mm/yy',
																	minDate : new Date(),
																});
												//alert('DataTables has finished its initialisation.');
											}

										});
						var csrfParameter = $("meta[name='_csrf_parameter']")
								.attr("content");
						var csrfHeader = $("meta[name='_csrf_header']").attr(
								"content");
						var csrfToken = $("meta[name='_csrf']").attr("content");
						var headers = {};
						headers[csrfHeader] = csrfToken;

						$("#example tbody").on(
								'click',
								'.link-details',
								function() {
									var dept_details = table.row(
											$(this).parents('tr')).data();
									var data = {};
									data[csrfParameter] = csrfToken;
									data["userName"] = dept_details.userName;
									$.ajax({
										url : 'deptdetails',
										type : 'POST',
										data : data, //'userName='+dept_details.userName,
										dataType : 'html'
									}).done(function(data) {

										$('#dynamic-content').html('');
										$('#dynamic-content').html(data);

									});
									$("#DescModal").modal('show');
								});

						$('#login_active, #login_deactive')
								.on(
										'click',
										function(e) {
											// var rows_selected = table.column(0).checkboxes.selected();

											var tableControl = document
													.getElementById('example');
											var obj = new Array();
											var returnVar = true;

											$('.dt-checkboxes:checked',
													tableControl)
													.each(
															function() {

																var validDate = $(
																		this)
																		.parent()
																		.next()
																		.next()

																		.next()
																		.children()
																		.val();

																if (validDate == null
																		|| validDate == '') {
																	alert('Please enter correct Vaild Till Date');
																	returnVar = false;
																	return false;
																}

																var username = $(
																		this)
																		.parent()
																		.next()
																		.next()
																		.text();

																//	alert("username :"+username );
																if (username != 'Username') {
																	obj
																			.push(username);

																	$(
																			'#frm-example')
																			.append(
																					$(
																							'<input>')
																							.attr(
																									'type',
																									'hidden')
																							.attr(
																									'name',
																									'users')
																							.attr(
																									'id',
																									'users')
																							.val(
																									username));
																}

																var dateValueList = $(
																		this)
																		.parent()
																		.parent()
																		.find(
																				'[name="validTill"]')
																		.val();
																//alert(dateValueList);

																if (dateValueList != 'Valid Till') {
																	obj
																			.push(dateValueList);

																	$(
																			'#frm-example')
																			.append(
																					$(
																							'<input>')
																							.attr(
																									'type',
																									'hidden')
																							.attr(
																									'name',
																									'dateValueList')
																							.attr(
																									'id',
																									'dateValueList')
																							.val(
																									dateValueList));
																}

															});

											if (!returnVar)
												return false;

											if ($('#users').val() != null) {
												$('#frm-example').submit();
											} else {
												alert("Please select atleast 1 department.");
												return false;
											}

										});
						//logic to add
						$('input[type="checkbox"]')
								.click(
										function() {

											var tableControl = document
													.getElementById('example');

											var accode;
											var acCodeElement;

											$('input:checkbox:checked',
													tableControl)
													.each(
															function() {

																if ($(this)
																		.attr(
																				'name') == 'loginStatusList') {
																	//alert("skipping others");
																} else {
																	acCodeElement = $(
																			this)
																			.parent()
																			.next()
																			.next()
																			.next();

																	$(this)
																			.parent()
																			.parent()
																			.find(
																					'*')
																			.attr(
																					'disabled',
																					false);
																}
															});

											$("input:checkbox:not(:checked)",
													tableControl)
													.each(
															function() {

																if ($(this)
																		.attr(
																				'name') == 'loginStatusList') {

																} else {

																	$(this)
																			.parent()
																			.next()
																			.next()
																			.next()
																			.find(
																					'*')
																			.attr(
																					'disabled',
																					true);

																}
															});

										});
						//logic to add
						document.getElementById("example").rows[0].cells[0].innerHTML = 'Check';

						/* 						$(function() {
						 $(
						 '[name="loginStatusList"]')
						 .bootstrapToggle({
						 on : 'Active',
						 off : 'Deactive',
						 onstyle : 'success',
						 offstyle : 'danger',

						 });
						 }); */

						/*  $(function() {
							$('input[name="validTill"').daterangepicker({
								singleDatePicker : true,
								showDropdowns : true,
								locale : {
									format : 'DD-MM-YYYY'
								},
								maxDate : new Date(),

							});
						});  */

					});
</script>
<!-- <script type="text/javascript">
            $(function () {
                $('input[name="validTill"').daterangepicker();
            });
        </script> -->

<div class="modal fade" id="DescModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">X</button>
				<h3 class="modal-title">Generate License Key</h3>

			</div>
			<div class="modal-body">
				<!-- <h5 class="text-center">Hello. Below is the descripton and/or requirements for hiring consideration.</h5> -->
				<div id="dynamic-content"></div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<%@ include file="footer.jsp"%>
