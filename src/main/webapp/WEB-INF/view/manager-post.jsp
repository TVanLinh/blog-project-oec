<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12">

            <jsp:include page="templates/menus/menu-admin.jsp"/>
            <!----------------end list-table ------------------------->
        </div>
        <div class="col-xs-12 col-sm-6 col-sm-push-3">
            <div class="text-center">
                <jsp:include page="templates/forms/search3.jsp">
                    <jsp:param name="searchBy" value="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('title',null,locale)}"/>
                    <jsp:param name="action" value="/manager-post-search"/>
                </jsp:include>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="col-xs-12">
            <jsp:include page="templates/tables/table_post_all.jsp"/>
        </div>



    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>

<jsp:include page="templates/footers/footer.jsp"/>