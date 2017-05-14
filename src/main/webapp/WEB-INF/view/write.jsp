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
        <form:form action="/write-post"  commandName="post" >
                <label class="fs-20">Title:</label>
                <%--<form:errors path="title"/>--%>
                <input name="title" type="text" class="input-xs mgb-40" style=";margin-bottom: 30px"><br>
                  <textarea class="ckeditor" cols="80" id="content" name="content" rows="10">
                    This is my textarea to be replaced with CKEditor.
                    </textarea>
                    <select name="status" >
                        <option value="1" >Public</option>
                        <option value="0">Private</option>
                    </select>
                    <input class="hide" name="link-image" id="link-image" type="text" >
                    <input class="hide" name="alt-image" id="alt-image" type="text" >
                <input type="submit" value="send" class="mgt-25 btn-md" onclick="getImages()">
        </form:form>

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
    <%--<button onclick="alert(getImages())">jfkjkgkjgfkjfg</button>--%>
</div>
<!-- /.container -->

</body>
<script type="text/javascript">
    function getImages()
    {
        var input=document.getElementById('cke_122_textInput');
        var outPut=document.getElementById('link-image');

        var altInput=document.getElementById('cke_129_textInput');
        var atlOutPut=document.getElementById('alt-image');
        if(input!=null)
        {
            outPut.value=input.value;
        }
        if(altInput!=null)
        {
            atlOutPut.value=altInput.value;
        }
        alert(input);
    }
</script>

</html>
