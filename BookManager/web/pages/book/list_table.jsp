<%@page 
    import ="java.util.Scanner"
    import ="main.BookList"
    import ="main.CategoryList"
    import ="Helper.Template"
    import ="Helper.Helper"
%>

<%    
    String tableBody    = "";
    // book list
    BookList list           = BookList.class.cast(request.getAttribute("list"));            
    if (list.getList().size() > 0) tableBody = Template.showBookTableBody(request, list);   
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of books</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="15%" class="sort"><a href="#" class="text-decoration-none" field="title">Title</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="author">Author</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="category">Category</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="price">Price</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="status">Status</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="created">Created</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="modified">Modified</a></th>
            <th scope="col" class="sort">Action</th>
        </tr>
    </thead>
    <tbody>       
        <% out.println(tableBody); %>
        <% if (Template.isStringEmpty(tableBody)) out.print("<tr><td colspan=\"9\" class=\"text-primary text-center\">Data is empty now</td></tr>"); %>
    </tbody>
</table>
