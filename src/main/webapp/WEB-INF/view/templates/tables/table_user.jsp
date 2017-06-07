<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userList.size()>0}">
    <table class="responstable"  id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><a href="<s:url value="/manager-user?orderBy=user_name"/>"><img src="<s:url value="public/asserts/images/sort.png" />"> <span class="dp-inline">${messageSource.getMessage("name",null,locale)}</span></a></th>
                <%--<th class="text-center"><a href="<s:url value="/manager-user?orderBy=pass_word"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("passWord",null,locale)}</a></th>--%>
                <th><a href="<s:url value="/manager-user?orderBy=role"/>"><!--img src="<s :url value="public/asserts/images/sort.png" />" class="mgr-5"-->${messageSource.getMessage("role",null,locale)}</a></th>
                <th>${messageSource.getMessage("td.action",null,locale)}</th>
            </tr>
        </thead>
        <tbody id="table-all-user">
        <jsp:useBean id="roleService" scope="page" class="service.RoleService"/>
        <c:forEach var="user"   items="${userList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${user.userName}</td>
                <%--<td>${user.passWord}</td>--%>
                <td>${roleService.getStringFromListRole(user.roleList)}</td>
                <td>
                    <a href="<s:url value="/manager-user?action=delete&id=${user.id}"/>" title="${messageSource.getMessage("delete",null,locale)}" onclick="return window.confirm('Are you sure you want to delete this post?')"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                    <a href="<s:url value="/update-user?id=${user.id}"/>" title="${messageSource.getMessage("edit",null,locale)}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <jsp:include page="../paginations/pagi_3.jsp">
            <jsp:param name="page" value="/manager-user"/>
            <jsp:param name="pageSearch" value="/manager-user-search"/>
        </jsp:include>
    </div>

    <script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
    <script src="<s:url value="public/Sortable-HTML-Tables-jQuery-sortable-js/sortable.js"/>" type="text/javascript"></script>
    <script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>

</c:if>
