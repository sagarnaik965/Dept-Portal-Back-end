<%@ include file = "header.jsp" %>
  <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Downloads</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							<h4>Reference Links</h4> 
                        </div>
						<div class="panel-body">
						
							<a href='<c:url value ="https://uidai.gov.in/authentication/authentication-devices-documents/devices.html"/>'><h5>Authentication Devices</h5></a>
							
							<br>
							
							<a href='<c:url value ="https://uidai.gov.in/resources/authentication-and-fi-documents.html"/>'><h5>Authentication Documents</h5></a>
							
							<br>
							
							<a href='<c:url value ="https://uidai.gov.in/authentication/authentication-devices-documents/biometric-devices.html	"/>'><h5>Biometric Devices </h5></a>
							
						</div>
                    </div>
                </div>
             
			 <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							<h4>Connector Download</h4>
                        </div>
						<!-- <div class="panel-body">
						<a href='<c:url value ="/downloadfile/integrationkit.zip"/>' target="_blank"><h5> Integration Kit (Old Version) </h5></a> -->
						
						<br> 
						
						<a href='<c:url value ="/downloadfile/integrationkit2_5.zip"/>' target="_blank"><h5>Integration Kit 2.5</h5></a>
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

<%@ include file = "footer.jsp" %>
