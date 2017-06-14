<%@include file="../components/import_libary.jsp"%>

<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="request"/>
</c:if>
<ul class="pager">
        <c:if test="${paramPage>=2}">
            <li class="previous">
                <a href="<s:url value="${paramTarget}?username=${param.username}&page=${paramPage-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

    <c:if test="${(requestScope.totalList/requestScope.limit>=paramPage) && (requestScope.totalList % requestScope.limit)!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
               <li class="next">
                    <a href="<s:url value="${paramTarget}?username=${param.username}&page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
               </li>
            </c:if>
        </c:if>

    <c:if test="${(requestScope.totalList/requestScope.limit>paramPage) && (requestScope.totalList % requestScope.limit)==0}">
        <li class="next">
            <c:if test="${requestScope.postList.size()!=0}">
                <a href="<s:url value="${paramTarget}?username=${param.username}&page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
            </c:if>
        </li>
    </c:if>
</ul>