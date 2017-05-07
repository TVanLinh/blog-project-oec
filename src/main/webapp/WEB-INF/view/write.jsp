<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="template/navbar.jsp"/>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8">
            <form action="/write-post" method="get">
                <label class="fs-20">Title:</label>
                <form:errors path="title"/>
                <input name="title" type="text" class="input-xs mgb-40" style=";margin-bottom: 30px"><br>
                  <textarea class="ckeditor" cols="80" id="content" name="content" rows="10">
                    This is my textarea to be replaced with CKEditor.
                    </textarea>
                <input type="submit" value="send" class="mgt-25 btn-md">

                  <script>
                    CKEDITOR.replace( 'content' );
                </script>
            </form>

            <hr>

            <!-- Blog Comments -->
            <%--<div id="comment">--%>
                <%--<jsp:include page="comment.jsp"/>--%>
            <%--</div>--%>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <div>
            <jsp:include page="template/slidebar-post.jsp"/>
        </div>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->
    <jsp:include page="template/footer.jsp"/>

</div>
<!-- /.container -->



</body>

</html>
