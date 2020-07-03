<%@page 
    import ="Helper.Helper"
%>
    
<%
    String containerClass   = "container bg-light mt-3 rounded pb-3";
    String containerType    = Helper.getParam(request, "containerType");
    if (!containerType.equals("container")) containerClass  = containerType;
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/template/head.jsp" />
    <body class="bg-secondary">                
        <div class="<%=containerClass%>">