<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="template/navbar-admin.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12 ">
            <ul class="list-group menu-admin">
                <li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="/manager-post"><img src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10">${messageSource.getMessage("managerPost",null,locale)}</a></li>
                <li class="list-inline btn btn-success mgr-10  mgb-15"><a href="/configuration"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>
                <li class="list-inline btn btn-warning mgl-10  mgb-15"> <a href="/manager-user"><i class="glyphicon glyphicon-user mgr-10"></i>${messageSource.getMessage("managerUser",null,locale)}</a></li>
            </ul>
            <a class="btn btn-default mgb-15" href="/insert-user"><img src="<s:url value="public/asserts/images/add_user.png"/> " class="mgr-10">${messageSource.getMessage("insertUser",null,locale)}</a>
            <ul class="list-group">
                <li class="list-group-item">${messageSource.getMessage("totalUser",null,locale)}<span class="badge" id="num">${requestScope.userList.size()}</span></li>
            </ul>
            <%--<jsp:include page="template/search.jsp">--%>
                <%--<jsp:param name="action" value="mySearch.userSearch('/search-user','#search','#table-all-user')"/>--%>
            <%--</jsp:include>--%>

            <%--<jsp:include page="template/table-list-user.jsp"/> --%>
            <jsp:include page="template/table_user.jsp"/>

            <!----------------end list-table ------------------------->
        </div>
    </div>
    <!-- /.row -->
    <hr>
    <jsp:include page="template/footer.jsp"/>
</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
</body>
</html>
