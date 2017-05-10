<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/head.jsp"/>

<head>
    <link href="public/asserts/css/form-sigin.css" type="text/css" rel="stylesheet">
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="login">
    <div class="login-triangle"></div>

    <h2 class="login-header">Log in</h2>

    <form name="loginForm" class="login-container" action="<c:url value="/login"/> " method="post">
        <p><input name="username" type="text" placeholder="Email"></p>
        <p><input name="password" type="password" placeholder="Password"></p>
        <p><input type="submit" value="Log In" onclick="return login()"></p>
        <center><span class="login-error">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </span></center>
        <span class="login-success">
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
        </span>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
