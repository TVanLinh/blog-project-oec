<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include page="templates/headers/head.jsp"/>
<!-- Navigation -->
    <jsp:include page="templates/navbars/navbar.jsp"/>

<div class="container">
        <!-- Blog Entries Column -->
        <div class="row">
                <div class="col-md-12">
                   <jsp:include page="templates/menus/menu-admin.jsp"/>
                    <!---------------list table -------------------------->
                    <c:if test="${requestScope.querySearch==null}">
                        <c:if test="${postList.size()==0}">
                            <h1 class="text-center">${messageSource.getMessage("table.zeroApprove",null,locale)}</h1>
                        </c:if>
                    </c:if>
                    <c:if test="${postList.size()!=0}">
                        <h1 class="text-center">${messageSource.getMessage("table.Aprrove",null,locale)}</h1>
                    </c:if>

                </div>
            <!-------form search-------------->
            <div class="col-xs-12 col-sm-6 col-sm-push-3">
                <div class="text-center">
                    <jsp:include page="templates/forms/search3.jsp">
                        <jsp:param name="searchBy" value="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('title',null,locale)}"/>
                        <jsp:param name="action" value="/admin-search-post-approve"/>
                    </jsp:include>
                </div>
            </div>
            <div class="clearfix"></div>

            <div class="col-xs-12">
                <jsp:include page="templates/tables/table_approve.jsp"/>
            </div>

        </div>
    <!-- /.row -->
     <hr>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>