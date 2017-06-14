<%@include file="../components/import_libary.jsp"%>
<jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>
<c:if test="${param.number != null}">
    <c:set var="numberViewPost" value="${param.number}"/>
</c:if>
<c:if test="${param.number == null}">
    <c:set var="numberViewPost" value="${numberView.getNumberView()}"/>
</c:if>
<ul class="pager">
    <c:if test="${param.page != null}">
        <c:set var="paramPage" value="${param.page}" scope="request"/>
    </c:if>

    <c:if test="${param.page == null}">
        <c:set var="paramPage" value="1" scope="request"/>
    </c:if>

    <c:if test="${param.query_search == null}">
        <c:if test="${paramPage>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageTarget}?page=${paramPage-1}&number=${numberViewPost}"/>">&larr;<s:message
                        code="back"/></a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>=(paramPage)) && (requestScope.totalList % requestScope.limit)!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
                <li class="next">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}&number=${numberViewPost}"/>"><s:message
                            code="next"/>
                        &rarr;</a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>(paramPage)) && (requestScope.totalList % requestScope.limit)==0}">
            <li class="next">
                <c:if test="${requestScope.postList.size()!=0}">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}&number=${numberViewPost}"/>"><s:message
                            code="next"/>
                        &rarr;</a>
                </c:if>
            </li>
        </c:if>

    </c:if>



    <c:if test="${param.query_search !=null}">
        <c:if test="${paramPage>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageSearch}?page=${paramPage-1}&query_search=${param.query_search}&number=${numberViewPost}"/>">&larr;<s:message
                        code="back"/></a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>=(paramPage)) && (requestScope.totalList % requestScope.limit)!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
                <li class="next">
                    <a href="<s:url value="${param.pageSearch}?page=${paramPage+1}&query_search=${param.query_search}&number=${numberViewPost}"/>"><s:message
                            code="next"/>
                        &rarr;</a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>(paramPage)) && (requestScope.totalList % requestScope.limit)==0}">
            <li class="next">
                <c:if test="${requestScope.postList.size()!=0}">
                    <a href="<s:url value="${param.pageSearch}?page=${paramPage+1}&query_search=${param.query_search}&number=${numberViewPost}"/>"><s:message
                            code="next"/>
                        &rarr;</a>
                </c:if>
            </li>
        </c:if>

    </c:if>
</ul>