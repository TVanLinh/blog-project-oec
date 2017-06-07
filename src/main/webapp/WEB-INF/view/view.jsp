<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>


<jsp:include page="templates/headers/head.jsp"/>

<jsp:include page="templates/navbars/navbar.jsp"/>
<!-- Navigation -->
<!-- Page Content -->
<div class="container">

    <div class="row" >
        <div class="col-xs-12 col-sm-12 ">

            <!--content--->
            <jsp:include page="templates/components/content-view-post-page.jsp"/>
            <!--end content---->
            <div id="comment">
                <%--<jsp:include page="templates/components/comment.jsp"/>--%>
            </div>

        </div>

        <%--<!-- Blog Sidebar Widgets Column -->--%>
            <%--<jsp:include page="templates/slidebar/slidebar-post.jsp"/>--%>

    </div>
    <!-- /.row -->

    <!-- Footer -->

</div>
<!-- /.container -->

<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>

<jsp:include page="templates/footers/footer.jsp"/>
