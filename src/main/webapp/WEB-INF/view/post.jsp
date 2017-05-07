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

                <!-- Blog Post -->
                <!-- Title -->
                <h1>Blog Post Title</h1>
                <hr>

                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted on August 24, 2013 at 9:00 PM</p>

                <hr>

                <!-- Preview Image -->
                <img class="img-responsive" src="http://placehold.it/900x300" alt="">

                <hr>

                <!-- Post Content -->
                ${post.getContent}
                <hr>

                <button class="btn-sm btn-xs">Like</button>  view: <button class="btn-sm btn-xs">10000000</button>

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
