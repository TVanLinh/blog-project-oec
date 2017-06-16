<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
<style>
    .menu-item:nth-child(2)
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

        <c:if test="${list.size() != 0}">
            <h1 class="text-center"><s:message code="listpost"/></h1>
        </c:if>
        <c:if test="${list.size( )== 0 && param.query_search == null}">
            <h1 class="text-center"><s:message code="notpost"/></h1>
        </c:if>
        <c:if test="${list.size( )== 0 && param.query_search != null}">
            <h1 class="text-center"><s:message code="not.post.find"/></h1>
        </c:if>

        <div class="col-md-10 col-xs-12 ">
            <div class="text-center">
                <jsp:include page="templates/forms/search3.jsp">
                    <jsp:param name="searchBy" value="search.by.title.author"/>
                    <jsp:param name="action" value="/manager-post-search"/>
                </jsp:include>
            </div>


            <c:if test="${requestScope.error != null}">
                <div class="alert alert-success"><s:message code="${requestScope.error}"/></div>
            </c:if>

            <c:if test="${list.size() > 0}">
                <c:if test="${param.query_search == null}">
                    <jsp:include page="templates/forms/select.jsp">
                        <jsp:param name="target" value="/manager-post"/>
                        <jsp:param name="search" value="''"/>
                    </jsp:include>
                </c:if>

                <c:if test="${param.query_search != null}">
                    <jsp:include page="templates/forms/select.jsp">
                        <jsp:param name="target" value="/manager-post-search"/>
                        <jsp:param name="search" value="'${param.query_search}'"/>
                    </jsp:include>
                </c:if>

            </c:if>

            <jsp:include page="templates/tables/table_post_all.jsp"/>

        </div>
        <div class="clearfix"></div>


    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>