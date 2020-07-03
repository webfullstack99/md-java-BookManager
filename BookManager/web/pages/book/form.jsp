<%-- 
    Document   : form
    Created on : Nov 21, 2019, 8:29:02 PM
    Author     : Asus ZenBook
--%>

<%@page 
    import ="main.Book"
    import ="java.time.LocalTime"
    import ="Helper.Template"
    import ="main.CategoryList"
    import ="config.*"
%>

<%
    String formAction   = "save";
    Book item               = Book.class.cast(request.getAttribute("item"));
    CategoryList catList    = CategoryList.class.cast(request.getAttribute("catList"));
    
    String title    = "";
    String author   = "";
    String category = "";
    String status   = "";
    String price    = "";
    String formType = "Add";
    String time     = LocalTime.now().toString();
    if (item != null){
        formAction  = "save?id="+item.getId();
        formType    = "Edit";   
        title   = item.getTitle();
        author  = item.getAuthor();
        category= item.getCategory();
        status  = item.getStatus();
        price   = ""+item.getPrice();
    }  
    String catSelect   = Template.showCatSelect(catList, category);
    String statusSelect     = Template.showFormSelect("status", Config.statusArr, status);   
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
                                    <label for="name" class="form-group col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Title</label>
                                    <input class="form-control form-group col-lg-10" value="<% out.print(title); %>" name="title" type="text" id="title">
                                </div>
                                <div class="form-row">
                                    <label for="name" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Author</label>
                                    <input class="form-control form-group col-lg-10" value="<% out.print(author); %>" name="author" type="text" id="author">
                                </div>
                                <div class="form-row">
                                    <label for="name" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Category</label>
                                    <% out.print(catSelect); %>
                                </div>
                                <div class="form-row">
                                    <label for="name" class="col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Status</label>
                                    <% out.print(statusSelect); %>
                                </div>
                                <div class="form-row">
                                    <label for="name" class=" col-lg-2 mb-1 mb-lg-3 d-block d-lg-flex align-items-center">Price</label>
                                    <input class="form-control col-lg-10" value="<% out.print(price); %>" name="price" type="text" id="price">
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
<!--HEADER-->
<jsp:include page="/template/footer.jsp" />