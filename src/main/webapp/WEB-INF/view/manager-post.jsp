<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="template/navbar-admin.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12">
            <ul class="list-group menu-admin">
                <li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="/manager-post"><img src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10">${messageSource.getMessage("managerPost",null,locale)}</a></li>
                <li class="list-inline btn btn-success mgr-10  mgb-15"><a href="/configuration"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>
                <li class="list-inline btn btn-warning mgl-10  mgb-15"> <a href="/manager-user"><i class="glyphicon glyphicon-user mgr-10"></i>${messageSource.getMessage("managerUser",null,locale)}</a></li>
            </ul>
            <%--<ul class="list-group">--%>
                <%--<c:if test="${requestScope.querySearch==null}">--%>
                    <%--<li class="list-group-item">${messageSource.getMessage("totalApprove",null,locale)}<span class="badge" id="numberApprove">${requestScope.totalPost}</span></li>--%>
                <%--</c:if>--%>
                <%--&lt;%&ndash;<c:if test="${requestScope.querySearch!=null}">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<li class="list-group-item">${messageSource.getMessage("resultFind",null,locale)}<span class="badge" id="numberApprove">${requestScope.totalPost}</span></li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--</ul>--%>

            <!-------form search-------------->
            <%--<jsp:include page="template/search.jsp">--%>
                <%--<jsp:param name="action" value="mySearch.postSearch('/search-all-post','#search','#table-all-post')"/>--%>
            <%--</jsp:include>--%>

            <jsp:include page="template/search3.jsp">
                 <jsp:param name="action" value="/manager-post-search"/>
            </jsp:include>
            <%--<div >--%>
                    <%--<form method="get" action="/manager-post-search">--%>
                        <%--<div class="input-group">--%>
                            <%--<input type="text" class="form-control"  id="search" name="query_search">--%>
                            <%--<span class="input-group-btn">--%>
                        <%--<button class="btn btn-default" type="submit">--%>
                            <%--<span class="glyphicon glyphicon-search"></span>--%>
                        <%--</button>--%>
                <%--</span>--%>
                        <%--</div>--%>
                    <%--</form>--%>
            <%--</div>--%>
            <jsp:include page="template/table_post_all.jsp"/>
            <!----------------end list-table ------------------------->
        </div>

    </div>
    <!-- /.row -->
    <hr>
    <jsp:include page="template/footer.jsp"/>

</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
</body>
</html>
