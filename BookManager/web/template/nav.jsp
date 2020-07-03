<%    
    String pageArr[]    = new String[]{"book", "category", "user", "cart"};
    String curPage      = request.getParameter("page");
    String xhtml        = "";
    for (int i=0; i<pageArr.length; i++){
        String pageName     = Helper.Helper.ucfirst(pageArr[i]);
        if (pageArr[i].equals(curPage)){
            xhtml   += "<a href=\"#\" class=\"btn btn-dark disabled\">"+pageName+"</a>&nbsp;";
        }else{
            xhtml   += "<a href=\""+request.getContextPath()+"/"+pageArr[i]+"/index\" class=\"btn btn-primary\">"+pageName+"</a>&nbsp;";
        }
    }
%>

<div class="row mt-3">
    <div class="col-12">        
        <div class="btn-group-sm">
           <span class="fas fa-browser fa-lg text-danger"></span>&nbsp;&nbsp;<% out.print(xhtml); %>
        </div>
    </div>
</div>