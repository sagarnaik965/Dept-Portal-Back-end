<div id="footer">
	<div class="row">

		<div class="col-md-3 text-center"></div>

		<div class="col-md-2 text-center">
			<a href='<c:url value ="/aboutus"/>'>About us</a>
		</div>

		<sec:authorize var="loggedIn" access="isAuthenticated()" />
		<c:choose>
			<c:when test="${loggedIn}">
				<div class="col-md-2 text-center">
					<a href='<c:url value ="/aboutus"/>'>Help</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-2 text-center">
					<a href='<c:url value ="/aboutus"/>'>Help</a>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="col-md-2 text-center">
			<a href='<c:url value ="/contactus"/>'>Contact Us</a>
		</div>

		<div class="col-md-3 text-center"></div>

	</div>



	<div class="row" style="padding-top: 5px">
		<div class="col-md-2  text-center">
		</div>
		<div class="col-md-8 text-left text-center">
			<p style="color: white;">Copyright © 2023 C-DAC Mumbai. All
				Rights Reserved.</p>
		</div>
		<div class="col-md-2  text-center ">
			 				<a>Last updated on : 30/05/2023</a> 
		</div>
	</div>
</div>


