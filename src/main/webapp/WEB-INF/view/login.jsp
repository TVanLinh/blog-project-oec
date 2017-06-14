<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="templates/headers/head.jsp"/>

<head>
    <link href="public/asserts/css/form-sigin.css" type="text/css" rel="stylesheet">
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="login">
    <div class="login-triangle"></div>

    <h2 class="login-header"><s:message code="login"/></h2>

    <form name="loginForm" class="login-container" action="<c:url value="/login"/> " method="post">
        <p><input id="username" name="username" type="text" placeholder="<s:message code="name" />"></p>
        <p><input id="password" name="password" type="password" placeholder="<s:message code="passWord" />"></p>
        <p><input type="submit" value="<s:message code="by" />" onclick="return checkForm.checkLogin()"></p>
        <center><span class="login-error">
            <c:if test="${not empty error}">
                <div class="error"><s:message code="${requestScope.error}"/></div>
            </c:if>
        </span></center>
        <span class="login-success">
            <c:if test="${not empty msg}">
                <div class="msg"> <s:message code="${requestScope.msg}"/></div>
            </c:if>
        </span>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/> ">

</script>