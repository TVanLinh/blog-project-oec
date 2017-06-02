<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>


    <script src="<s:url value="public/jquery/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
    <!-- Navigation -->
   <jsp:include page="templates/navbars/navbar.jsp"/>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->
                <!-- Title -->
                <h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title}</a></h2>

                <span class="lead">
                        <span class="fs-15">${messageSource.getMessage("by",null,locale)}</span> <a href="index.php" class="fs-15">${post.user.userName}</a>
                    </span>
                <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-3">${messageSource.getMessage("postTime",null,locale)}</span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</p>
                <hr>

                <!-- Post Content -->
                ${post.content}

                <jsp:useBean id="cookieUtils" type="utils.cookie.CookieUtils" scope="session" class="utils.cookie.CookieUtils"/>
                <hr>
                <c:if test="${cookie.status_like_post==null ||cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==false}">
                    <img id="like" onclick="A.like('<s:url value="/like"/>' ,${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/notlike.png"/>" alt="not like"  class="">
                </c:if>

                <c:if test="${cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==true}">
                    <img id="like" onclick="A.like('<s:url value="/like"/>',${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/like.png"/>" alt="like"  class="">
                </c:if>

                </button><button class="btn-sm btn-xs" id="number-like">${post.numberLike}</button>

                ${messageSource.getMessage("view",null,locale)}: <button class="btn-sm btn-xs">${post.numberView} </button>

               <c:if test="${sessionScope.username!=null}">
                    <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="/delete-post?id=${post.id}">${messageSource.getMessage("delete",null,locale)}</a>
                </c:if>
                <hr>
                <div id="comment">
                   <jsp:include page="templates/components/comment.jsp"/>
               </div>

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div>
                <%--<jsp:include page="templates/slidebar-post.jsp"/>--%>
                <%--<div>--%>
                    <jsp:include page="templates/slidebar/slidebar-post.jsp">
                        <jsp:param name="action" value="mySearch.formSearch('/view-search?title=','#search')"/>
                        <jsp:param name="urlTarget" value="/view-search"/>
                    </jsp:include>
                <%--</div>--%>
            </div>


        </div>
        <!-- /.row -->

        <hr>



    </div>
    <script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>