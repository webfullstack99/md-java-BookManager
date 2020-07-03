<%@page 
    import ="main.*"
    import ="Helper.*"
%>

<%    
    BookList list           = BookList.class.cast(request.getAttribute("bookList"));            
%>

<table id="list-table" class="table table-hover table-responsive-lg">
    <caption>List of books</caption>
    <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col" width="30%" class="sort"><a href="#" class="text-decoration-none" field="title">Title</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="author">Author</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="category">Category</a></th>
            <th scope="col" width="10%" class="sort">Qty</a></th>
            <th scope="col" class="sort"><a href="#" class="text-decoration-none" field="price">Price</a></th>
            <th scope="col" class="sort text-center">Action</th>
        </tr>
    </thead>
    <tbody>       
        <%
        String xhtml    = "";
        if (!Template.isListEmpty(list)){            
            int order   = 1;
            for (int i=0; i<list.getTempList().size(); i++){    
                Book item     = list.getTempList().get(i);
                String title    = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"title", item.getTitle());
                String author   = Highlight.highlight(Helper.getParam(request, "search_field"), Helper.getParam(request, "search_value"),"author", item.getAuthor());
                String category = item.getCategory();
                String created      = Template.showItemHistory(item.getCreated());
                String modified     = Template.showItemHistory(item.getModified());
                String status       = Template.showItemStatus(item.getStatus(), item.getId());
                
                String price       = Helper.number_format(item.getPrice(), "#,###");
                xhtml   += "<tr>\n" +
"                                <th scope=\"row\">"+order+"</th>\n" +
"                                <td>"+title+"</td>\n" +
"                                <td>"+author+"</td>\n" +
"                                <td>"+category+"</td>\n" +
"                                <td field=\"quantity\"><input class=\"qty-input form-control\" type=\"number\" min=\"1\" max=\"10\" value=\"1\" /></td>\n" +                               
"                                <td class=\"\">"+price+"</td>\n" +
"                                <td class=\"text-center\">\n" +
"                                    <a item-id=\""+item.getId()+"\" href=\"#\" class=\"text-success add-action\"><i class=\"fas fa-plus fa-fw\"></i></a>\n" +                        
"                                </td>\n"+
"                            </tr>";
                order++;
            }
        }
        %>
        <% out.println(xhtml); %>
        <% if (Template.isStringEmpty(xhtml)) out.print("<tr><td colspan=\"9\" class=\"text-primary text-center\">Data is empty now</td></tr>"); %>
    </tbody>
</table>
