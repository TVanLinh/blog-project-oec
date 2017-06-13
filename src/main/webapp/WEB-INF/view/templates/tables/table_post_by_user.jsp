<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="request"/>
</c:if>
<c:if test="${postList.size()>0}">
    <table class="responstable  " id="">
        <thead>
        <tr>
            <th data-th="Driver details"><span>STT</span></th>
            <th class="text-center"><a href="<s:url value="/user?orderBy=title"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.title",null,locale)}</a></th>
            <th><a href="<s:url value="/user?orderBy=time_post"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.timePost",null,locale)}</a></th>
            <th><a href="<s:url value="/user?orderBy=status"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.status",null,locale)}</a></th>
            <th><a href="<s:url value="/user?orderBy=approve"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.approve",null,locale)}</a></th>
            <th><a href="<s:url value="/user?orderBy=number_like"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("nLike",null,locale)}</a></th>
            <th><a href="<s:url value="/user?orderBy=number_view"/>"><img src="<s:url value="public/asserts/images/sort.png" />">${messageSource.getMessage("nView",null,locale)}</a></th>
            <th>${messageSource.getMessage("td.action",null,locale)}</th>
        </tr>
        </thead>
        <tbody id="table-all-post">
        <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
        <c:forEach var="post"   items="${postList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td><a href="<s:url value="/post?id=${post.id}"/>">${post.title}</a></td>
                <td>${dateUtil.format(post.timePost,sessionScope.dateFormat)}</td>
                <td>
                    <c:if test="${post.status==1}">
                        public
                    </c:if>
                    <c:if test="${post.status!=1}">
                        private
                    </c:if>
                </td>
                <td>
                    <c:if test="${post.approve==1}">
                        Yes
                    </c:if>
                    <c:if test="${post.approve!=1}">
                        No
                    </c:if>
                </td>
                <td>${post.numberLike}</td>
                <td>${post.numberView}</td>
                <td>
                   <a href="<s:url value="/user-delete-post?page=${paramPage}&id=${post.id}"/>" title="${messageSource.getMessage("delete",null,locale)}" onclick="return window.confirm('${messageSource.getMessage("confirm.delete.post",null,locale)}')"> <i class="fa fa-trash-o"></i></a>
                    <a href="<s:url value="/update?action=update&id=${post.id}"/>" title="${messageSource.getMessage("edit",null,locale)}"><i class="fa fa-pencil-square-o mgl-15"></i></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <jsp:include page="../paginations/pagi_3.jsp">
            <jsp:param name="pageTarget" value="/user"/>
        </jsp:include>
    </div>

</c:if>
<script src="<s:url value="public/asserts/js/main.js"/>" type="text/javascript"></script>