<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<jsp:include page="templates/headers/head.jsp"/>
<style>
    .menu-item:nth-child(1)
    {
        color: #ffff5d;
    }

</style>
<!-- Navigation -->
    <jsp:include page="templates/navbars/navbar.jsp"/>

<div class="container-fluid">
        <!-- Blog Entries Column -->
        <div class="row">
                <div class="col-md-2 col-xs-12">
                    <jsp:include page="templates/menus/menu-admin.jsp"/>
                </div>
                <div class="col-md-10 col-xs-12">

                    <!---------------list table -------------------------->

                    <c:if test="${list.size()==0 && param.query_search == null}">
                        <h1 class="text-center"><s:message code="notpost.approve"/></h1>
                    </c:if>
                    <c:if test="${list.size()!=0 }">
                        <h1 class="text-center"><s:message code="table.Aprrove"/></h1>
                    </c:if>

                    <c:if test="${list.size()==0 && param.query_search != null}">
                        <h1 class="text-center"><s:message code="not.post.find"/></h1>
                    </c:if>

                    <div class="text-center">
                        <jsp:include page="templates/forms/search3.jsp">
                            <jsp:param name="searchBy" value="search.by.title.author"/>
                            <jsp:param name="action" value="/admin-search-post-approve"/>
                        </jsp:include>
                    </div>

                    <c:if  test="${requestScope.error != null}">
                        <span class="error"><s:message code="${requestScope.error}"/></span>
                    </c:if>

                    <c:if test="${list.size() > 0}">
                        <c:if test="${param.query_search == null}">
                            <jsp:include page="templates/forms/select.jsp">
                                <jsp:param name="target" value="/admin"/>
                                <jsp:param name="search" value="''"/>
                            </jsp:include>
                        </c:if>

                        <c:if test="${param.query_search != null}">
                            <jsp:include page="templates/forms/select.jsp">
                                <jsp:param name="target" value="/admin-search-post-approve"/>
                                <jsp:param name="search" value="'${param.query_search}'"/>
                            </jsp:include>
                        </c:if>
                    </c:if>

                    <jsp:include page="templates/tables/table_approve.jsp"/>

                </div>
        </div>
    <!-- /.row -->
     <hr>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>