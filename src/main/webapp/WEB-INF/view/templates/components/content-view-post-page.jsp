<%@include file="import_libary.jsp"%>
<%--<h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title}</a></h2>--%>
<h2>${post.title}</h2>

<span class="lead">
    <span class="fs-15">${messageSource.getMessage("by",null,locale)}</span>
    <a href="<s:url value="/list-post-by-user?username=${post.user.userName}"/>" class="fs-15">${post.user.userName}</a>
</span>

<jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
<p><span>${messageSource.getMessage("postTime",null,locale)}</span>
    ${dateUtil.format(post.timePost,sessionScope.dateFormat)}
</p>
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

<c:if test="${sessionScope.username!=null &&( post.user.userName==sessionScope.username || requestScope.userDAO.isRoleAdmin(post.user)) }">
    <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>" title=" ${messageSource.getMessage("edit",null,locale)}">
        <i class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i>
    </a>
    <a id="action-" onclick="return window.confirm('${messageSource.getMessage("confirm.delete.post",null,locale)}')" title=" ${messageSource.getMessage("delete",null,locale)}" href="<s:url value="/delete-post?id=${post.id}"/>">
        <i class="fa fa-trash-o mgl-15"></i>
    </a>
</c:if>