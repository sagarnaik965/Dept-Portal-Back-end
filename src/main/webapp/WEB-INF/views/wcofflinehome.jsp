<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Home</title>
<c:url value="/resources/wc/images/ok.png"  var="okImage"/>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <c:url value="/resources/wc/css/offline-home.css" var="offline_home" />
<link href="${offline_home }" rel="stylesheet">
</head>

<body>

	<div id="page-wrapper" style="padding-top: 10px;">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading text-center">
						<h4>Offline Web Client</h4>
					</div>
					<div class="panel-body">
						<div class="container">
		<div class="row mb"> 

			<div class="col-md-6" id="qrcode" class="result">
				<!-- Search Widget -->
				<div class="panel panel-default tp-1 cb qr" id="resultdiv">
					<div class="panel-heading text-center" style="padding-top: 5px; padding-bottom: 5px;">
						<h4>QR CODE / XML Scanning</h4>
					</div>
					<div class="panel-body">

						<!-- button for file start -->
						<c:url value="/client/singleUpload?${_csrf.parameterName}=${_csrf.token}" var="singleUpload" />
												<!-- button for file start -->
						<form:form method="POST" enctype="multipart/form-data"
							action="${ singleUpload}">

							<div class="col-md-12 qr-block">
								<div class="mid">


									<span class="btn btn-primary btn-file"><i
										class="fa fa-upload fa-qr" aria-hidden="true"><input
											type="file" id="check" name="qRCodeData" value="ValidateData"
											accept="image/x-png,image/jpeg,image/jpg"
											onchange="this.form.submit()"></i></span>
								</div>

								<div>
									<p>Scan  QR Code File</p>
								</div>


							</div>


						</form:form>

						<!-- button for file end -->


						<!-- button for scanner start -->
						
						<!-- <form:form method="POST" enctype="multipart/form-data"
							action="singleUploadqrcodescanner">


							<div class="col-md-12 qr-block">

								<div class="mid">
									<span class="btn btn-primary btn-file "><i
										class="fa fa-qrcode fa-qr" aria-hidden="true"><input
											type="file" id="check" name="qRCodeData" value="ValidateData"
											accept="image/x-png,image/jpeg,image/jpg"></i> </span>
								</div>


								<div>
									<p>Scan From QR Code Reader</p>
								</div>
							</div>

						</form:form> -->

						<!-- button for scanner end -->


						<!-- button for xml file start -->
						<c:url value="/client/singleUploadxmlfile?${_csrf.parameterName}=${_csrf.token}" var="singleXMLUpload" />
						<form:form method="POST" enctype="multipart/form-data"
							action="${singleXMLUpload }">


							<div class="col-md-12 qr-block">

								<div class="mid">
									<span class="btn btn-primary btn-file "><i
										class="fa fa-file-code-o fa-qr" aria-hidden="true"><input
											type="file" id="check" name="xmlfileData"
											value="ValidateData" accept="text/xml"
											onchange="this.form.submit()"></i> </span>
								</div>
								<div>
									<p>Scan  XML File</p>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>

			<!-- Result for not a uidai complaint exception  -->

			<c:if test="${not empty msg}">
				<div class="col-md-6" id="result" class="result">
					<!-- Search Widget -->
					<div class="panel panel-default tp-1 cb" id="resultdiv">
						<h4 class="panel-heading text-center">
							<i class="fa fa-remove pull-right"
								id="clear"></i></a>
							Result <a href="wcofflinehome">
						</h4>

						<div class="panel-body">

							<font size="4" color="Tomato"><strong><p
										id="result">${msg}</strong></font>
						</div>
					</div>
				</div>
			</c:if>

			<!-- Result for demographic and xml details  -->
			<c:if test="${not empty invalidxmlfile}">
				<div class="col-md-6" id="result" class="result">
					<!-- Search Widget -->
					<div class="panel panel-default tp-1 cb" id="resultdiv">
						<h5 class="panel-heading text-center">
							Result <a href="wcofflinehome"><i class="fa fa-remove pull-right"
								id="clear"></i></a>
						</h5>

						<div class="panel-body">
							<!-- <i class="far fa-frown" style="font-size:36px"></i> -->
							<font size="4" color="Tomato"><strong><p
										id="result">${invalidxmlfile}</strong></font>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty details}">

				<div class="col-md-6" id="result" class="result">
					<!-- Search Widget -->
					<div class="panel panel-default tp-1 cb" id="resultdiv">
						<div class="panel-heading text-center" style="padding-top: 5px; padding-bottom: 5px;">
							<a href="wcofflinehome"><i
								class="fa fa-remove pull-right" id="clear"></i></a>
							<h4> ${details} </h4> 
						</div>

						<div class="panel-body">

							<font size="6"><strong><p id="result"></p></strong></font>
							<div class="row">
								<div class="column">
									<c:if test="${not empty demoauth.photo}">
										<p id="result">
											<img alt="img" src="data:image/jpeg;base64,${demoauth.photo}"
												style="height: 100px;" />
										</p>
									</c:if>
								</div>
								<div class="column">
									<c:if test="${not empty demoauth.photo}">
										<!-- <p id="result">
											<i class="fa fa-check-circle fa-5x" aria-hidden="true"> </i>
										</p> -->
										
										<img align="right" id="verifyimg"
											src="${okImage }" alt="Snow" style="width: 50%">
									</c:if>
								</div>
							</div>

							<div>
								<c:if test="${not empty demoauth.referenceId}">
									<p id="result">ReferenceId: ${demoauth.referenceId}</p>
								</c:if>
								<c:if test="${not empty demoauth.name}">
									<p id="result">AadharName : ${demoauth.name}</p>
								</c:if>
								<c:if test="${not empty demoauth.dateofbirth}">
									<p id="result">Dateofbirth : ${demoauth.dateofbirth}</p>
								</c:if>
								<c:if test="${not empty demoauth.gender}">
									<p id="result">Gender :${demoauth.gender}</p>
								</c:if>
								<c:if test="${not empty demoauth.careof}">
									<p id="result">Careof :${demoauth.careof}</p>
								</c:if>
								<c:if test="${not empty demoauth.district}">
									<p id="result">District :${demoauth.district}</p>
								</c:if>
								<c:if test="${not empty demoauth.landmark}">
									<p id="result">Landmark :${demoauth.landmark}</p>
								</c:if>
								<c:if test="${not empty demoauth.house}">
									<p id="result">House :${demoauth.house}</p>
								</c:if>
								<c:if test="${not empty demoauth.location}">
									<p id="result">Location :${demoauth.location}</p>
								</c:if>
								<c:if test="${not empty demoauth.pincode}">
									<p id="result">Pincode :${demoauth.pincode}</p>
								</c:if>
								<c:if test="${not empty demoauth.postoffice}">
									<p id="result">Postoffice :${demoauth.postoffice}</p>
								</c:if>
								<c:if test="${not empty demoauth.state}">
									<p id="result">State :${demoauth.state}</p>
								</c:if>
								<c:if test="${not empty demoauth.street}">
									<p id="result">Street :${demoauth.street}</p>
								</c:if>
								<c:if test="${not empty demoauth.subdistrict}">
									<p id="result">Subdistrict :${demoauth.subdistrict}</p>
								</c:if>
								<c:if test="${not empty demoauth.VTC}">
									<p id="result">VTC :${demoauth.VTC}</p>
								</c:if>
								<!------------------ QR code email and mobile buttons start ----------------------->
								<c:if test="${not empty demoauth.referenceId}">
									<div class="container">
										<!-- Trigger the modal with a button -->
										<!--  <button type="button" class="btn btn-primary btn-email"
											data-toggle="modal" data-target="#myModalemail" style="margin-top: 20px;margin-left: 35px;">Validate
											E-mail id</button>  -->

										<form:form method="POST" action="QRemailvalidate">
											<!-- Modal -->
											<div class="modal fade" id="myModalemail" role="dialog">
												<div class="modal-dialog modal-sm">
													<div class="modal-content">
														<div class="modal-header">
															<span> <label for="UIDdataPoiemail"><font color="337ab7"><strong>Validate
																		Email ID</font></strong></label>
																<button type="button" class="close bttn-close" data-dismiss="modal">&times;</button>
															</span>
														</div>
														<div class="modal-body">
															<div class="form-group">
																<!-- <label for="UIDdataPoiemail"><strong>Validate Email ID</strong></label> -->
																<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
																<input type="text" name="UIDdataPoiemail"
																	path="UIDdataPoiemail" id="UIDdataPoiemail"
																	class="form-control" placeholder="enter email id"
																	pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
																	required="required">
															</div>
															<div id="emailsuccsess"></div>
														</div>
														<div class="modal-footer">
															<input type="hidden" name="emailrefrence"
																id="emailrefrence" value="${demoauth.referenceId}" /> <input
																type="hidden" name="emailhash" id="emailhash"
																value="${demoauth.emailIdHash}" />

															<button type="button" class="btn btn-primary btn-sm"
																id="QRemailvalidate">Validate</button>

														</div>
													</div>
												</div>
											</div>
										</form:form>
										<!-- Modal -->
										<div class="modal fade" id="myModalmobile" role="dialog">

											<div class="modal-dialog modal-sm">
												<div class="modal-content">
													<div class="modal-header">
														<span> <label for="UIDdataPoimobile"><font color="337ab7"><strong>Validate
																	Mobile No</font></strong></label>
															<button type="button" class="close bttn-close" data-dismiss="modal">&times;</button>
														</span>
													</div>
													<div class="modal-body">
														<div class="form-group">
															<!-- <label for="UIDdataPoimobile"><strong>Validate Mobile No</strong></label> -->
															<input type="text" path="UIDdataPoimobile"
																name="UIDdataPoimobile" id="UIDdataPoimobile"
																class="form-control mb-2"
																placeholder="Enter mobile number" maxlength="10"
																oninput="this.value=this.value.replace(/[^0-9]/g,'');"
																required>
														</div>
														<div id="mobilesuccsess"></div>
													</div>
													<div class="modal-footer">
														<input type="hidden" name="mobilerefrence"
															id="mobilerefrence" value="${demoauth.referenceId} " />
														<input type="hidden" name="mobilehash" id="mobilehash"
															value="${demoauth.mobileNumberHash}" />
														<button type="button" class="btn btn-primary btn-sm"
															id="QRmobilevalidate">Validate</button>



													</div>
												</div>
											</div>
										</div>
									</div>
								</c:if>
								<!------------------------------ popup QRCODE mobile end------------------------------>


								<!-- ------------------QR code email and mobile buttons endddd------------------- -->



								<!------------ 	xmlfile data below in result start -------------->

								<div class="row">
									<div class="column">
										<c:if test="${not empty photo}">
											<p id="result">
												<img alt="img" src="data:image/jpeg;base64,${photo}"
													style="height: 100px;" />
											</p>
										</c:if>
									</div>
									<div class="column">
										<c:if test="${not empty photo}">
											<p id="result">
												<!--  <p>Aadhar Data <p><i class="fa fa-check-circle fa-5x" aria-hidden="true">
												</i><p>Verified</p> -->
												<img src="${okImage }" id="verifyimg" alt="Snow"
													style="width: 50%">
											</p>
										</c:if>
									</div>
								</div>
								<c:if test="${not empty referenceId}">
									<p id="result">ReferenceId: ${referenceId}</p>
								</c:if>


								<c:if test="${not empty UIDdataPoi.name}">
									<p id="result">AadharName :${UIDdataPoi.name}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoi.dob}">
									<p id="result">Dateofbirth :${UIDdataPoi.dob}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoi.gender}">
									<p id="result">Gender :${UIDdataPoi.gender}</p>
								</c:if>
								<c:if test="${not empty UIDdataPoa.dist}">
									<p id="result">District :${UIDdataPoa.dist}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoa.landmark}">
									<p id="result">Landmark :${UIDdataPoa.landmark}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoa.loc}">
									<p id="result">Loc :${UIDdataPoa.loc}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoa.pc}">
									<p id="result">PinCode :${UIDdataPoa.pc}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoa.po}">
									<p id="result">Postoffice :${UIDdataPoa.po}</p>
								</c:if>

								<c:if test="${not empty UIDdataPoa.state}">
									<p id="result">State :${UIDdataPoa.state}</p>
								</c:if>
								<c:if test="${not empty UIDdataPoa.vtc}">
									<p id="result">VTC :${UIDdataPoa.vtc}</p>
								</c:if>

								<!------------------------------ popup email start------------------------------>

								<c:if test="${not empty referenceId}">
									<div class="container">
										<form:form method="POST" action="validateemail">
											<!-- Modal -->
											<div class="modal fade" id="myModalemail" role="dialog">
												<div class="modal-dialog modal-sm">
													<div class="modal-content">
														<div class="modal-header">
															<span> <label  for="UIDdataPoiemail"><strong><font color="#337ab7">Validate
																		Email Id & Passcode</font></strong></label>
																<button type="button" class="close bttn-close" data-dismiss="modal">&times;</button>
															</span>
														</div>
														<div class="modal-body">
															<div class="form-group">
																<label for="UIDdataPoiemail"><strong>Validate
																		Email Id</strong></label> 
																		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
																		<input type="text" name="UIDdataPoiemail"
																	path="UIDdataPoiemail" id="UIDdataPoiemail"
																	class="form-control" placeholder="Enter e-mail id"
																	pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
																	required="required">
															</div>
															<div class="form-group">
																<label for="passcode"><strong>Passcode</strong></label>
																<input type="text" path="passcode" name="passcode"
																	id="passcode" class="form-control"
																	placeholder="Enter passcode/sharePhrase" maxlength="4"
																	oninput="this.value=this.value.replace(/[^0-9]/g,'');"
																	required>
															</div>

															<div id="emailsuccsess"></div>


														</div>
														<div class="modal-footer">
															<input type="hidden" name="emailrefrence"
																id="emailrefrence" value="${referenceId}" /> <input
																type="hidden" name="emailhash" id="emailhash"
																value="${UIDdataPoi.e}" />

															<button type="button" class="btn btn-primary btn-sm"
																id="emailvalidate">Validate</button>

														</div>
													</div>
												</div>
											</div>
										</form:form>

										<!------------------------------ popup email end ------------------------------>


										<!------------------------------ popup xml file mobile start------------------------------>
										<form:form method="POST" action="validatemobile">
											<!-- Modal -->
											<div class="modal fade" id="myModalmobile" role="dialog">
												<div class="modal-dialog modal-sm">
													<div class="modal-content">
														<div class="modal-header">
															<span> <label for="UIDdataPoimobile"><strong><font color="#337ab7">Validate
																		Mobile No & Passcode </font></strong></label>
																<button type="button" class="close bttn-close" data-dismiss="modal">&times;</button>
															</span>
														</div>
														<div class="modal-body">
															<div class="form-group">
																<label for="UIDdataPoimobile"><strong>Validate
																		Mobile No</strong></label> 
																		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
																		<input type="text" path="UIDdataPoimobile"
																	name="UIDdataPoimobile" id="UIDdataPoimobile"
																	class="form-control mb-2"
																	placeholder="Enter mobile number" maxlength="10"
																	oninput="this.value=this.value.replace(/[^0-9]/g,'');"
																	required>
															</div>
															<div class="form-group">
																<label for="passcodem"><strong>Passcode</strong></label>
																<input type="text" path="passcodem" name="passcodem"
																	id="passcodem" class="form-control"
																	placeholder="Enter passcode/sharephrase" maxlength="4"
																	oninput="this.value=this.value.replace(/[^0-9]/g,'');"
																	required>
															</div>

															<div id="mobilesuccsess"></div>
														</div>
														<div class="modal-footer">
															<input type="hidden" name="mobilerefrence"
																id="mobilerefrence" value="${referenceId} " /> <input
																type="hidden" name="mobilehash" id="mobilehash"
																value="${UIDdataPoi.m}" />
															<button type="button" class="btn btn-primary btn-sm"
																id="mobilevalidate">Validate</button>



														</div>
													</div>
												</div>
											</div>
										</form:form>
									</div>
								</c:if>

								<!--------------------------------	popup  mobile end------------------------------>
								<!-- 	xmlfile data below in result end -->


							</div>
						</div>
					</div>
			</c:if>

		</div>

	</div> 
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- jQuery -->
<c:url value="/resources/" var="jsfile" />
<script src="${resources}vendor/jquery/jquery.min.js"></script>
<script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
<script src="${resources}dist/js/sb-admin-2.js"></script>
        <%@ include file="footer.jsp"%>
