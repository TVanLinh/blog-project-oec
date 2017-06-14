<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${postList.size()>0}">
    <table class="responstable"  id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><a href="<s:url value="/manager-user?orderBy=user_name"/>"><img
                        src="<s:url value="public/asserts/images/sort.png" />"> <span class="dp-inline"><s:message
                        code="name"/></span></a></th>
                <th><a href="<s:url value="/manager-user"/>"><s:message code="role"/></a></th>
                <th><s:message code="td.action"/></th>
            </tr>
        </thead>
        <tbody id="table-all-user">
        <jsp:useBean id="roleService" scope="page" class="service.RoleService"/>
        <c:forEach var="user"   items="${postList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${user.userName}</td>
                <td>${roleService.getStringFromListRole(user.roleList)}</td>
                <td>
                    <a href="<s:url value="/delete-user?page=${requestScope.page}&id=${user.id}"/>"
                       title="<s:message code="delete" />"
                       onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"> <i
                            class="fa fa-trash-o"></i></a>
                    <a href="<s:url value="/update-user?id=${user.id}"/>" title="<s:message code="edit" />"><i
                            class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
        <jsp:include page="../paginations/pagi_3.jsp">
            <jsp:param name="pageTarget" value="/manager-user"/>
            <jsp:param name="pageSearch" value="/manager-user-search"/>
        </jsp:include>
    </div>
</c:if>
