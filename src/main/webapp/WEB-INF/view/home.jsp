<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
    <!-- Navigation -->
   <jsp:include page="templates/navbars/navbar.jsp"/>
    <!-- Page Content -->
    <div class="container">
        <%--<div ><i class="fa fa-hand-o-up"></i> </div>--%>
        <div class="row">
            <!-- Blog Entries Column -->
                <div class="col-xs-12 col-md-8">

                    <%--<div class="">--%>
                        <%--<jsp:include page="templates/forms/search2.jsp"   >--%>
                            <%--<jsp:param name="action" value="mySearch.formSearch('/view-search?title=','#search')"/>--%>
                            <%--<jsp:param name="urlTarget" value="/view-search"/>--%>
                        <%--</jsp:include>--%>
                    <%--</div>--%>
                    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
                     <%--<c:forEach var="post" items="${postList}">--%>
                <%--<!-- First Blog Post -->--%>
                        <%--<h2><a href="<s:url value="/post?id=${post.id}"/>" target="_self">${post.title} </a></h2> <!--button>${post.id}</button-->--%>

                        <%--<span class="lead">--%>
                            <%--<span class="fs-15">${messageSource.getMessage("by",null,locale)}</span> <a href="<s:url value="/list-post-by-user?username=${post.user.userName}"/>" class="fs-15">${post.user.userName}</a>--%>
                        <%--</span>--%>
                        <%--<p><span>${messageSource.getMessage("postTime",null,locale)}</span>--%>
                                <%--${dateUtil.format(post.timePost,sessionScope.dateFormat)}--%>
                        <%--</p>--%>
                        <%--<hr>--%>
                            <%--<c:if test="${post.image.link!=null}">--%>
                                <%--<img class="img-responsive pdb-15" src="${post.image.link}">--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${post.image.link==null}">--%>
                                <%--<img class="img-responsive pdb-15" src="http://placehold.it/900x300" alt="">--%>
                            <%--</c:if>--%>
                        <%--&lt;%&ndash;<hr>&ndash;%&gt;--%>
                         <%--${pageContext.setAttribute("str","\\<.*?>")}--%>
                        <%--<c:if test="${post.content.replaceAll(str,'').length()>500}">--%>
                            <%--<p>${post.content.replaceAll(str,"").substring(0,500)}...</p>--%>
                        <%--</c:if>--%>
                         <%--<c:if test="${post.content.replaceAll(str,'').length()<500}">--%>
                             <%--<p>${post.content.replaceAll(str,"")}...</p>--%>
                         <%--</c:if>--%>
                        <%--&lt;%&ndash;<p>${ Jsoup.parse(post.content).text()}</p>&ndash;%&gt;--%>
                        <%--<a class="btn btn-primary" href="<s:url value="/post?id=${post.id}"/>" target="_self" > ${messageSource.getMessage("read",null,locale)} <span class="glyphicon glyphicon-chevron-right"></span></a>--%>
                         <%--<c:if test="${sessionScope.username!=null && requestScope.userDAO.find(post.user.id).userName==sessionScope.username}">--%>
                                 <%--<a id="action-update" href="<s:url value="/update?action=update&id=${post.id}"/>" title="${messageSource.getMessage("edit",null,locale)}"><img src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>--%>
                                 <%--<a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="<s:url value="/delete-post?id=${post.id}" />" title="${messageSource.getMessage("delete",null,locale)}"><span class="glyphicon glyphicon-remove mgl-10"></span></a>--%>
                         <%--</c:if>--%>
                        <%--<hr>--%>
                    <%--</c:forEach>--%>



                 <!--------------content-->
                 <jsp:include page="templates/components/content.jsp"/>

                 <!------end content------>

                    <!-- Pager -->
                    <ul class="pager">
                        <c:if test="${requestScope.page>=2}">
                            <li class="previous">
                                <a href="<s:url value="/home?page=${requestScope.page-1}"/>">&larr; ${messageSource.getMessage("back",null,locale)}</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope.totalList/requestScope.limit>=requestScope.page}">
                            <li class="next">
                                <c:if test="${postList.size()!=0}">
                                    <a href="<s:url value="/home?page=${requestScope.page+1}"/>">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                                </c:if>
                            </li>
                        </c:if>
                    </ul>
                </div>
            <!-- Blog Sidebar Widgets Column -->
            <jsp:include page="templates/slidebar/slidebar.jsp">
                <jsp:param name="action" value="mySearch.formSearch('/view-search?title=','#search')"/>
                <jsp:param name="urlTarget" value="/view-search"/>
            </jsp:include>

        </div>
        <!-- /.row -->

        <hr>
    </div>
    <script src="<s:url value="public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>