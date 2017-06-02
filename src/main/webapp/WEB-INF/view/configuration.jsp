<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/headers/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-12">

            <jsp:include page="templates/menus/menu-admin.jsp"/>

            <form ACTION="<s:url value="/processConfigurarion"/>" METHOD="post">
                <div class="form-group">
                    <label for="titleBlog">${messageSource.getMessage("titleBlog",null,locale)}:</label>
                    <input type="text" class="form-control " name="titleBlog" id="titleBlog" value="${requestScope.conf.webTitle}">
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("fomatDate",null,locale)}:</label>
                    <select class="form-control"  name="formatTime" >
                        <option value="HH:mm:ss dd/MM/yyyy">HH:mm:ss dd/MM/yyyy</option>
                        <option value="HH:mm:ss dd-MM-yyyy">HH:mm:ss dd-MM-yyyy</option>
                        <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                        <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                    </select>

                    <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <div class="form-group">
                    <label for="numberPost">${messageSource.getMessage("numberView",null,locale)}:</label>
                    <input type="number" min="1"  value="${requestScope.conf.numberViewPost}" class="form-control pd-0" name="numberPost" id="numberPost">
                </div>
                <input type="submit" value="${messageSource.getMessage("save",null,locale)}">
                <p class="pd-10 error">${requestScope.error}</p>
            </form>
        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<jsp:include page="templates/footers/footer.jsp"/>

<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
