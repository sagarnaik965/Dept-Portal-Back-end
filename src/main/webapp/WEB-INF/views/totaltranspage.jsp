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
					<h4>Total Transactions Details</h4>
				</div>

				<div class="panel-body">

					<div class="table-responsive">
					<table class="table table-bordered w-auto">
						<caption>Transactions</caption>
						<thead>
							<tr >
								<th scope="col" rowspan="2" class="blue-grey lighten-4">Authentication</th>
								<th scope="col" rowspan="2" class="blue-grey lighten-4">Demographic</th>
								<th scope="col" rowspan="2" class="blue-grey lighten-4">OTP</th>
								<th scope="col" colspan="2" class="blue-grey lighten-4">Biometric</th>
							</tr>
							<tr >
								<th scope="col" >Finger</th>
								<th scope="col">Iris</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">YES/NO</th>
								<td  class="table-danger"><a href="detailedtranscount?type=demoauth" >
								${count2.getuid}
								</a></td>
								<td><a  href="detailedtranscount?type=demootp" >
								${count2.demootp}
								</a></td>
								<td><a  href="detailedtranscount?type=demofinger" >
								${count2.demofinger}
								</a></td>
								<td><a  href="detailedtranscount?type=demoiris" >
								${count2.demoiris}
								</a></td>
							</tr>
							<tr >
								<th scope="row">e-Kyc</th>
								<td >Not Applicable</td>
								<td><a  href="detailedtranscount?type=kycotp" >
								${count2.kycotp}
								</a></td>
								<td><a  href="detailedtranscount?type=kycfinger" >
								${count2.kycfinger}
								</a></td>
								<td><a  href="detailedtranscount?type=kyciris" >
								${count2.kyciris}
								</a></td>
							</tr>
						</tbody>
					</table>
					</div>
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
<%@ include file="footer.jsp"%>