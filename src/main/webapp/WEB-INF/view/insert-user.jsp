<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>


<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container-fluid">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-lg-2 col-md-3 col-xs-12">
            <jsp:include page="templates/menus/menu-admin.jsp"/>
        </div>
        <s:url value="/action-insert-user" var="action"/>
        <div class="col-lg-8 col-md-8 col-xs-12">
            <form:form method="post" action="${action}" modelAttribute="userForm" onsubmit="return checkFormInsertUser()">
                <div class="form-group">
                    <label for="userName">${messageSource.getMessage("name",null,locale)}:</label>
                    <form:input type="text" class="form-control" id="userName" path="user.userName"/>
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)}:</label>
                    <form:input type="password" class="form-control" name="passWord" id="passWord" path="user.passWord"/>
                </div>
                <div class="form-group">
                    <label for="rePassWord">${messageSource.getMessage("rePassWord",null,locale)}:</label>
                    <form:input type="password" class="form-control"   id="rePassWord" path="rePassWord"/>
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("role",null,locale)}:</label>
                    <form:select class="form-control"  multiple="multiple" id="listRole" path="user.roleList">
                        <option value="ROLE_USER"  >ROLE_USER</option>
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    </form:select>

                    <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <input type="submit" class="btn btn-default" value="${messageSource.getMessage("save",null,locale)}" onsubmit="return checkFormInsertUser()" onclick="return checkFormInsertUser()">
                <p class="pd-10 error">
                    <c:if test="${requestScope.errors != null}">
                        <c:forEach var="item" items="${requestScope.errors}">
                            ${messageSource.getMessage(item,null,locale)}
                        </c:forEach>
                    </c:if>
                </p>
            </form:form>
        </div>
        <div class="clearfix"></div>
        <!-- Blog Sidebar Widgets Column -->
        <div>
            <%--<jsp:include page="templates/slidebar.jsp"/>--%>
        </div>
    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>

