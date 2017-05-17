<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>
<body>

    <!-- Navigation -->
   <jsp:include page="template/navbar.jsp"/>

    <!-- Page Content -->
    <div class="container">
        <%--<div ><i class="fa fa-hand-o-up"></i> </div>--%>
        <div class="row">
            <!-- Blog Entries Column -->
                <div class="col-md-8">
                     <c:forEach var="post" items="${postList}">
                <!-- First Blog Post -->
                    <h2><a href="/post?id=${post.id}" target="_self">${post.title} </a></h2> <!--button>${post.id}</button-->

                    <span class="lead">
                        <span class="fs-15">${messageSource.getMessage("by",null,locale)}</span> <a href="#" class="fs-15">${post.user.userName}</a>
                    </span>
                         <jsp:useBean id="dateUtil" class="Utils.DateFormatUtil" scope="session"/>
                    <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-3">${messageSource.getMessage("postTime",null,locale)}</span> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</p>
                    <hr>
                        <c:if test="${post.image.link!=null}">
                            <img class="img-responsive pdb-15" src="${post.image.link}">
                        </c:if>
                        <c:if test="${post.image.link==null}">
                            <img class="img-responsive pdb-15" src="http://placehold.it/900x300" alt="">
                        </c:if>
                    <%--<hr>--%>

                    <p>${post.content.replaceAll("\\<.*?>","").substring(0,50)}...</p>
                    <%--<p>${ Jsoup.parse(post.content).text()}</p>--%>
                    <a class="btn btn-primary" href="/post?id=${post.id}" target="_self"> ${messageSource.getMessage("read",null,locale)} <span class="glyphicon glyphicon-chevron-right"></span></a>
                         <c:if test="${sessionScope.username!=null}">
                             <a id="action-update" href="/update?action=update&id=${post.id}"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="/delete-post?id=${post.id}">${messageSource.getMessage("delete",null,locale)}</a>
                         </c:if>
                    <hr>
                    </c:forEach>
                    <!-- Pager -->
                    <ul class="pager">
                        <li class="previous">
                            <a href="/home?page=${requestScope.page-1}">&larr; ${messageSource.getMessage("back",null,locale)}</a>
                        </li>
                        <li class="next">
                            <c:if test="${postList.size()!=0}">
                                <a href="/home?page=${requestScope.page+1}">${messageSource.getMessage("next",null,locale)} &rarr;</a>
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




</body>

</html>
