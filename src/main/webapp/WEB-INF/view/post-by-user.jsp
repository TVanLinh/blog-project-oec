<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/headers/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<!-- Page Content -->
<div class="container">
    <%--<div ><i class="fa fa-hand-o-up"></i> </div>--%>
    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-xs-12">
            <h3>
                <s:message code="postOf"/> <span class="color-main2">${param.username}</span>
            </h3>

            <jsp:include page="templates/components/content2.jsp"/>

            <!-- Pager -->
            <jsp:include page="templates/paginations/pagi_2.jsp">
                <jsp:param name="paramTarget" value="/list-post-by-user"/>
                <jsp:param name="query" value="username=${requestScope.userName}"/>
            </jsp:include>

        </div>
    </div>
    <!-- /.row -->

    <hr>
</div>



<script src="<s:url value="public/asserts/js/search.js"/>">

</script>

<jsp:include page="templates/footers/footer.jsp"/>
