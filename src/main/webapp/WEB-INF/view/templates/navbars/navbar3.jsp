<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<script src="<s:url value="/public/vendors/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
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
                <c:if test="${requestScope.active=='admin'}">
                    <li><a href="<s:url value="/admin"/>" class="active">Admin</a></li>
                </c:if>
                <c:if test="${requestScope.active!='admin'}">
                    <li><a href="<s:url value="/admin"/>">Admin</a></li>
                </c:if>

            </c:if>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="javascript:void(0)" id="seachHomePage"><span class="glyphicon glyphicon-search"></span></a>
            </li>
            <c:if  test="${sessionScope.username !=null}">
                <li class="dropdown">
                    <a href="<s:url value="/user"/>" style="color: #00aaaa" class="dropdown-toggle" data-toggle="dropdown" >
                        <img src="<s:url value="public/asserts/images/user_blue.png"/>">${sessionScope.username}
                    </a>
                    <ul class="dropdown-menu">
                        <li>    <a href="<s:url value="/user"/>">${messageSource.getMessage("mypost",null,locale)}</a></li>
                        <li>    <a href="/change-pass-word">${messageSource.getMessage("changepass",null,locale)}</a></li>
                        <li><a href="<s:url value="/logout"/>" >${messageSource.getMessage("logout",null,locale)}</a></li>
                    </ul>
                </li>

            </c:if>
            <c:if  test="${sessionScope.username ==null}">
                <li><a href="<s:url value="/login"/>" ><span class="glyphicon glyphicon-log-in" ></span> ${messageSource.getMessage("login",null,locale)}</a></li>
            </c:if>
            <li><a href="<s:url value="/language?language=en"/>"><img src="<s:url value="public/asserts/images/US.gif"/>" alt="USA"></a></li>
            <li><a href="<s:url  value="/language?language=vi"/>"><img src="<s:url value="public/asserts/images/VI.gif"/>"></a></li>
        </ul>
    </div>
</nav>
<jsp:include page="../forms/form_search_home.jsp"/>

<script>
    var temp=0;
    jQuery(document).ready(function ($) {
        if($('#seachHomePage').length>0)
        {
            var seach=$('#seachHomePage');
            var target=$('#search-home');
            if(temp===0)
            {
                seach.on('click',function () {
//                    target.removeClass("search-hide");
                    target.toggleClass("search-show",1000);
//                    $("#place-holder").toggleClass('mgb-30');
                });
                temp=1;
            }
        }
    });
</script>