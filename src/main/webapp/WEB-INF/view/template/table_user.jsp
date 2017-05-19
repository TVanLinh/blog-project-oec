<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userList.size()>0}">
    <table class="display"  id="showTable">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><span>${messageSource.getMessage("name",null,locale)}</span></th>
                <th class="text-center">${messageSource.getMessage("passWord",null,locale)}</th>
                <th>${messageSource.getMessage("role",null,locale)}</th>
                <th>${messageSource.getMessage("td.action",null,locale)}</th>
            </tr>
        </thead>
        <tbody id="table-all-user">
        <jsp:useBean id="roleService" scope="page" class="Service.RoleService"/>
        <c:forEach var="user"   items="${userList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${user.userName}</td>
                <td>${user.passWord}</td>
                <td>${roleService.getStringFromListRole(user.roleList)}</td>
                <td>
                    <a href="/manager-user?action=delete&id=${user.id}" onclick="return window.confirm('Are you sure you want to delete this post?')"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                    <a href="/update-user?id=${user.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="public/Sortable-HTML-Tables-jQuery-sortable-js/sortable.js"/>" type="text/javascript"></script>
        <script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>

    </div>
</c:if>
