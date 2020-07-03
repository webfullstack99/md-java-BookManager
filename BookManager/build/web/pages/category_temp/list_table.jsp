<%@page 
    import ="java.util.Scanner"
    import ="main.BookList"
    import ="main.CategoryList"
    import ="main.Template"
    import ="main.Helper"
%>

<%    
    String tableBody    = "";
    // LIST
    CategoryList list   = CategoryList.class.cast(request.getAttribute("list"));      
    if (list.getN() > 0) tableBody = Template.showCategoryTableBody(request, list);   
    else tableBody  = "<tr><td colspan=\"7\" class=\"text-primary text-center\">Data is empty now</td></tr>";  
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of categories</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="30%" class="sort"><a href="#" class="text-decoration-none" field="name">Name</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="status">Status</a></th>
            <th scope="col" class="sort">Action</th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="id">Id</a></th>
        </tr>
    </thead>
    <tbody>       
        <% out.println(tableBody); %>
    </tbody>
</table>
