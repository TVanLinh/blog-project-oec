<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Blog Search class="well" Well -->
<div >
    <%--<h4>Blog Search</h4>--%>
        <form method="get" action="${param.action}" onsubmit="return checkFormValid()">
            <div class="input-group">
                <input type="text" class="form-control"  id="search" name="query_search" onsubmit="return checkFormValid()" value="${requestScope.querySearch}">
                <span class="input-group-btn">
                <button class="btn btn-default" type="submit"  onclick="return checkFormValid()" onsubmit="return checkFormValid()" >
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


<script src="<s:url value="public/asserts/js/search.js"/> "></script>