<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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

            <%--<!-- Blog Post -->--%>
               <%--link image:  ${linkImage}--%>
            <%--<!-- Title -->--%>
                <h2><a href="/post?id=${post.id}" target="_blank">${post.title} <button>${post.id}</button></a></h2>

                <span class="lead">
                        <span class="fs-15">By</span> <a href="index.php" class="fs-15">${post.user.userName}</a>
                    </span>
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-3">Posted on</span> ${post.timePost}</p>
                <hr>

            <!-- Post Content -->
             <div id="content">
                ${post.content}
             </div>
            <hr>
                <c:if test="${cookie.status_like_post==null ||cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==false}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/notlike.png"/>" alt="not like"  class="">
                </c:if>

                <c:if test="${cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==true}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/like.png"/>" alt="like"  class="">
                </c:if>

            </button><button class="btn-sm btn-xs" id="number-like">${post.numberLike}</button>

            view: <button class="btn-sm btn-xs">${post.numberView} </button>
            <a id="action-update" href="/update?action=update&id=${post.id}"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="/delete-post?id=${post.id}">Xoa</a>
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


<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
</body>

</html>
