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
                <div class="col-md-8">
                    <ul class="list-group">
                        <li class="list-inline btn btn-danger mgl-10 mgr-10" onclick="A.getPostImprove('action=approve&id=4',44)">Manager Post</li>
                        <li class="list-inline btn btn-success mgr-10"><a href="/configuration"><span class="glyphicon glyphicon-cog mgr-5"></span>Configuration System</a></li>
                        <li class="list-inline btn btn-warning mgl-10 ">Manager User</li>
                    </ul>
                    <ul class="list-group">
                        <li class="list-group-item">Total post have not approve <span class="badge" id="numberApprove">${requestScope.totalPost}</span></li>
                    </ul>
                    <jsp:include page="template/table-list-post.jsp"/>
                </div>
            <!-- Blog Sidebar Widgets Column -->
                <div>
                    <jsp:include page="template/slidebar.jsp"/>
                </div>
        </div>
    <!-- /.row -->
     <hr>
        <jsp:include page="template/footer.jsp"/>

    </div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
</body>
</html>
