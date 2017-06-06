<%@include file="../components/import_libary.jsp"%>

<ul class="pager">
        <c:if test="${requestScope.page>=2}">
            <li class="previous">
                <a href="<s:url value="${param.page}?${param.query}&page=${requestScope.page-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>=(requestScope.page)) && (requestScope.totalList % requestScope.limit)!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
               <li class="next">
                    <a href="<s:url value="${param.page}?${param.query}&page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
               </li>
            </c:if>
        </c:if>

    <c:if test="${(requestScope.totalList/requestScope.limit>(requestScope.page)) && (requestScope.totalList % requestScope.limit)==0}">
        <li class="next">
            <c:if test="${requestScope.postList.size()!=0}">
                <a href="<s:url value="${param.page}?${param.query}&page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
            </c:if>
        </li>
    </c:if>

</ul>

${requestScope.postList.size()!=0}