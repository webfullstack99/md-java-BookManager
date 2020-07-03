<%@page 
    import ="main.UserList"
    import ="Helper.Template"
%>

<%    
    String tableBody    = "";
    // LIST
    UserList list   = UserList.class.cast(request.getAttribute("list"));      
    if (list.getList().size() > 0) tableBody = Template.showUserTableBody(request, list);   
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of categories</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="20%" class="sort"><a href="#" class="text-decoration-none" field="username">User info</a></th>
            <th scope="col">Phone</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="level">Level</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="status">Status</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="created">Created</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="modified">Modified</a></th>
            <th scope="col">Action</th>
<!--            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="id">Id</a></th>-->
        </tr>
    </thead>
    <tbody>       
        <% out.println(tableBody); %>
        <% if (Template.isStringEmpty(tableBody)) out.print("<tr><td colspan=\"6\" class=\"text-primary text-center\">Data is empty now</td></tr>"); %>
    </tbody>
</table>
