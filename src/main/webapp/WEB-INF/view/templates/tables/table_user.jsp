<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="request"/>
</c:if>


<c:if test="${ param.orderBy != null}">
    <c:set var="vOrderBy" value="${param.orderBy}"/>
</c:if>

<c:if test="${ param.orderBy == null}">
    <c:set var="vOrderBy" value="user_name"/>
</c:if>

<c:if test="${list.size()>0}">
    <table class="responstable"  id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details">
                    <a href="<s:url value="/manager-user?orderBy=user_name"/>">
                        <c:if test="${!vOrderBy.equals('user_name')}">
                            <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('desc')}">
                            <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('asc')}">
                            <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                        </c:if>
                        <span class="dp-inline"><s:message code="name"/></span>
                    </a>
                </th>
                <th>
                    <a href="<s:url value="/manager-user?orderBy=role"/>">
                        <c:if test="${!vOrderBy.equals('role')}">
                            <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('role') && requestScope.typeOrder.equals('asc')}">
                            <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('role') && requestScope.typeOrder.equals('desc')}">
                            <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                        </c:if>
                        <span class="dp-inline">  <s:message code="role"/></span>
                    </a></th>
                <th><s:message code="td.action"/></th>
            </tr>
        </thead>
        <tbody id="table-all-user">
        <jsp:useBean id="roleService" scope="page" class="service.RoleService"/>
        <c:forEach var="user" items="${list}" varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${user.userName}</td>
                <td>${roleService.getStringFromListRole(user.roleList)}</td>
                <td>
                    <a href="<s:url value="/delete-user?page=${paramPage}&id=${user.id}"/>"
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
