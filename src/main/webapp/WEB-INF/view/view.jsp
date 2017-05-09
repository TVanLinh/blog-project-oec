<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="template/head.jsp"/>
<body>

<jsp:include page="template/navbar.jsp"/>
<!-- Navigation -->
<!-- Page Content -->
<div class="container">

    <div class="row" >

        <div class="col-lg-8">

            <!-- Blog Post -->
               link image:  ${linkImage}
            <!-- Title -->
            <h2>${post.title}</h2>

            <!--Userthor -->
            <span class="lead">
                by <a href="#">${post.user.userName}</a>${post.status}
            </span>
            <hr>

            <!-- Date/Time -->
            <p><span class="glyphicon glyphicon-time"></span> Posted on  ${post.timePost}</p>

            <hr>

            <!-- Post Content -->
             <div id="content">
                ${post.content}
             </div>
            <hr>
            <button class="btn-sm btn-xs">Like</button>${post.numberLike}  view: <button class="btn-sm btn-xs">${post.numberView} </button>
            <a href="/update">Update</a> <a href="/update">Xoa</a>
            <hr>

            <!-- Blog Comments -->

            <div id="comment">
                <jsp:include page="template/comment.jsp"/>
            </div>

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
