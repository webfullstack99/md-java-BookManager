<!--FILTER-->
<div class="row mt-2">
    <div class="col-md-4 col-8 offset-2 offset-md-0 mb-2 mb-md-0">
        <jsp:include page="/pages/book/cat_filter.jsp" />
    </div>
    <div class="col-md-8">
        <jsp:include page="/pages/book/search_bar.jsp" />                   
    </div>                
</div>

<!-- TABLE -->
<div class="row mt-1">
    <div class="col-12">
        <jsp:include page="/pages/cart/form/book_list_table.jsp" />                    
    </div>
</div>