<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:forEach var="post" items="${postList}">
    <!-- First Blog Post -->
    <h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title} </a></h2> <!--button>${post.id}</button-->
    <span class="lead">
        <span class="fs-15">${messageSource.getMessage("by",null,locale)}</span>
        <a href="/list-post-by-user?username=${post.user.userName}" class="fs-15">${post.user.userName}</a>
    </span>
    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
    <p><span>${messageSource.getMessage("postTime",null,locale)}</span>
            ${dateUtil.format(post.timePost,sessionScope.dateFormat)}

        <c:if test="${sessionScope.username!=null}">
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
    <%--<hr>--%>
    ${pageContext.setAttribute("str","\\<.*?>")}
    <c:if test="${post.content.replaceAll(str,'').length()>500}">
        <p>${post.content.replaceAll(str,"").substring(0,500)}...</p>
    </c:if>
    <c:if test="${post.content.replaceAll(str,'').length()<500}">
        <p>${post.content.replaceAll(str,"")}...</p>
    </c:if>

    <%--<p>${ Jsoup.parse(post.content).text()}</p>--%>
    <a class="btn btn-primary" href="<s:url value="/post?id=${post.id}"/>" target="_self"> ${messageSource.getMessage("read",null,locale)} <span class="glyphicon glyphicon-chevron-right"></span></a>
    <c:if test="${sessionScope.username!=null && post.user.userName==sessionScope.username}">
        <a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>" title=" ${messageSource.getMessage("edit",null,locale)}">
            <img src="<s:url value="public/asserts/images/edit.gif"/>" alt="">
        </a>
        <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" title=" ${messageSource.getMessage("delete",null,locale)}" href="<s:url value="/delete-post?id=${post.id}"/>">
            <span class="glyphicon glyphicon-remove mgl-10"></span>
        </a>
    </c:if>
</c:forEach>