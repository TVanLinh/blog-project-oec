<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
       <H3 class="color-red bgc-color-gray fs-25  pd-15 mgb-25" >${messageSource.getMessage("postnew",null,locale)}</H3>
    <ul class="media-list main-list" id="content-slidebar">
        <c:forEach var="post" items="${postSlideBar}">
            <li class="media" >
                <c:if test="${post.image.link!=null}">
                    <a class="pull-left pdb-15" href="/post?id=${post.id}">
                        <img class="media-object img-slidebar" src="${post.image.link}" alt="${post.image.alt}">
                    </a>
                </c:if>

                <div class="media-body">
                    <h4 class="media-heading">${post.title}</h4>
                    <p class="by-user color-main2">By ${post.user.userName}</p>
                    <!-- Pager -->
                    <a href="/post?id=${post.id}">${messageSource.getMessage("read",null,locale)}</a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>