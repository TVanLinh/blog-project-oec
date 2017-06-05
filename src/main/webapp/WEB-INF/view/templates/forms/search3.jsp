<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Blog Search class="well" Well -->
<div >
    <%--<h4>Blog Search</h4>--%>
        <form method="get" action="<s:url value="${param.action}"/> " onsubmit="return mySearch.checkFormSearchValid('#search')">
            <div class="input-group search">
                <input type="text" class="form-control " placeholder="${param.searchBy}" id="search" name="query_search" onsubmit="return mySearch.checkFormSearchValid('#search')" value="${requestScope.querySearch}">
                <span class="input-group-btn">
                <button class="btn btn-default" type="submit"  onclick="return mySearch.checkFormSearchValid('#search')" onsubmit="return mySearch.checkFormSearchValid('#search')" >
                    <span class="glyphicon glyphicon-search"></span>
                </button>
                </span>
            </div>
        </form>
</div>

<div>
    <jsp:useBean id="numberView" class="utils.number.NumberViewSort"/>
    <c:if test="${requestScope.postList.size()>0}">

        <H3>
            <span class="pd-10">${messageSource.getMessage("recordFrom",null,locale)}</span>
            <span>${(requestScope.page-1)*numberView.getNumberView()+1} </span>
            <span class="pd-10">${messageSource.getMessage("to",null,locale)}</span>
            <span>${(requestScope.page-1)*numberView.getNumberView()+requestScope.postList.size()}</span>
            <span class="pd-10">
                    ${messageSource.getMessage("in",null,locale)}
            </span>
            <span>${requestScope.totalPost}<span>${requestScope.totalList}</span>
        </H3>
    </c:if>

    <c:if test="${requestScope.userList.size()>0}">
        <H3>
            <span class="pd-10">${messageSource.getMessage("recordFrom",null,locale)}</span>
            <span>${(requestScope.page-1)*numberView.getNumberView()+1} </span>
            <span class="pd-10">${messageSource.getMessage("to",null,locale)}</span>
            <span>${(requestScope.page-1)*numberView.getNumberView()+requestScope.userList.size()}</span>
            <span class="pd-10">
                    ${messageSource.getMessage("in",null,locale)}
            </span>
            <span>${requestScope.totalPost}<span>${requestScope.totalList}</span>
        </H3>
    </c:if>
</div>


<script src="<s:url value="/public/asserts/js/search.js"/> "></script>