<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.paramValueType"%>
<%

    String button;
    String icon;
    String curPage     = request.getParameter("page");
    if (curPage.equals("index")){ 
        button  = "<a href=\"form?action=add\" class=\"btn btn-dark\"><i class=\" fas fa-plus \"></i>&nbsp;Add</a>";
        icon    = "fal fa-books";
    }
    else{
        button  = "<a href=\"index\" class=\"btn btn-dark\"><i class=\"fas fa-hand-point-left\"></i>&nbsp;Back</a>";
        icon    = "fab fa-wpforms";
    }
   
%>

<div class="row">
    <div class="col-12 mt-5 d-flex align-items-center justify-content-between">
        <a href="index " class="text-decoration-none"><h1 class="text-primary"><i class="<%=icon%>"></i>&nbsp;${param.title}</h1></a>
        <% out.print(button); %>
    </div>
</div>