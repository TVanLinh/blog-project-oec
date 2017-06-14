<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<script src="<s:url value="/public/vendors/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
<nav class="navbar navbar-inverse " style="border-radius: 0 !important;">
    <div class="container pdt-0 pdb-0">
        <div class="navbar-header">
            <c:if test="${param.active=='home'}">
                <a class="navbar-brand active" href="<s:url value="/"/>">${sessionScope.blogTitle}</a>
            </c:if>
            <c:if test="${param.active!='home'}">
                <a class="navbar-brand" href="<s:url value="/"/>" >${sessionScope.blogTitle}</a>
            </c:if>

        </div>
        <ul class="nav navbar-nav">


            <c:if  test="${sessionScope.username !=null}">


                <c:if test="${param.active=='write'}">
                    <li><a href="<s:url value="/write"/>" class="active">
                    <s:message code="newpost"/>
                </c:if>
                <c:if test="${param.active!='write'}">
                    <li><a href="<s:url value="/write"/>"> <s:message code="newpost"/></a></li>
                </c:if>
            </c:if>

        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="javascript:void(0)" id="seachHomePage"><i class="fa fa-search" aria-hidden="true"></i></a>
            </li>
            <c:if  test="${sessionScope.username !=null}">
                <li class="dropdown">
                    <a href="<s:url value="/user"/>" style="color: #00aaaa" class="dropdown-toggle" data-toggle="dropdown" >
                        <img src="<s:url value="public/asserts/images/user_blue.png"/>">${sessionScope.username}
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<s:url value="/user"/>"><s:message code="mypost"/> </a></li>
                        <c:if test="${sessionScope.userService !=null && sessionScope.username != null}">
                            <c:if test="${sessionScope.userService.isRoleAdmin(sessionScope.userService.getUserByName(sessionScope.username)) == true}">
                                <c:if test="${requestScope.active!='admin'}">
                                    <li><a href="<s:url value="/admin"/>"><s:message code="admin"/></a></li>
                                </c:if>
                            </c:if>
                        </c:if>
                        <li><a href="/change-pass-word"> <s:message code="changepass"/></a>

                        </li>
                        <li><a href="<s:url value="/logout"/>"><s:message code="logout"/></a></li>
                    </ul>
                </li>

            </c:if>
            <c:if  test="${sessionScope.username == null}">
                <li><a href="<s:url value="/login"/>"><span class="glyphicon glyphicon-log-in"></span> <s:message
                        code="login"/></a></li>
            </c:if>
            <li><a href="<s:url value="/language?language=en"/>"><img src="<s:url value="public/asserts/images/US.gif"/>" alt="USA"></a></li>
            <li><a href="<s:url  value="/language?language=vn"/>"><img
                    src="<s:url value="public/asserts/images/VI.gif"/>"></a></li>
        </ul>
    </div>
</nav>
<jsp:include page="../forms/form_search_home.jsp"/>

<script>
    jQuery(document).ready(function ($) {
        if($('#seachHomePage').length>0)
        {
            var seach=$('#seachHomePage');
            var target=$('#search-home')
                seach.on('click',function () {
                    target.toggleClass("search-show",1000);
                    seach.toggleClass('active');
                });
        }
    });
</script>