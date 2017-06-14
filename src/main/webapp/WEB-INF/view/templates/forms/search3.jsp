<%@ include file="../components/import_libary.jsp" %>
<div >
        <form method="get" action="<s:url value="${param.action}"/> " onsubmit="return mySearch.checkFormSearchValid('#search')">
            <div class="input-group search">
                <input type="text" class="form-control "
                       placeholder="<s:message code="searchBy"/> <s:message code="title"/> " id="search"
                       name="query_search" onsubmit="return mySearch.checkFormSearchValid('#search')"
                       value="${param.query_search}">
                <span class="input-group-btn">
                <button class="btn btn-default" type="submit"  onclick="return mySearch.checkFormSearchValid('#search')" onsubmit="return mySearch.checkFormSearchValid('#search')" >
                    <span class="glyphicon glyphicon-search"></span>
                </button>
                </span>
            </div>
        </form>
</div>

<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="page"/>
</c:if>

<div>
    <jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>
    <c:if test="${requestScope.postList.size()>0}">

        <H3 class="pdt-10 pdb-10">
            <span class="pd-10"><s:message code="recordFrom"/></span>
            <span>${(paramPage-1)*requestScope.limit+1} </span>
            <span class="pd-10"><s:message code="to"/></span>
            <span>${(paramPage-1)*requestScope.limit+requestScope.postList.size()}</span>
            <span class="pd-10">
                    <s:message code="in"/>
            </span>
            <span>${requestScope.totalList}</span>
        </H3>
    </c:if>

</div>

<script src="<s:url value="/public/asserts/js/search.js"/> "></script>