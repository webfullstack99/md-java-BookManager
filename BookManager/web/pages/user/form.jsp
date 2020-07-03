<%-- 
    Document   : form
    Created on : Nov 21, 2019, 8:29:02 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="main.User"
    import ="java.time.LocalTime"
    import ="Helper.Template"
    import ="config.*"
%>

<%
    String formAction   = "save";
    String message      = "";
    if (request.getAttribute("message") != null) message = Template.showMessage(request.getParameter("message"));
    User item   = User.class.cast(request.getAttribute("item"));
    String username = "";
    String email = "";
    String fullname = "";
    String phone = "";
    String password = "";
    String level    = "";
    String status   = "";
    String formType = "Add";
    String time     = LocalTime.now().toString();
    if (item != null){
        formAction  = "save?id="+item.getId();
        formType    = "Edit";   
        username    = item.getUsername();
        email       = item.getEmail();
        fullname    = item.getFullname();
        phone       = item.getPhone()+"";
        level       = item.getLevel();
        status      = item.getStatus();
    }  
    String statusSelect     = Template.showFormSelect("status", Config.statusArr, status); 
    String levelSelect     = Template.showFormSelect("level", Config.levelArr, level);        
%>

<!--HEADER-->
<jsp:include page="/template/header.jsp" >
    <jsp:param name="containerType" value="container" />
</jsp:include>

    <!-- HEADING -->
    <jsp:include page="/template/heading.jsp" >
        <jsp:param name="title" value="<%=formType %>" />
        <jsp:param name="page" value="form" />
    </jsp:include>

    <!-- MESSAGE -->
    <jsp:include page="/template/message.jsp" />

    <!-- FORM -->
    <div class="row">
        <div class="col-12">
            <form method="POST" action="<% out.print(formAction); %>" class="container">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="form-row">
                            <label for="username" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Username</label>
                            <input class="form-control form-group col-lg-10" value="<% out.print(username); %>" name="username" type="text" id="username">
                        </div>
                        <div class="form-row">
                            <label for="email" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Email</label>
                            <input class="form-control form-group col-lg-10" value="<% out.print(email); %>" name="email" type="email" id="email">
                        </div>
                        <div class="form-row">
                            <label for="fullname" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Full name</label>
                            <input class="form-control form-group col-lg-10" value="<% out.print(fullname); %>" name="fullname" type="text" id="fullname">
                        </div>
                        <div class="form-row">
                            <label for="phone" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Phone</label>
                            <input class="form-control form-group col-lg-10" value="<% out.print(phone); %>" maxlength="10" name="phone" type="text" id="phone">
                        </div>
                        
                        <div class="form-row">
                            <label for="password" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Password</label>
                            <input class="form-control form-group col-lg-10" value="<% out.print(password); %>" name="password" type="password" id="password">
                        </div>                     
                        <div class="form-row">
                            <label for="level" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Level</label>
                            <%=levelSelect%>
                        </div>   <div class="form-row">
                            <label for="status" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Status</label>
                            <%=statusSelect%>
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
                            
<!--FOOTER-->
<jsp:include page="/template/footer.jsp" />