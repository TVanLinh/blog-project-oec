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
    <%--<div ><i class="fa fa-hand-o-up"></i> </div>--%>
    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <c:forEach var="post" items="${requestScope.postList}">
                <!-- First Blog Post -->
                <h2><a href="/post?id=${post.id}" target="_blank">${post.title} <button>${post.id}</button></a></h2>

                <span class="lead">
                        <span class="fs-15">By</span> <a href="index.php" class="fs-15">${post.user.userName}</a>
                    </span>
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-5" ><span class="margin-left-5" >Posted on</span>  ${post.timePost}</p>
                <hr>
                <c:if test="${post.image.link!=null}">
                    <img src="${post.image.link}">
                </c:if>
                <c:if test="${post.image.link==null}">
                    <img class="img-responsive" src="http://placehold.it/900x300" alt="">
                </c:if>
                <%--<hr>--%>

                <p>${post.content.trim().substring(0,50)}</p>
                <a class="btn btn-primary" href="/post?id=${post.id}" target="_blank"> Read More <span class="glyphicon glyphicon-chevron-right"></span></a>

                <hr>
            </c:forEach>
            <!-- Pager -->
            <ul class="pager">
                <li class="previous">
                    <a href="/user?page=${requestScope.page-1}">&larr; Back</a>
                </li>
                <li class="next">
                    <c:if test="${postList.size()!=0}">
                        <a href="/user?page=${requestScope.page+1}">Next &rarr;</a>
                    </c:if>
                </li>
            </ul>
        </div>
        <!-- Blog Sidebar Widgets Column -->
        <div>
            <jsp:include page="template/slidebar.jsp"/>
        </div>

    </div>
    <!-- /.row -->

    <hr>

    <jsp:include page="template/footer.jsp"/>


</div>
    <!-- /.row -->
</div>
</body>

</html>
