<%@page 
    import ="main.*"
    import ="Helper.*"
    import ="config.Config"
%>

<%    
    Cart item   = Cart.class.cast(request.getAttribute("item"));            
%>

<table id="cart-table" class="table table-striped mt-2">
    <caption>Cart in detail</caption>
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Qty</th>
            <th scope="col">Price</th>
            <th scope="col">Action</th>
        </tr>
    </thead>
    <tbody>
        <%
            String xhtml    = "";
            int order       = 1;
            Book book;
            if (item != null){
                for (int i=0; i<item.getBookArr().size(); i++){
                    book            = item.getBookArr().get(i);
                    xhtml   += Template.showBookRowInCart(order, item.getQtyArr().get(i), book);
                    order++;
                }
                String totalPrice   = Template.showTotalPriceInCart(item.getTotalPrice());
                xhtml   += totalPrice;
           }
            


            
        %>
        <% out.print(xhtml); %>        
    </tbody>
</table>

