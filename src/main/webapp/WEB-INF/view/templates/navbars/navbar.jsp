<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse " style="border-radius: 0 !important;">
    <div class="container pdt-0 pdb-0">
        <div class="navbar-header">
            <c:if test="${requestScope.active=='home'}">
                <a class="navbar-brand active" href="<s:url value="/"/>">${sessionScope.blogTitle}</a>
            </c:if>
            <c:if test="${requestScope.active!='home'}">
                <a class="navbar-brand" href="<s:url value="/"/>" >${sessionScope.blogTitle}</a>
            </c:if>

        </div>
        <ul class="nav navbar-nav">


            <c:if  test="${sessionScope.username !=null}">


                <c:if test="${requestScope.active=='write'}">
                    <li><a href="<s:url value="/write"/>" class="active">${messageSource.getMessage("newpost",null,locale)}</a></li>
                </c:if>
                <c:if test="${requestScope.active!='write'}">
                    <li><a  href="<s:url value="/write"/>">${messageSource.getMessage("newpost",null,locale)}</a></li>
                </c:if>
            </c:if>


            <c:if  test="${sessionScope.username !=null}">
                <c:if test="${requestScope.active=='author'}">
                    <li><a href="<s:url value="/user"/>" class="active">User</a></li>
                </c:if>
                <c:if test="${requestScope.active!='author'}">
                    <li><a href="<s:url value="/user"/>">User</a></li>
                </c:if>

            </c:if>

            <c:if  test="${sessionScope.username !=null}">
                <c:if test="${requestScope.active=='admin'}">
                    <li><a href="<s:url value="/admin"/>" class="active">Admin</a></li>
                </c:if>
                <c:if test="${requestScope.active!='admin'}">
                    <li><a href="<s:url value="/admin"/>">Admin</a></li>
                </c:if>

            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if  test="${sessionScope.username ==null}">
                <li><a href="<s:url value="/login"/>" ><span class="glyphicon glyphicon-log-in" ></span> ${messageSource.getMessage("login",null,locale)}</a></li>
            </c:if>
            <c:if  test="${sessionScope.username !=null}">
                <li><a href="<s:url value="/logout"/>" ><span class="glyphicon glyphicon-log-out" ></span> ${messageSource.getMessage("logout",null,locale)}</a></li>
            </c:if>

            <li><a href="<s:url value="/language?language=en"/>"><img src="<s:url value="public/asserts/images/US.gif"/>" alt="USA"></a></li>
            <li><a href="<s:url  value="/language?language=vi"/>"><img src="<s:url value="public/asserts/images/VI.gif"/>"></a></li>
        </ul>
    </div>
</nav>