<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="template/navbar-admin.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-8">
            <ul class="list-group">
                <li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="/manager-post">${messageSource.getMessage("managerPost",null,locale)}</a></li>
                <li class="list-inline btn btn-success mgr-10  mgb-15"><a href="/configuration"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>
                <li class="list-inline btn btn-warning mgl-10  mgb-15"><a href="/manager-user">${messageSource.getMessage("managerUser",null,locale)}</a></li>
            </ul>
            <form ACTION="/processConfigurarion" METHOD="post">
                <div class="form-group">
                    <label for="titleBlog">${messageSource.getMessage("titleBlog",null,locale)}:</label>
                    <input type="text" class="form-control " name="titleBlog" id="titleBlog">
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("fomatDate",null,locale)}:</label>
                    <select class="form-control"  name="formatTime">
                        <option value="HH:mm:ss dd:MM:yyyy">HH:mm:ss dd:MM:yyyy</option>
                        <option value="HH:mm:ss dd:MM:yyyy">HH:mm:ss dd/MM/yyyy</option>
                        <option value="HH:mm:ss dd:MM:yyyy">HH:mm:ss dd-MM-yyyy</option>
                        <option value="dd:MM:yyyy">dd:MM:yyyy</option>
                        <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                    </select>

                    <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <div class="form-group">
                    <label for="numberPost">${messageSource.getMessage("numberView",null,locale)}:</label>
                    <input type="number" min="1"  value="3" class="form-control pd-0" name="numberPost" id="numberPost">
                </div>
                <input type="submit" value="${messageSource.getMessage("save",null,locale)}">
                <p class="pd-10 error">${requestScope.error}</p>
            </form>
        </div>
        <!-- Blog Sidebar Widgets Column -->
        <div>
            <jsp:include page="template/slidebar.jsp"/>
        </div>
    </div>
    <!-- /.row -->
    <hr>
    <jsp:include page="template/footer.jsp"/>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
</body>
</html>

