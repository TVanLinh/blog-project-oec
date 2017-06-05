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
        <div class="col-xs-12">
            <jsp:include page="templates/forms/write-post.jsp"/>
        </div>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->

</div>
<!-- /.container -->

<script type="text/javascript" src="<s:url value="public/asserts/js/check_valid_form.js" />">
</script>

<jsp:include page="templates/footers/footer.jsp"/>
