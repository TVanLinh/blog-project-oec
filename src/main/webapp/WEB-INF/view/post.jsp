<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="template/head.jsp"/>

<body>
    <script src="<s:url value="public/jquery/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
    <!-- Navigation -->
   <jsp:include page="template/navbar.jsp"/>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->
                <!-- Title -->
                <h2><a href="/post?id=${post.id}" target="_self">${post.title}</a></h2>

                <span class="lead">
                        <span class="fs-15">${messageSource.getMessage("by",null,locale)}</span> <a href="index.php" class="fs-15">${post.user.userName}</a>
                    </span>
                <jsp:useBean id="dateUtil" class="Utils.DateFormatUtil" scope="session"/>
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-3">${messageSource.getMessage("postTime",null,locale)}</span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</p>
                <hr>

                <!-- Post Content -->
                ${post.content}

                <jsp:useBean id="cookieUtils" type="Utils.CookieUtils" scope="session" class="Utils.CookieUtils"/>
                <hr>
                <c:if test="${cookie.status_like_post==null ||cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==false}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/notlike.png"/>" alt="not like"  class="">
                </c:if>

                <c:if test="${cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==true}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/like.png"/>" alt="like"  class="">
                </c:if>

                </button><button class="btn-sm btn-xs" id="number-like">${post.numberLike}</button>

                ${messageSource.getMessage("view",null,locale)}: <button class="btn-sm btn-xs">${post.numberView} </button>

               <c:if test="${sessionScope.username!=null}">
                    <a id="action-update" href="/update?action=update&id=${post.id}"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="/delete-post?id=${post.id}">${messageSource.getMessage("delete",null,locale)}</a>
                </c:if>
                <hr>
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

        <jsp:include page="template/footer.jsp"/>

    </div>
    <script src="<s:url value="/public/asserts/js/main.js"/>"></script>
</body>

</html>