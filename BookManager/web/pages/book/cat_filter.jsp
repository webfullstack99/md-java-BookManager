
<%@page 
    import ="java.util.Scanner"
    import ="main.BookList"
    import ="main.CategoryList"
    import ="Helper.Template"
    import ="Helper.Helper"
%>

<%    
    String catFilter    = "";
    CategoryList catList    = CategoryList.class.cast(request.getAttribute("catList"));    
    if (catList.getList().size() > 0) catFilter = Template.showCatFilter(request, catList);
        
    
%>

<% out.print(catFilter); %>