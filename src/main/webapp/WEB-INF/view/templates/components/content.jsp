<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:forEach var="post" items="${list}">

    <h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title} </a></h2>

    <span class="lead">
        <span class="fs-15"><s:message code="by"/></span>
        <a href="<s:url value="/list-post-by-user?username=${post.user.userName}"/>"
           class="fs-15">${post.user.userName}</a>
    </span>

    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>

    <p>
        <span><s:message code="postTime"/> </span>
        <span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</span>
    </p>
    <hr>

    <c:if test="${post.image.link!=null}">
        <img class="img-responsive pdb-15" src="${post.image.link}" alt="${post.image.alt}">
    </c:if>
    <c:if test="${post.image.link==null}">
        <img class="img-responsive pdb-15" src="http://placehold.it/900x300" alt="">
    </c:if>

    ${pageContext.setAttribute("str","\\<.*?>")}

    <c:if test="${post.content.replaceAll(str,'').length()>500}">
        <p>${post.content.replaceAll(str,"").substring(0,500)}...</p>
    </c:if>
    <c:if test="${post.content.replaceAll(str,'').length()<500}">
        <p>${post.content.replaceAll(str,"")}...</p>
    </c:if>

    <a class="btn btn-primary" href="<s:url value="/post?id=${post.id}"/>" target="_self">
        <span><s:message code="read"/></span>
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>

    <c:if test="${sessionScope.username!=null && post.user.userName==sessionScope.username}">
        <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>"
           title="<s:message code="edit" />">
            <i class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i>
        </a>
        <a id="action-" onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"
           title=" <s:message code="delete" />" href="<s:url value="/delete-post?id=${post.id}"/>">
            <i class="fa fa-trash-o mgl-15"></i>
        </a>
    </c:if>

</c:forEach>