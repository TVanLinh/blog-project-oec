<%@include file="../components/import_libary.jsp"%>
<%--<ul class="pager">--%>
    <%--<jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>--%>
    <%--<c:if test="${requestScope.querySearch==null}">--%>
        <%--<c:if test="${requestScope.page>=2}">--%>
            <%--<li class="previous">--%>
                <%--<a href="<s:url value="${param.page}?page=${requestScope.page-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>--%>
            <%--</li>--%>
        <%--</c:if>--%>

        <%--<c:if test="${(requestScope.totalList/numberView.getNumberView()>=(requestScope.page)) && (requestScope.totalList % numberView.getNumberView())!=0}">--%>
            <%--<c:if test="${requestScope.postList.size()!=0}">--%>
                <%--<li class="next">--%>
                    <%--<a href="<s:url value="${param.page}?page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>--%>
                <%--</li>--%>
            <%--</c:if>--%>
        <%--</c:if>--%>

        <%--<c:if test="${(requestScope.totalList/numberView.getNumberView()>(requestScope.page)) && (requestScope.totalList % numberView.getNumberView())==0}">--%>
            <%--<li class="next">--%>
                <%--<c:if test="${requestScope.postList.size()!=0}">--%>
                    <%--<a href="<s:url value="${param.page}?page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>--%>
                <%--</c:if>--%>
            <%--</li>--%>
        <%--</c:if>--%>

    <%--</c:if>--%>

    <%--<c:if test="${requestScope.querySearch!=null}">--%>
        <%--<c:if test="${requestScope.page>=2}">--%>
            <%--<li class="previous">--%>
                <%--<a href="<s:url value="${param.pageSearch}?page=${requestScope.page-1}&query_search=${requestScope.querySearch}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>--%>
            <%--</li>--%>
        <%--</c:if>--%>

        <%--<c:if test="${(requestScope.totalList/numberView.getNumberView()>=(requestScope.page)) && (requestScope.totalList % numberView.getNumberView())!=0}">--%>
            <%--<c:if test="${requestScope.postList.size()!=0}">--%>
                <%--<li class="next">--%>
                    <%--<a href="<s:url value="${param.pageSearch}?page=${requestScope.page+1}&query_search=${requestScope.querySearch}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>--%>
                <%--</li>--%>
            <%--</c:if>--%>
        <%--</c:if>--%>

        <%--<c:if test="${(requestScope.totalList/numberView.getNumberView()>(requestScope.page)) && (requestScope.totalList % numberView.getNumberView())==0}">--%>
            <%--<li class="next">--%>
                <%--<c:if test="${requestScope.postList.size()!=0}">--%>
                    <%--<a href="<s:url value="${param.pageSearch}?page=${requestScope.page+1}&query_search=${requestScope.querySearch}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>--%>
                <%--</c:if>--%>
            <%--</li>--%>
        <%--</c:if>--%>
    <%--</c:if>--%>
<%--</ul>--%>

<%@include file="../components/import_libary.jsp"%>
<jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>
<ul class="pager">
    <%--<c:if test="${param.page == null}">--%>
    <%--<jsp:useBean id="entites" class="model.RequestEntities" scope="page"/>--%>
    <%--</c:if>--%>


    <c:if test="${param.page != null}">
        <c:set var="paramPage" value="${param.page}" scope="request"/>
    </c:if>

    <c:if test="${param.page == null}">
        <c:set var="paramPage" value="1" scope="request"/>
    </c:if>

    <c:if test="${param.query_search == null}">
        <c:if test="${paramPage>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageTarget}?page=${paramPage-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/numberView.getNumberView()>=(paramPage)) && (requestScope.totalList % numberView.getNumberView())!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
                <li class="next">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${(requestScope.totalList/numberView.getNumberView()>(paramPage)) && (requestScope.totalList % numberView.getNumberView())==0}">
            <li class="next">
                <c:if test="${requestScope.postList.size()!=0}">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </c:if>
            </li>
        </c:if>

    </c:if>



    <c:if test="${param.query_search !=null}">
        <c:if test="${paramPage>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageSearch}?page=${paramPage-1}&query_search=${param.query_search}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/numberView.getNumberView()>=(paramPage)) && (requestScope.totalList % numberView.getNumberView())!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
                <li class="next">
                    <a href="<s:url value="${param.pageSearch}?page=${paramPage+1}&query_search=${param.query_search}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${(requestScope.totalList/numberView.getNumberView()>(paramPage)) && (requestScope.totalList % numberView.getNumberView())==0}">
            <li class="next">
                <c:if test="${requestScope.postList.size()!=0}">
                    <a href="<s:url value="${param.pageSearch}?page=${paramPage+1}&query_search=${param.query_search}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </c:if>
            </li>
        </c:if>

    </c:if>
</ul>