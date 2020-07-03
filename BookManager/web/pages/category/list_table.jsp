<%@page 
    import ="main.CategoryList"
    import ="Helper.Template"
%>

<%    
    String tableBody    = "";
    // LIST
    CategoryList list   = CategoryList.class.cast(request.getAttribute("list"));      
    if (list.getList().size() > 0) tableBody = Template.showCategoryTableBody(request, list);   
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of categories</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="30%" class="sort"><a href="#" class="text-decoration-none" field="name">Name</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="status">Status</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="created">Created</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="modified">Modified</a></th>
            <th scope="col" class="sort">Action</th>
<!--            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="id">Id</a></th>-->
        </tr>
    </thead>
    <tbody>       
        <% out.println(tableBody); %>
        <% if (Template.isStringEmpty(tableBody)) out.print("<tr><td colspan=\"6\" class=\"text-primary text-center\">Data is empty now</td></tr>"); %>
    </tbody>
</table>
