<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="request"/>
</c:if>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-xs-12">
            <p class="fs-20"><s:message code="result.find.word"/> : <span
                    class="error fs-20">${param.query_search}</span></p>
            <c:if test="${requestScope.totalList<=0}">
                <H3>
                    <s:message code="not.post.find"/>
                </H3>
            </c:if>
            <c:if test="${requestScope.totalList>0}">
                    <p class="fs-20">
                        <span class="pd-10"><s:message code="post.from"/></span>
                        <span>${(paramPage-1)*requestScope.limit+1} </span>
                        <span class="pd-10"><s:message code="to"/></span>
                        <span>${(paramPage-1)*requestScope.limit+requestScope.list.size()}</span>
                        <span class="pd-10"><s:message code="in"/></span>
                        <span>${(paramPage-1)*requestScope.limit+requestScope.list.size()}</span>
                        <span class="pd-10"><s:message code="post"/></span>
                    </p>
            </c:if>

            <jsp:include page="templates/components/content.jsp"/>
            <!-- Pager -->
            <jsp:include page="templates/paginations/pagi_1.jsp">
                <jsp:param name="pageTarget" value="/view-search"/>
            </jsp:include>

        </div>
    </div>
    <!-- /.row -->

    <hr>
</div>





<script src="<s:url value="public/asserts/js/search.js"/>">

</script>
<jsp:include page="templates/footers/footer.jsp"/>
