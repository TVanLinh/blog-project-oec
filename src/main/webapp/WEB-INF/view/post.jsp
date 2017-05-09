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
                <h2>${post.title}</h2>
                <span class="lead">
                by <a href="#">${post.user.userName}</a>${post.status}
                </span>
                <hr>
                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> Posted on ${post.timePost}</p>

                <hr>

                <%--<!-- Preview Image -->--%>
                <%--<img class="img-responsive" src="http://placehold.it/900x300" alt="">--%>

                <hr>

                <!-- Post Content -->
                ${post.content}
                <hr>

                <button id="#like" onclick="load()" class="btn-sm btn-xs">Like</button><button class="btn-sm btn-xs">${post.numberLike}</button>  view: <button class="btn-sm btn-xs">${post.numberView} </button>

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


    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--$("#like").on("click",function () {--%>
                <%--load();--%>
            <%--});--%>
        <%--});--%>
        <%--function load() {--%>

                <%--$.ajax({method:"POST",url:"/like",dataType:"json",data:{"id":id},--%>
                    <%--success:function (result,status) {--%>
                        <%--$("#like").text("ok");--%>
                        <%--alert("ok");--%>
                        <%--//  alert(result);--%>
                    <%--},error:function () {--%>
                        <%--alert("error");--%>
                    <%--}});--%>
        <%--}--%>
    <%--</script>--%>
    <%--<script src="public/asserts/js/main.js" type="text/javascript"></script>--%>
</body>

</html>
