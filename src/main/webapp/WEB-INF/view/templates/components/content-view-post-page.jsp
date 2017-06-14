<%@include file="import_libary.jsp"%>
<%--<h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title}</a></h2>--%>
<h2>${post.title}</h2>

<span class="lead">
    <span class="fs-15"><s:message code="by"/></span>
    <a href="<s:url value="/list-post-by-user?username=${post.user.userName}"/>" class="fs-15">${post.user.userName}</a>
</span>

<jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
<p>
    <span><s:message code="postTime"/></span>
    <span>${dateUtil.format(post.timePost,sessionScope.dateFormat)}</span>
</p>
<hr>


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

<s:message code="view"/>:
<button class="btn-sm btn-xs">${post.numberView} </button>

<c:if test="${sessionScope.username!=null &&( post.user.userName==sessionScope.username || requestScope.userDAO.isRoleAdmin(post.user)) }">
    <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>"
       title=" <s:message code="edit" />">
        <i class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i>
    </a>
    <a id="action-" onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"
       title=" <s:message code="delete" />" href="<s:url value="/delete-post?id=${post.id}"/>">
        <i class="fa fa-trash-o mgl-15"></i>
    </a>
</c:if>