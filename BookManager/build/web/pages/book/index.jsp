<%
    String curPage  = (String)request.getAttribute("controller");
%>

<jsp:include page="/template/header.jsp" >
    <jsp:param name="containerType" value="container" />
</jsp:include>
<!-- HEADING -->
<jsp:include page="/template/heading.jsp" >
    <jsp:param name="title" value="Book manager" />
    <jsp:param name="page" value="index" />
</jsp:include>


<!-- NAVIGATION -->
<jsp:include page="/template/nav.jsp" >
    <jsp:param name="page" value="<%=curPage%>" />
</jsp:include>

<!--FILTER-->
<div class="row mt-2">
    <div class="col-md-4 col-8 offset-2 offset-md-0 mb-2 mb-md-0">
        <jsp:include page="/pages/book/cat_filter.jsp" />
    </div>
    <div class="col-md-8">
        <jsp:include page="/pages/book/search_bar.jsp" />                   
    </div>                
</div>

<!-- MESSAGE -->
<jsp:include page="/template/message.jsp" />

<!-- TABLE -->
<div class="row mt-1">
    <div class="col-12">
        <jsp:include page="/pages/book/list_table.jsp" />                    
    </div>
</div>
<jsp:include page="/template/footer.jsp" />