<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page language="java" pageEncoding="UTF-8" %>


<jsp:include page="templates/headers/head.jsp"/>


<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>

<!-- Page Content -->
<div class="container">
    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-12">
           <jsp:include page="templates/forms/write-post.jsp">
               <jsp:param name="action" value="/write-update"/>
               <jsp:param name="title" value="${sessionScope.postUpdate.title}"/>
               <jsp:param name="content" value="${sessionScope.postUpdate.content}"/>
           </jsp:include>
        </div>

        <!-- Blog Sidebar Widgets Column -->
        <%--<div>--%>
            <%--<jsp:include page="templates/slidebar-post.jsp"/>--%>
        <%--</div>--%>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->

</div>
<!-- /.container -->
<%--<button onclick="getImages()">ok</button>--%>
</body>
<script type="text/javascript" src="<s:url value="public/asserts/js/check_valid_form.js" />"></script>\
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>