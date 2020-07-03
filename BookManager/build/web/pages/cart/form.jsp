<%-- 
    Document   : form
    Created on : Nov 21, 2019, 8:29:02 PM
    Author     : Asus ZenBook
--%>

<%
    String formType = "View";
%>

<!--HEADER-->
<jsp:include page="/template/header.jsp" >
    <jsp:param name="containerType" value="container-fluid bg-light mt-3 px-5 rounded pb-3" />
</jsp:include>

    <!-- HEADING -->
    <jsp:include page="/template/heading.jsp" >
        <jsp:param name="title" value="<%=formType %>" />
        <jsp:param name="page" value="form" />
    </jsp:include>

    <!-- MESSAGE -->
    <jsp:include page="/template/message.jsp" />

    <!-- FORM -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-7">
                <jsp:include page="/pages/cart/form/book.jsp" />
            </div>
            <div class="col-lg-5">
                <jsp:include page="/pages/cart/form/cart.jsp" />
            </div>
        </div>
    </div>
                            
<!--FOOTER-->
<jsp:include page="/template/footer.jsp" />