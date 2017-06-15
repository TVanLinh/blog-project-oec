<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<jsp:include page="templates/headers/head.jsp"/>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-xs-12 col-sm-6 col-sm-push-3">
            <c:if test="${postList.size()==0}">
                <h1 class="text-center">${messageSource.getMessage("notpost",null,locale)}</h1>
            </c:if>
           <div class="text-center">
               <jsp:include page="templates/forms/search3.jsp">
                   <jsp:param name="searchBy" value="search.by.title"/>
                   <jsp:param name="action" value="/user-post-search"/>
               </jsp:include>
           </div>
        </div>
        <div class="clearfix"></div>

        <div class="col-xs-12">
            <c:if  test="${requestScope.error != null}">
                <span class="error"><s:message code="${requestScope.error}"/></span>
            </c:if>

            <c:if test="${param.query_search == null}">
                <jsp:include page="templates/forms/select.jsp">
                    <jsp:param name="target" value="/user"/>
                    <jsp:param name="search" value="''"/>
                </jsp:include>
            </c:if>

            <c:if test="${param.query_search != null}">
                <jsp:include page="templates/forms/select.jsp">
                    <jsp:param name="target" value="/user-post-search"/>
                    <jsp:param name="search" value="'${param.query_search}'"/>
                </jsp:include>
            </c:if>

            <jsp:include page="templates/tables/table_post_by_user.jsp"/>
        </div>
    </div>

    <hr>

    <jsp:include page="templates/footers/footer.jsp"/>


</div>
<script src="<s:url value="public/asserts/js/search.js"/>"></script>