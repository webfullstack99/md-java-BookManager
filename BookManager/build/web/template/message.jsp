<%-- 
    Document   : index
    Created on : Nov 22, 2019, 1:38:57 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="Helper.Template"
%>

<%    
    String message      = "";
    if (session.getAttribute("message") != null){
        message = Template.showMessage((String)session.getAttribute("message"));
        session.removeAttribute("message");
    }
%>

<!-- MESSAGE -->
<% out.print(message); %>
          