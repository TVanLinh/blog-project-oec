<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
   <div class="hidden-md hidden-xs hidden-sm">
       <jsp:include page="search.jsp"   />
   </div>
    <ul class="media-list main-list" id="content-slidebar">
        <c:forEach var="post" items="${postSlideBar}">
            <li class="media" >
                <a class="pull-left pdb-15" href="/post?id=${post.id}">
                    <img class="media-object" src="${post.image.link}" alt="${post.image.alt}">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">${post.title}</h4>
                    <p class="by-user color-main2">By ${post.user.userName}</p>
                    <!-- Pager -->
                    <a href="/post?id=${post.id}">${messageSource.getMessage("read",null,locale)}</a>
                </div>
            </li>
        </c:forEach>
    </ul>
    <%--<ul class="pager">--%>
        <%--<li class="previous">--%>
            <%--<a id="previousPost" href="javascript:void(0)"  class="hide" >&larr; Back</a>--%>
        <%--</li>--%>
        <%--<li class="next">--%>
            <%--<c:if test="${postList.size()!=0}">--%>
                <%--<a id="nextPost" href="javascript:void(0)" onclick="A.getPost('get-post-page-post',1)">Next &rarr;</a>--%>
            <%--</c:if>--%>
        <%--</li>--%>
    <%--</ul>--%>
</div>