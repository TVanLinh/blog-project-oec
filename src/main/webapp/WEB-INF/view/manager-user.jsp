<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
<style>
    .menu-item:nth-child(4)
    {
        color: #ffff5d;
    }
</style>
<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container-fluid">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-2 col-xs-12">
            <jsp:include page="templates/menus/menu-admin.jsp"/>
        </div>

        <div class="col-md-10 col-xs-12 ">
            <c:if test="${userList.size()!=0}">
                <h1 class="text-center">${messageSource.getMessage("listuser",null,locale)}</h1>
            </c:if>
            <c:if test="${userList.size()==0}">
                <h1 class="text-center">${messageSource.getMessage("notuser",null,locale)}</h1>
            </c:if>
            <!----------------end list-table ------------------------->
            <div class="text-center">
                <jsp:include page="templates/forms/search3.jsp">
                    <jsp:param name="searchBy" value="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('username',null,locale)}"/>
                    <jsp:param name="action" value="/manager-user-search"/>
                </jsp:include>
            </div>
            <div>
                <a class="btn btn-default mgb-15 mgt-20" href="<s:url value="/insert-user"/>"><img src="<s:url value="public/asserts/images/add_user.png"/> " class="mgr-10">${messageSource.getMessage("insertUser",null,locale)}</a>
                <c:if  test="${requestScope.error != null}">
                    <span class="error">${messageSource.getMessage(requestScope.error,null,locale)}</span>
                </c:if>
            </div>

            <c:if test="${param.query_search == null}">
                <jsp:include page="templates/forms/select.jsp">
                    <jsp:param name="target" value="/manager-user"/>
                    <jsp:param name="search" value="''"/>
                </jsp:include>
            </c:if>

            <c:if test="${param.query_search != null}">
                <jsp:include page="templates/forms/select.jsp">
                    <jsp:param name="target" value="/manager-user-search"/>
                    <jsp:param name="search" value="'${param.query_search}'"/>
                </jsp:include>
            </c:if>


            <jsp:include page="templates/tables/table_user.jsp"/>
         </div>
    <hr>
</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>