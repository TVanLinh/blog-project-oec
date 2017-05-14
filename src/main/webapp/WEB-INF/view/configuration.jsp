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
            <form ACTION="/processConfigurarion" METHOD="post">
                <div class="form-group">
                    <label for="titleBlog">Title Blog:</label>
                    <input type="text" class="form-control " name="titleBlog" id="titleBlog">
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >Format Time:</label>
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
                    <label for="numberPost">Number view post:</label>
                    <input type="number" min="1"  value="3" class="form-control pd-0" name="numberPost" id="numberPost">
                </div>
                <input type="submit" value="SAVE">
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

