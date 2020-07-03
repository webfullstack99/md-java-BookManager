<%-- 
    Document   : index
    Created on : Nov 22, 2019, 1:38:57 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="main.Template"
%>

<%    
    String message      = "";
    if (request.getParameter("message") != null) message = Template.showMessage(request.getParameter("message"));
%>

<!-- MESSAGE -->
<% out.print(message); %>
          