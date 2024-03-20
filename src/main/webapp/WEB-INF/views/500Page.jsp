<%@ include file = "header.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header text-danger" >Something went wrong! Please try after sometime </h4>
                    
                </div>
            </div>
        </div>

    </div>
     <c:url value="/resources/" var="resources" />
 <script src="${resources}vendor/jquery/jquery.min.js"></script>
 <script src="${resources}vendor/bootstrap/js/bootstrap.min.js"></script>
 <script src="${resources}vendor/metisMenu/metisMenu.min.js"></script>
 <script src="${resources}dist/js/sb-admin-2.js"></script>

<%@ include file = "footer.jsp" %>
