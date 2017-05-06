<%@page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home page blog</title>

    <!-- Bootstrap Core CSS -->
    <link href="public/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="public/bootstrap/css/blog-post.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" type="text/javascript"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" type="text/javascript"></script>
    <!--[endif]-->
    <script src="public/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="public/ckeditor/ckeditor.js" type="text/javascript"></script>

</head>

<body>

<!-- Navigation -->
<jsp:include page="template/navbar.jsp"/>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8">
            <form action="/write-post" method="get">
                Title:  <input name="title" type="text" style="height: 30px;width: 200px;margin-bottom: 30px"><br>
                  <textarea class="ckeditor" cols="80" id="content" name="content" rows="10">
                    This is my textarea to be replaced with CKEditor.
                    </textarea>
                <input type="submit" value="send">
                  <script>
                    // Replace the <textarea id="editor1"> with a CKEditor
                    // instance, using default configuration.
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
