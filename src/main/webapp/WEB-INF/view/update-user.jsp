<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>


<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12">
            <s:url value="/action-update-user" var="formAction"/>
            <form:form ACTION="${formAction}" METHOD="post" onsubmit="return checkFormInsertUser()" commandName="userForm">
                <form:input type="hidden" path="user.id" value="${param.id}"/>
                <div class="form-group">
                    <label for="userName">${messageSource.getMessage("name",null,locale)}:</label>
                    <form:input path="user.userName" type="text" class="form-control userName " name="userName" id="userName" value="${requestScope.user.userName}"/>
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)}:</label>
                    <form:input path="user.passWord" type="password" class="form-control  passWord" name="passWord" id="passWord" />
                </div>
                <div class="form-group">
                    <label for="rePassWord">${messageSource.getMessage("rePassWord",null,locale)}:</label>
                    <form:input path="rePassWord" type="password" class="form-control " name="rePassWord" id="rePassWord"/>
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("role",null,locale)}:</label>
                    <form:select path="user.roleList" class="form-control"  name="listRole" multiple="multiple" >
                        <c:if test="${requestScope.userService.isRoleUser(requestScope.user) && requestScope.userService.isRoleAdmin(requestScope.user)}">
                            <option value="ROLE_USER"  selected>ROLE_USER</option>
                            <option value="ROLE_ADMIN"  selected >ROLE_ADMIN</option>
                        </c:if>

                        <c:if test="${!requestScope.userService.isRoleUser(requestScope.user) && !requestScope.userService.isRoleAdmin(requestScope.user)}">
                            <option value="ROLE_USER"  >ROLE_USER</option>
                            <option value="ROLE_ADMIN"   >ROLE_ADMIN</option>
                        </c:if>

                        <c:if test="${!requestScope.userService.isRoleUser(requestScope.user) && requestScope.userService.isRoleAdmin(requestScope.user)}">
                            <option value="ROLE_USER"  >ROLE_USER</option>
                            <option value="ROLE_ADMIN"  selected >ROLE_ADMIN</option>
                        </c:if>

                        <c:if test="${requestScope.userService.isRoleUser(requestScope.user) && !requestScope.userService.isRoleAdmin(requestScope.user)}">
                            <option value="ROLE_USER"  selected>ROLE_USER</option>
                            <option value="ROLE_ADMIN"   >ROLE_ADMIN</option>
                        </c:if>
                    </form:select>

                        <%--<form:input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <input type="submit" class="btn btn-default" value="${messageSource.getMessage("save",null,locale)}" onclick="return checkFormInsertUser()" onsubmit="return checkFormInsertUser()">
                <p class="pd-10 error">
                    <c:forEach var="item" items="${requestScope.errors}">
                        ${messageSource.getMessage(item,null,locale)}
                    </c:forEach>
                </p>
            </form:form>
        </div>
        <!-- Blog Sidebar Widgets Column -->
        <%--<div>--%>
            <%--<jsp:include page="templates/slidebar/slidebar.jsp"/>--%>
        <%--</div>--%>
    </div>
    <!-- /.row -->
    <hr>



    <script src="<s:url value="/public/asserts/js/search.js"/>"></script>
    <script src="<s:url value="/public/asserts/js/main.js"/>"></script>
    <script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>
