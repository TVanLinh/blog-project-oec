<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>


<jsp:include page="templates/headers/head.jsp"/>

<jsp:include page="templates/navbars/navbar.jsp"/>
<!-- Navigation -->
<!-- Page Content -->
<div class="container">

    <div class="row" >

        <div class="col-lg-8">

            <%--<!-- Blog Post -->--%>
               <%--link image:  ${linkImage}--%>
            <%--<!-- Title -->--%>
                <h2><a href="<s:url value="/post?id=${post.id}"/>" target="_blank">${post.title} </a></h2>

                <p><span>${messageSource.getMessage("postTime",null,locale)}</span>
                    ${dateUtil.format(post.timePost,sessionScope.dateFormat)}
                </p>
                <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-3">${messageSource.getMessage("postTime",null,locale)}</span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</p>
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

                ${messageSource.getMessage("view",null,locale)}: <button class="btn-sm btn-xs">${post.numberView} </button>
            <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="<s:url value="/delete-post?id=${post.id}"/>">${messageSource.getMessage("delete",null,locale)}</a>
            <hr>

            <!-- Blog Comments -->

            <div id="comment">
                <jsp:include page="templates/components/comment.jsp"/>
            </div>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <div>
            <jsp:include page="templates/slidebar/slidebar-post.jsp"/>
        </div>

    </div>
    <!-- /.row -->

    <hr>
    <!-- Footer -->

</div>
<!-- /.container -->


<script src="<s:url value="/public/asserts/js/main.js"/>"></script>

<jsp:include page="templates/footers/footer.jsp"/>
