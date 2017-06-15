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
            <c:if test="${list.size() != 0}">
                <h1 class="text-center"><s:message code="listuser"/></h1>
            </c:if>

            <c:if test="${list.size() == 0 && param.query_search == null }">
                <h1 class="text-center"><s:message code="notuser"/></h1>
            </c:if>

            <c:if test="${list.size() == 0 && param.query_search != null}">
                <h1 class="text-center"><s:message code="not.user.find"/></h1>
            </c:if>
            <!----------------end list-table ------------------------->
            <div class="text-center">
                <jsp:include page="templates/forms/search3.jsp">
                    <jsp:param name="searchBy" value="search.by.author"/>
                    <jsp:param name="action" value="/manager-user-search"/>
                </jsp:include>
            </div>
            <div>
                <a class="btn btn-default mgb-15 mgt-20" href="<s:url value="/insert-user"/>"><img
                        src="<s:url value="public/asserts/images/add_user.png"/> " class="mgr-10"><s:message
                        code="insertUser"/></a>
                <c:if  test="${requestScope.error != null}">
                    <span class="error"><s:message code="${requestScope.error}"/></span>
                </c:if>
            </div>
            <c:if test="${list.size() > 0}">
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
            </c:if>


            <jsp:include page="templates/tables/table_user.jsp"/>
         </div>
    <hr>
</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>