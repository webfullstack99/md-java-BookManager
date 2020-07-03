<%-- 
    Document   : form
    Created on : Nov 21, 2019, 8:29:02 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="main.Category"
    import ="java.time.LocalTime"
    import ="main.Template"
%>

<%
    String formAction   = "save";
    String message      = "";
    if (request.getParameter("message") != null) message = Template.showMessage(request.getParameter("message"));
    Category item   = Category.class.cast(request.getAttribute("item"));
    String name    = "";
    String status   = "";
    String formType = "Add";
    String time     = LocalTime.now().toString();
    if (item != null){
        formAction  = "save?id="+item.getId();
        formType    = "Edit";   
        name   = item.getName();
        status  = item.getStatus();
    }  
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/template/head.jsp" />
    <body class="bg-secondary">
        <div class="container bg-light mt-3 mt-lg-3 px-lg-5 rounded pb-5">
            <!-- HEADING -->
            <jsp:include page="/template/heading.jsp" >
                <jsp:param name="title" value="<%=formType %>" />
                <jsp:param name="page" value="form" />
            </jsp:include>

            <!-- MESSAGE -->
            <% out.print(message); %>
            
            <!-- FORM -->
            <div class="row">
                <div class="col-12">
                    <form method="POST" action="<% out.print(formAction); %>" class="container">
                        <div class="row">
                            <div class="col-md-6 offset-md-3">
                                <div class="form-row">
                                    <label for="name" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Name</label>
                                    <input class="form-control form-group col-lg-10" value="<% out.print(name); %>" name="name" type="text" id="name">
                                </div>
                                <div class="form-row">
                                    <label for="name" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Status</label>
                                    <input class="form-control form-group col-lg-10" value="<% out.print(status); %>" name="status" type="text" id="status">
                                </div>                               
                                
                                <div class="form-row mt-3">
                                    <input type="hidden" value="<% out.print(time); %>" name="token" />
                                    <input class="form-control col-lg-2 btn btn-primary" type="submit" value="Submit">
                                </div>

                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    <jsp:include page="/template/scripts.jsp" />
    </body>

</html>