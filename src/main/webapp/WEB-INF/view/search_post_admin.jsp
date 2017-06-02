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

            <ul class="list-group">
                <li class="list-group-item">${messageSource.getMessage("totalAllPost",null,locale)}<span class="badge" id="num">${requestScope.totalPost}</span></li>
            </ul>
            <!-------form search-------------->
            <jsp:include page="templates/forms/search.jsp">
                <jsp:param name="action" value="mySearch.postSearch('/search-all-post','#search','#table-all-post')"/>
            </jsp:include>
            <jsp:include page="templates/tables/table_post_all.jsp"/>
            <!----------------end list-table ------------------------->
        </div>
    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>
