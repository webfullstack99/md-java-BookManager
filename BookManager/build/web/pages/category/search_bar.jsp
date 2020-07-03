<%-- 
    Document   : index
    Created on : Nov 22, 2019, 1:38:57 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="java.util.Scanner"
    import ="main.BookList"
    import ="main.CategoryList"
    import ="Helper.Template"
    import ="Helper.Helper"
%>

<%    
    String searchField  = "name";
    String searchValue  = "";
    if (Helper.getParam(request, "search_field") != null) searchField = Helper.getParam(request, "search_field");
    if (Helper.getParam(request, "search_value") != null) searchValue = Helper.getParam(request, "search_value");    
%>

 <div class="input-group">
    <div class="input-group-prepend">
        <div id="search-dropdown" class="dropdown">
          <button id="search-by-btn" name="<% out.print(searchField); %>" class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Search by <% out.print(searchField); %>
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#" name="name">Search by name</a>
          </div>
        </div>
    </div>
    <input id="search-input" value="<% out.print(searchValue); %>" type="text" class="form-control" name="search">
    <div class="input-group-append">
        <div class="btn-group">
            <button id="search-btn" class="btn btn-primary">Search</button>
            <button id="clear-btn" class="btn btn-info">Clear</button>                                   
        </div>                                    
    </div>
</div>             
              