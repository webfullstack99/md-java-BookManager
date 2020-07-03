<%@page 
    import ="main.CartList"
    import ="Helper.Template"
%>

<%    
    String tableBody    = "";
    // LIST
    CartList list   = CartList.class.cast(request.getAttribute("list"));      
    if (list.getList().size() > 0) tableBody = Template.showCartTableBody(request, list);   
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of categories</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="15%" class="sort"><a href="#" class="text-decoration-none" field="id">Cart Id</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="totalQty">Total qty</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="totalPrice">Total price</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="username">Cart of user</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="status">Status</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="created">Created</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="modified">Modified</a></th>
            <th scope="col" class="sort">Action</th>
        </tr>
    </thead>
    <tbody>       
        <% out.println(tableBody); %>
        <% if (Template.isStringEmpty(tableBody)) out.print("<tr><td colspan=\"6\" class=\"text-primary text-center\">Data is empty now</td></tr>"); %>
    </tbody>
</table>
