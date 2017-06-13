<%@include file="../components/import_libary.jsp"%>
<ul class="pager">
   <%--<c:if test="${param.page == null}">--%>
       <%--<jsp:useBean id="entites" class="model.RequestEntities" scope="page"/>--%>
   <%--</c:if>--%>


       <c:if test="${param.page != null}">
           <c:set var="paramPage" value="${param.page}" scope="page"/>
       </c:if>

       <c:if test="${param.page == null}">
           <c:set var="paramPage" value="1" scope="request"/>
       </c:if>

        <c:if test="${param.title == null}">
            <c:if test="${paramPage>=2}">
                <li class="previous">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
                </li>
            </c:if>

            <c:if test="${(requestScope.totalList/requestScope.limit>=(paramPage)) && (requestScope.totalList % requestScope.limit)!=0}">
                <c:if test="${requestScope.postList.size()!=0}">
                    <li class="next">
                        <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                    </li>
                </c:if>
            </c:if>

            <c:if test="${(requestScope.totalList/requestScope.limit>(paramPage)) && (requestScope.totalList % requestScope.limit)==0}">
                <li class="next">
                    <c:if test="${requestScope.postList.size()!=0}">
                        <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                    </c:if>
                </li>
            </c:if>

        </c:if>



    <c:if test="${param.title !=null}">
        <c:if test="${param.page>=2}">
            <li class="previous">
                <a href="<s:url value="${param.pageTarget}?page=${paramPage-1}&title=${param.title}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
            </li>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>=(paramPage)) && (requestScope.totalList % requestScope.limit)!=0}">
            <c:if test="${requestScope.postList.size()!=0}">
                <li class="next">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}&title=${param.title}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </li>
            </c:if>
        </c:if>

        <c:if test="${(requestScope.totalList/requestScope.limit>(paramPage)) && (requestScope.totalList % requestScope.limit)==0}">
            <li class="next">
                <c:if test="${requestScope.postList.size()!=0}">
                    <a href="<s:url value="${param.pageTarget}?page=${paramPage+1}&${param.title}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                </c:if>
            </li>
        </c:if>

    </c:if>
</ul>