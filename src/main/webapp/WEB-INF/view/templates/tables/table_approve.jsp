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
    <table class="responstable" id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details">
                    <a href="<s:url value="/admin?orderBy=user_name"/>">

                        <c:if test="${!vOrderBy.equals('user_name')}">
                            <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('desc')}">
                            <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('asc')}">
                            <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                        </c:if>
                        <span class="dp-inline"><s:message code="td.author"/></span>
                    </a>
                </th>
                <th class="text-center">
                    <a href="<s:url value="/admin?orderBy=title"/>">
                        <c:if test="${!vOrderBy.equals('title')}">
                            <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('title') && requestScope.typeOrder.equals('desc')}">
                            <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('title') && requestScope.typeOrder.equals('asc')}">
                            <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                        </c:if>
                        <s:message code="td.title"/>
                    </a>
                </th>
                <th>
                    <a href="<s:url value="/admin?orderBy=time_post"/>">
                        <c:if test="${!vOrderBy.equals('time_post')}">
                            <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('time_post') && requestScope.typeOrder.equals('desc')}">
                            <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                        </c:if>

                        <c:if test="${vOrderBy.equals('time_post') && requestScope.typeOrder.equals('asc')}">
                            <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                        </c:if>
                        <s:message code="td.timePost"/>
                    </a></th>
                <th><s:message code="td.action"/></th>
            </tr>
        </thead>
        <tbody id="table-post-approve">
        <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
        <c:forEach var="post" items="${list}" varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${post.user.userName}</td>
                <td><a href="<s:url value="/post?id=${post.id}"/>">${post.title}</a></td>
                <td> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</td>
                <td>
                    <a href="<s:url value="/admin-approve-post?page=${paramPage}&id=${post.id}"/>"
                       title="<s:message code="approve" />"> <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                    <a href="<s:url value="/admin-delete-post?page=${paramPage}&id=${post.id}"/>"
                       title="<s:message code="delete" /> "
                       onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"><i
                            class="fa fa-trash-o"></i></a>
                    <a href="<s:url value="/update?id=${post.id}"/>" title="<s:message code="edit" />"><i
                            class="fa fa-pencil-square-o mgl-15" aria-hidden="true"></i></a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <jsp:include page="../paginations/pagi_3.jsp">
            <jsp:param name="pageTarget" value="/admin"/>
            <jsp:param name="pageSearch" value="/admin-search-post-approve"/>
        </jsp:include>
    </div>

</c:if>