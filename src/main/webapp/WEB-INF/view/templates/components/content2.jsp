<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:forEach var="post" items="${list}">
    <!-- First Blog Post -->
    <h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title} </a></h2> <!--button>${post.id}</button-->
    <span class="lead">
        <span class="fs-15"><s:message code="by"/></span>
        <a href="/list-post-by-user?username=${post.user.userName}" class="fs-15">${post.user.userName}</a>
    </span>

    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
    <p>
        <span><s:message code="postTime"/></span>
        <span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</span>

        <c:if test="${post.user.userName == sessionScope.userLogin.userName}">
            <span class="mgl-20 fs-15 color-red">
                <c:if test="${post.status==0}">
                    private
                </c:if>
                <c:if test="${post.status!=0}">
                    public
                </c:if>
            </span>
        </c:if>
    </p>
    <hr>

    <c:if test="${post.image.link!=null}">
        <img class="img-responsive pdb-15" src="${post.image.link}">
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

    <c:if test="${sessionScope.userLogin != null && post.user.userName == sessionScope.userLogin.userName}">
        <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>" title=" ${messageSource.getMessage("edit",null,locale)}">
            <i class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i>
        </a>
        <a id="action-" onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"
           title="<s:message code="delete" />" href="<s:url value="/delete-post?id=${post.id}"/>">
            <i class="fa fa-trash-o mgl-15"></i>
        </a>
    </c:if>
</c:forEach>