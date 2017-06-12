<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${postList.size()>0}">
    <table class="responstable" id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><a href="<s:url value="/admin?orderBy=id_user"/>"><img src="<s:url value="public/asserts/images/sort.png" />"> <span class="dp-inline">${messageSource.getMessage("td.author",null,locale)}</span></a></th>
                <th class="text-center"><a href="<s:url value="/admin?orderBy=title"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.title",null,locale)}</a></th>
                <th><a href="<s:url value="/admin?orderBy=time_post"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.timePost",null,locale)}</a></th>
                <th>${messageSource.getMessage("td.action",null,locale)}</th>
            </tr>
        </thead>
        <tbody id="table-post-approve">
        <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
        <c:forEach var="post"   items="${postList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${post.user.userName}</td>
                <td><a href="<s:url value="/post?id=${post.id}"/>">${post.title}</a></td>
                <td> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</td>
                <td>
                    <a href="<s:url value="/admin-approve-post?page=${requestScope.page}&id=${post.id}"/>" title="${messageSource.getMessage("approve",null,locale)} ">  <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                    <a href="<s:url value="/admin-delete-post?page=${requestScope.page}&id=${post.id}"/>" title="${messageSource.getMessage("delete",null,locale)} " onclick="return window.confirm('${messageSource.getMessage("confirm.delete.post",null,locale)}')"><i class="fa fa-trash-o"></i></a>
                    <a href="<s:url value="/update?action=update&id=${post.id}"/>" title="${messageSource.getMessage("edit",null,locale)}"><i class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i></a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <jsp:include page="../paginations/pagi_3.jsp">
            <jsp:param name="page" value="/admin"/>
            <jsp:param name="pageSearch" value="/admin-search-post-approve"/>
        </jsp:include>
    </div>

</c:if>
<%--<li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>--%>
<%--<script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>--%>
<%--<script src="<s:url value="public/Sortable-HTML-Tables-jQuery-sortable-js/sortable.js"/>" type="text/javascript"></script>--%>
<%--<script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>--%>