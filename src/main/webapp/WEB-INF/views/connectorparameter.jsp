<%@ include file="header.jsp"%>

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
					style="padding-top: 15px; padding-bottom: 15px;">
				
					<label style="font-size:20px;">Connector Parameters</label>
                                <div class="btn-group pull-right ">
                                   <button type="button" class="btn btn-primary center" id="download">
                                   Download as PDF
                                   </button>
                                    </div>
				</div>
				</div>
				<div class="panel-body">
					 <c:choose>
								    <c:when test="${not empty errormessage}">
									    <div class="alert alert-danger" role="alert">
									    	${errormessage}
									    </div>
									    
								    </c:when>
								       <c:when test="${not empty successMessage}">
									    <div class="alert alert-success" role="alert">
									    	${successMessage}
									    </div>
									    
								    </c:when>
								    <c:otherwise>
								      
								    </c:otherwise>
								</c:choose>
								<table  class="table table-striped table-bordered table-hover text-center" >
								
								<tbody>
								<tr>
								<td class="col-md-4"><b>AUA Code: </b></td>
										<td id="ac" class="col-md-8"></td>
										</tr>
								</tbody>
							
							</table>
							
							<table  class="table table-striped table-bordered table-hover text-center" >
								<thead>
									<tr>
										<th class="text-center">Licence Type</th>
										<th class="text-center">Clent Licence Key </th>
										<th class="text-center">Valid Till</th>
										
									</tr>
								</thead>
								<tbody id="lk" >
								</tbody>
							
							</table>
							<table id="example"  class="table table-striped table-bordered table-hover" >
								<thead>
									<tr>
										<th class="text-center">SR.No</th>
										<th class="text-center">UDC Code</th>
										
									</tr>
								</thead>
							</table>
					<div class="row">	    
						<div class="col-md-2 col-md-offset-5">	 
						</div>
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.0/js/responsive.bootstrap.min.js"></script>
<link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/css/dataTables.checkboxes.css" rel="stylesheet" />
<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.9/js/dataTables.checkboxes.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/2.3.2/jspdf.plugin.autotable.js"></script>

<script type="text/javascript">
$(document).ready(function() {
		var data = "";
		$.ajax({
			type : "GET",
			url : "downloadparameteronready",
			dataType : 'json',
			success : function(data) {
				console.log(data);
				var doc = new jsPDF();
				var dname=data.deptName;
				//dname= dname.replace(/\s/g,'');
				doc.setFontSize(10);
				doc.text(5, 5,"Deptartment : "+dname);
				
				doc.setFontSize(20);
				doc.text(20, 20,"Connector Parameters");
				
				doc.setFontSize(15);
				doc.text(15,30,"AUA code");
				var columnsAuaCode = [
				    {title: "Sr No.", dataKey: "srno"},
				    {title: "AUA Code", dataKey: "auacode"}
				    
				];
				var rowsAuaCode=[];
				rowsAuaCode.push({
			        'srno': "1",
			        'auacode':data.ac
			    });
				if(data.ac!=null)
				 $("#ac").append("<td>"+data.ac+"</td>");
				else
					$("#ac").append("<tr><td></td></tr>");

				doc.autoTable(columnsAuaCode, rowsAuaCode, {
				    
				    margin: {top: 32},
				    
				});
				
				doc.text(15, 60,"Licence keys");
				var rows = [];
				str = JSON.stringify(data.lkDetails);
				
				doc.setFontSize(40);
				for (var key in data.lkDetails) {
				   
				   var val = data.lkDetails[key];
				 
				   
				   $("#lk").append("<tr><td>"+val.licenseKeyType+"</td><td>"+val.clientLicensekey+"</td><td>"+val.validTill+"</td>");
				   rows.push({
				        'lt': val.licenseKeyType,
				        'clk': val.clientLicensekey,
				        'validtill':val.validTill
				    });
				   
				     
				}
				var columns = [
				    {title: "Type", dataKey: "lt"},
				    {title: "Client License Key", dataKey: "clk"},
				    {title: "Valid Till", dataKey: "validtill"}
				    
				    
				];
			    
				doc.autoTable(columns, rows, {
				    margin: {top: 62},
				}); 
				doc.setFontSize(15);
				doc.text(15,105,"UDC codes");
								
				var rowsUdc = [];
				for (var key in data.udc) {
					  if (data.udc.hasOwnProperty(key)) {
					    var val = data.udc[key];
					    rowsUdc.push({
					        'srno': key,
					        'udcode': val
					    });
					    
					  }
					}
			   var columnsUdc = [
				    {title: "Sr No.", dataKey: "srno"},
				    {title: "Udc Code", dataKey: "udcode"}
				    
				];

				doc.autoTable(columnsUdc, rowsUdc, {
				    
				    margin: {top: 108},
				    
				    
				});

				$('#download').click(function(){
					var filename=data.deptName;
					filename= filename.replace(/\s/g,'');
					doc.save(filename+'connectorParameters.pdf');	
				});
				
				var udcData=eval(data.udc);
				var dataPoints = [];
				console.log(data.udc);
				$.each(data.udc, function(key, value) {
					dataPoints.push({
						srno: key,
						udccode : value,
						});
				});
				console.log(dataPoints);
				
				var table = $('#example').DataTable( {
					  "aaData": dataPoints,
					  "aoColumns": [
						{"mData": 'srno'},
						{ "mData": "udccode"},
					  ],
					  "paging":true,"columnDefs": [
			               {"targets": 0, 
			            		"className": "text-center",},
			               {"targets": 1, 
			                		"className": "text-center",}
			        ]
					});
					
				
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				console.log(data);
			},
			done : function(e) {
				console.log("DONE");
				console.log(data);
			}
		});
		
    
	} );
</script>

<div class="modal fade" id="DescModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                 <h3 class="modal-title">Department Details</h3>

            </div>
            <div class="modal-body">
                  <div id="dynamic-content">
                  </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%@ include file = "footer.jsp" %>