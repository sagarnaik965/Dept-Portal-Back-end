<%@ include file="header.jsp"%>
<style>
div{
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
			<div class="panel panel-default" >
				<div class="panel-heading text-center"  
						style="padding-top: 1px;  border: 1px; height:35px;width:100%;">
					<h4>Select Time Period for ASA AUA Failure Report</h4>
				</div>
				<div class="panel-body ">
				<c:choose>
								    <c:when test="${not empty errorMessage}">
									    <div class="alert alert-danger" role="alert">
									    	${errorMessage}
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
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							
							<c:url value="/getnetworkerrorreport" var="url" />
							
							<form:form role="form" method="post" action="${url}" onsubmit="return checkdate()">
							
							   <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}"/>
						<div>
						<table class="table table-striped table-bordered table-hover" >
						
						<tr>
						<td>Last Month</td><td> <input type="radio" name="period" value="current" checked/><br>
						</tr>
						<tr><td>Last Three Months</td><td><input type="radio" name="period" value="threemonth"/><br>
						</tr><tr><td>Last Six Months </td><td><input type="radio" name="period" value="sixmonth"/><br>
						</tr><tr><td>Custom Period </td><td><input id ="custom" type="radio" name="period" value="custom"/><br>
						</tr>
						
				
						</table>
						<div class="row"   >
						<div id="divDate" style='display:none'>
							<div class="col-lg-5">
							From : <input  placeholder="From date" class="form-control" name="fromDate" id="fromDate" />
							</div>
							<div class="col-lg-5">
							To :<input  placeholder="To  date" class="form-control" name="toDate" id="toDate" />
							</div>
							
						</div>
						</div>
						</div>
						<div class="row">
						<p id="errormess" class="text-danger">
						</p>
						<hr>
						<div class="col-lg-5">
						<button type="submit" class="btn btn-success">Download</button>
						
						<button type="button" onclick="location.href = '/krdh/home'" class="btn btn-primary">Cancel</button>
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/metisMenu/2.7.0/metisMenu.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/3.3.7+1/js/sb-admin-2.min.js"></script>
	<script type="text/javascript" src="http://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css">
	
<script>
var sdate;

$('input[type="radio"]').click(function() {
    if($(this).attr('id') == 'custom') {
         
         $('#divDate').show();
    }

    else {
         $('#divDate').hide();
    	
         
    }
});

$(function() {
	$('input[name="fromDate"').daterangepicker({
		singleDatePicker: true,
		showDropdowns: true	,
		locale: {
		      format: 'DD-MM-YYYY'
		    },
		    maxDate:new Date(),
		
	})
});
$(function() {
	$('input[name="toDate"').daterangepicker({
		singleDatePicker: true,
		showDropdowns: true,
		locale: {
		      format: 'DD-MM-YYYY'
		    },
		    maxDate:new Date(),
		
	})
	 
	
});

function checkdate()
{
	var a=document.getElementById('fromDate').value
	var b=document.getElementById('toDate').value
	var c=document.getElementById("custom").checked;
	
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	var fromDateArr = fromDate.split("-");
	var toDateArr = toDate.split("-");
	var date2 = new Date(toDateArr[2], toDateArr[1], toDateArr[0]);
	var date1 = new Date(fromDateArr[2], fromDateArr[1], fromDateArr[0]);
		if(c){
 			//alert("custom");
 			if (date1>date2) {
 				$("#errormess").html("<div class='text-danger'>FROM date should be smaller than TO date</div>");
 			   return false;  
 			}
			 else
				 {
		 		return true;
		 		 }
	
			}
 		else
 			{
 			return true;
 			}
 	}

</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
	
	<script>
	$(document).ready( function () {
				
		$("#updateksalk").validate({
			rules: {
				fromDate :
				{
					required: true,
				},
				toDate :
				{
					required: true,
				}
			},
			messages: {
				fromDate : {
					required:"Please Pick Date ",
							},
				toDate : {
					required:"Please Pick Date ",
				   		 },
				
					  },
			errorElement: "span",
			errorPlacement: function ( error, element ) {
				console.log(error+" "+element );
			if ( element.prop( "type" ) === "checkbox" ) {
					error.insertAfter( element.parent( "label" ) );
				} else {
					error.insertAfter( element );
				}
			},
			
		});
	});

	</script>


<%@ include file = "footer.jsp" %>