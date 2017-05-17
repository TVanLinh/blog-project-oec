<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-inverse " style="border-radius: 0 !important;">
    <div class="container pdt-0 pdb-0">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">${sessionScope.blogTitle}</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/write"> ${messageSource.getMessage("newpost",null,locale)}</a></li>
            <li><a href="/user">User</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
            <c:if  test="${sessionScope.username ==null}">
                <li><a href="/login" ><span class="glyphicon glyphicon-log-in" ></span>  ${messageSource.getMessage("login",null,locale)}</a></li>
            </c:if>
            <c:if  test="${sessionScope.username !=null}">
                <li><a href="/logout" ><span class="glyphicon glyphicon-log-out" ></span>  ${messageSource.getMessage("logout",null,locale)}</a></li>
            </c:if>

            <li><a href="/language?language=en"><img src="<s:url value="public/asserts/images/US.gif"/>" alt="USA"></a></li>
            <li><a href="/language?language=vi"><img src="<s:url value="public/asserts/images/VI.gif"/>"></a></li>
        </ul>
    </div>
</nav>
