<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>

<jsp:include page="templates/navbars/navbar.jsp"/>

<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <s:url var="action" value="/change-pass-word"/>
        <div class="col-xs-12">
            <form:form method="post" action="${action}"  onsubmit="return checkFormValidPassWord()" commandName="userForm">
                <form:input path="user.id" type="hidden" value="${requestScope.user.id}"/>
                <form:input path="user.userName" type="hidden" value="${requestScope.user.userName}"/>
                <form:input path="user.passWord" type="hidden" value="${requestScope.user.passWord}"/>
                <div class="form-group">
                    <label for="oldPassWord">${messageSource.getMessage("passWord",null,locale)} ${messageSource.getMessage("old",null,locale)}:</label>
                    <form:input path="oldPassWord" type="password" class="form-control " name="oldPassWord" id="oldPassWord"/>
                </div>

                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)} ${messageSource.getMessage("new",null,locale)}:</label>
                    <input:input path="newPassWord" type="password" class="form-control " name="passWord" id="passWord"/>
                </div>

                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("rePassWord",null,locale)}:</label>
                    <form:input path="rePassWord" type="password" class="form-control " name="rePassWord" id="rePassWord"/>
                </div>

                <input type="submit" class="btn btn-default" value="${messageSource.getMessage("save",null,locale)}" onsubmit="return checkFormValidPassWord()" onclick="return checkFormInsertUser()">
                <p class="pd-10 error">
                    <c:if test="${requestScope.error != null}">
                        ${messageSource.getMessage(requestScope.error,null,locale)}
                    </c:if>
                </p>
                <c:if test="${requestScope.errors != null}">
                    <c:forEach var="item" items="${requestScope.errors}">
                        ${messageSource.getMessage(item,null,locale)}
                    </c:forEach>
                </c:if>
            </form:form>
        </div>
    </div>
    <hr>
</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>

