<%@include file="../components/import_libary.jsp"%>
<ul class="pager">
    <jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>
    <c:if test="${requestScope.querySearch==null}">
        <c:if test="${requestScope.page>=2}">
            <li class="previous">
                <a href="<s:url value="${param.page}?page=${requestScope.page-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${requestScope.page<=requestScope.totalList/numberView.getNumberView()}">
            <li class="next">
                <c:if test="${postList.size()!=0}">
                    <a href="<s:url value="${param.page}?page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </c:if>
            </li>
        </c:if>
    </c:if>

    <c:if test="${requestScope.querySearch!=null}">
        <c:if test="${requestScope.page>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageSearch}?page=${requestScope.page-1}&query_search=${requestScope.querySearch}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${requestScope.page<=requestScope.totalList/numberView.getNumberView()}">
            <li class="next">
                <c:if test="${postList.size()!=0}">
                    <a href="<s:url value="${param.pageSearch}?page=${requestScope.page+1}&query_search=${requestScope.querySearch}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </c:if>
            </li>
        </c:if>
    </c:if>
</ul>