<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12 ">

            <jsp:include page="templates/menus/menu-admin.jsp"/>

           <c:if  test="${requestScope.errorInsertUser!=null}">
               <span class="error">${requestScope.errorInsertUser}</span>
           </c:if>
            <%--<jsp:include page="templates/table-list-user.jsp"/> --%>
            <!----------------end list-table ------------------------->
        </div>

        <div class="col-xs-12 col-sm-6 col-sm-push-3 ">
            <jsp:include page="templates/forms/search3.jsp">
                <jsp:param name="searchBy" value="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('username',null,locale)}"/>
                <jsp:param name="action" value="/manager-user-search"/>
            </jsp:include>
        </div>

        <div class="clearfix"></div>
        <div class="col-xs-12">
            <a class="btn btn-default mgb-15" href="<s:url value="/insert-user"/>"><img src="<s:url value="public/asserts/images/add_user.png"/> " class="mgr-10">${messageSource.getMessage("insertUser",null,locale)}</a>
            <jsp:include page="templates/tables/table_user.jsp"/>

        </div>
    </div>
    <!-- /.row -->
    <hr>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>

<jsp:include page="templates/footers/footer.jsp"/>