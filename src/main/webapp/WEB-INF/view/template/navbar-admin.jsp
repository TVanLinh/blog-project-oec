<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse " style="border-radius: 0 !important;">
    <div class="container pdt-0 pdb-0">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Linh Tran Blog</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/write">New Post</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
            <c:if  test="${sessionScope.username ==null}">
                <li><a href="/login" ><span class="glyphicon glyphicon-log-in" ></span> Login</a></li>
            </c:if>
            <c:if  test="${sessionScope.username !=null}">
                <li><a href="/logout" ><span class="glyphicon glyphicon-log-out" ></span> Logout</a></li>
            </c:if>

            <li><a href="#"><img src="/images/US.gif" alt="USA"></a></li>
            <li><a href="#"><img src="/images/VI.gif"></a></li>
        </ul>
    </div>
</nav>
