<%-- 
    Document   : index
    Created on : Nov 22, 2019, 1:38:57 PM
    Author     : Asus ZenBook
--%>

<%    
    String curPage     = "book";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/template/head.jsp" />
    <body class="bg-secondary">        
        
        <div class="container bg-light mt-3 mt-lg-3 px-lg-5 rounded">
            <!-- HEADING -->
            <jsp:include page="/template/heading.jsp" >
                <jsp:param name="title" value="Book manager" />
                <jsp:param name="page" value="index" />
            </jsp:include>

            
            <!-- NAVIGATION -->
            <jsp:include page="/template/nav.jsp" >
                <jsp:param name="page" value="<%=curPage%>" />
            </jsp:include>
            
            <!-- FILTER -->
            <div class="row mt-2">
                <div class="col-md-8">
                    <jsp:include page="/pages/book/search_bar.jsp" />                   
                </div>                
            </div>
            
            <!-- MESSAGE -->
            <jsp:include page="/pages/book/message.jsp" />
           
            <!-- TABLE -->
            <div class="row mt-1">
                <div class="col-12">
                    <jsp:include page="/pages/book/list_table.jsp" />                    
                </div>
            </div>
        </div>                       
    <jsp:include page="/template/scripts.jsp" />
    </body>
</html>
