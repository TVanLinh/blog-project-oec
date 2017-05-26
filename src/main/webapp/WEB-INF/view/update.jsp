<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
        <div class="col-lg-12">
            <form:form action="/write-update"  commandName="post">
                <label class="fs-20">${messageSource.getMessage("title",null,locale)}:</label>
                <%--<form:errors path="title"/>--%>
                <input name="title" id="idTitle" type="text" class="input-xs mgb-40 title" style=";margin-bottom: 30px" value="${sessionScope.postUpdate.title}"><br>
                <textarea class="ckeditor" cols="80" id="content" name="content" rows="50">
                     ${sessionScope.postUpdate.content}
                    </textarea>
                <select name="status" >
                    <option value="1" >${messageSource.getMessage("public",null,locale)}</option>
                    <option value="0">${messageSource.getMessage("private",null,locale)}</option>
                </select>
                <input class="hide" name="link-image" id="link-image" type="text" >
                <input class="hide" name="alt-image" id="alt-image" type="text" >
                <input type="submit" value="${messageSource.getMessage("save",null,locale)}" class="mgt-25 btn-md" onclick="return getImages()" onsubmit="return getImages()">
            </form:form>

            <hr>

            <!-- Blog Comments -->
            <%--<div id="comment">--%>
            <%--<jsp:include page="comment.jsp"/>--%>
            <%--</div>--%>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <%--<div>--%>
            <%--<jsp:include page="template/slidebar-post.jsp"/>--%>
        <%--</div>--%>

    </div>
    <!-- /.row -->

    <hr>

    <!-- Footer -->
    <jsp:include page="template/footer.jsp"/>
</div>
<!-- /.container -->
<%--<button onclick="getImages()">ok</button>--%>
</body>
<script type="text/javascript" src="<s:url value="public/asserts/js/check_valid_form.js" />">
</script>

</html>
