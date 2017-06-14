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
        <c:if test="${requestScope.totalList<=0}">
            <H3>
                    ${messageSource.getMessage("noRecord",null,locale)}
            </H3>
        </c:if>
            <c:if test="${requestScope.totalList>0}">
                    <p class="fs-20">
                        <span>${messageSource.getMessage("resultFind",null,locale)} </span>
                        <span>${requestScope.totalList}</span>
                        <span class="pd-10">${messageSource.getMessage("recordFrom",null,locale)}</span>
                        <span>${(paramPage-1)*requestScope.limit+1} </span>
                        <span class="pd-10">${messageSource.getMessage("to",null,locale)}</span>
                        <span>${(paramPage-1)*requestScope.limit+requestScope.postList.size()}</span>
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
