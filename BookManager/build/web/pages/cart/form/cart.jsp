<%@page 
    import ="main.*"
    import ="Helper.*"
    import ="config.*"
%>

<%
    String formAction   = "save";
    String cartId       = "...";
    String booksInput   = "";
    String qtysInput    = "";
    int id              = 1;
    Cart item           = Cart.class.cast(request.getAttribute("item"));
    if (item != null){
        formAction  = "save?id="+item.getId();
        id = item.getId();
        booksInput      = item.convertBookArrToString(item.getBookArr());
        qtysInput       = item.convertQtyArrToString(item.getQtyArr());
    }
    else id = Integer.class.cast(request.getAttribute("newId"));
    cartId  = "#"+Helper.strpad(id+"", Config.cartIdLimit, "0");
%>

<!-- CART ID -->
<div class="row mt-1">
    <div class="col-12">
        <h4>Cart Id: <span class="text-primary"><%=cartId%></span></h4>                 
    </div>
</div>

<!-- TABLE -->
<div class="row mt">
    <div class="col-12">    
        <jsp:include page="/pages/cart/form/cart_table.jsp" />
    </div>
    <div class="col-lg-3 col-4">
        <form id="cart-form" method="POST" action="<% out.print(formAction); %>" class="container">        
            <input type="hidden" name="booksString" value="<%=booksInput%>" />
            <input type="hidden" name="qtysString" value="<%=qtysInput%>" />
            <button id="submit-cart-btn" class="btn btn-primary btn-block">Submit</button>
        </form>
    </div>
</div>
    