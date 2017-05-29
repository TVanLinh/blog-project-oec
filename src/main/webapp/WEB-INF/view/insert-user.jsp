<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="template/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-8">
            <form:form ACTION="/action-insert-user" METHOD="post"  onsubmit="return checkFormInsertUser()">
                <div class="form-group">
                    <label for="userName">${messageSource.getMessage("name",null,locale)}:</label>
                    <input type="text" class="form-control " name="userName" id="userName">
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)}:</label>
                    <input type="password" class="form-control " name="passWord" id="passWord">
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)}:</label>
                    <input type="password" class="form-control " name="rePassWord" id="rePassWord">
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("role",null,locale)}:</label>
                    <select class="form-control"  name="listRole" multiple="multiple" id="listRole">
                        <option value="ROLE_USER"  >ROLE_USER</option>
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    </select>

                    <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <input type="submit" value="${messageSource.getMessage("save",null,locale)}" onsubmit="return checkFormInsertUser()" onclick="return checkFormInsertUser()">
                <p class="pd-10 error">${requestScope.error}</p>
            </form:form>
        </div>
        <!-- Blog Sidebar Widgets Column -->
        <div>
            <%--<jsp:include page="template/slidebar.jsp"/>--%>
        </div>
    </div>
    <!-- /.row -->
    <hr>
    <jsp:include page="template/footer.jsp"/>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
</body>
</html>

