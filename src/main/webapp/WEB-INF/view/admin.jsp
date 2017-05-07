<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    Admin
    <!-- Blog Sidebar Widgets Column -->
    <div>
        <jsp:include page="template/slidebar.jsp"/>
    </div>
</div>
<!-- /.row -->

<hr>

<jsp:include page="template/footer.jsp"/>

</div>
</body>

</html>
