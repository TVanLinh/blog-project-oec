<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template/head.jsp"/>

<!-- Navigation -->
<jsp:include page="template/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12">
            <ul class="list-group menu-admin">
                <li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="<s:url value="/manager-post"/>"><img src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10">${messageSource.getMessage("managerPost",null,locale)}</a></li>
                <li class="list-inline btn btn-success mgr-10  mgb-15"><a href="<s:url value="/configuration"/>"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>
                <li class="list-inline btn btn-warning mgl-10  mgb-15"> <a href="<s:url value="/manager-user"/>"><i class="glyphicon glyphicon-user mgr-10"></i>${messageSource.getMessage("managerUser",null,locale)}</a></li>
            </ul>


            <jsp:include page="template/search3.jsp">
                 <jsp:param name="action" value="/manager-post-search"/>
            </jsp:include>
            <jsp:include page="template/table_post_all.jsp"/>
            <!----------------end list-table ------------------------->
        </div>

    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>

<jsp:include page="template/footer.jsp"/>