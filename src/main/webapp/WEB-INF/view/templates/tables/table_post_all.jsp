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
    <table class="responstable  " id="">

      <thead>
          <tr>
              <th data-th="Driver details"><span>STT</span></th>
              <th data-th="Driver details">
                  <a href="<s:url value="/manager-post?orderBy=user_name"/>">
                          <%--<i class="fa fa-sort fs-20 pd-5" aria-hidden="true">--%>
                          <%----%>
                          <%--</i>--%>
                          <%--<i class="fa fa-sort-desc" aria-hidden="true"></i>--%>
                          <%--<span class="dp-inline">--%>
                      <c:if test="${!vOrderBy.equals('user_name')}">
                          <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('desc')}">
                          <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('user_name') && requestScope.typeOrder.equals('asc')}">
                          <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                      </c:if>
                      <s:message code="td.author"/>

                  </a>
              </th>
              <th class="text-center">
                  <a href="<s:url value="/manager-post?orderBy=title"/>">
                          <%--<i class="fa fa-sort fs-20 pd-5" aria-hidden="true"></i>--%>
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
              <th><a href="<s:url value="/manager-post?orderBy=time_post"/>">
                  <c:if test="${!vOrderBy.equals('time_post')}">
                      <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                  </c:if>

                  <c:if test="${vOrderBy.equals('time_post') && requestScope.typeOrder.equals('desc')}">
                      <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                  </c:if>

                  <c:if test="${vOrderBy.equals('time_post') && requestScope.typeOrder.equals('asc')}">
                      <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                  </c:if>
                  <s:message code="td.timePost"/></a></th>
              <th>
                  <a href="<s:url value="/manager-post?orderBy=status"/>">
                      <c:if test="${!vOrderBy.equals('status')}">
                          <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('status') && requestScope.typeOrder.equals('desc')}">
                          <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('status') && requestScope.typeOrder.equals('asc')}">
                          <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                      </c:if>
                      <s:message code="td.status"/>
                  </a>
              </th>
              <th>
                  <a href="<s:url value="/manager-post?orderBy=approve"/>">
                          <%--<i class="fa fa-sort fs-20 pd-5" aria-hidden="true"></i>--%>
                      <c:if test="${!vOrderBy.equals('approve')}">
                          <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('approve') && requestScope.typeOrder.equals('desc')}">
                          <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('approve') && requestScope.typeOrder.equals('asc')}">
                          <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                      </c:if>
                      <s:message code="td.approve"/>
                  </a>
              </th>
              <th>
                  <a href="<s:url value="/manager-post?orderBy=number_like"/>">
                      <c:if test="${!vOrderBy.equals('number_like')}">
                          <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('number_like') && requestScope.typeOrder.equals('desc')}">
                          <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('number_like') && requestScope.typeOrder.equals('asc')}">
                          <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                      </c:if>
                      <s:message code="nLike"/>
                  </a>
              </th>
              <th>
                  <a href="<s:url value="/manager-post?orderBy=number_view"/>">
                      <c:if test="${!vOrderBy.equals('number_view')}">
                          <i class="fa fa-sort fs-20 pd-5 mgr-10 opacity-3" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('number_view') && requestScope.typeOrder.equals('desc')}">
                          <i class="fa fa-sort-desc   fs-20 mgr-10" aria-hidden="true"></i>
                      </c:if>

                      <c:if test="${vOrderBy.equals('number_view') && requestScope.typeOrder.equals('asc')}">
                          <i class="fa fa-sort-asc mgr-10 fs-20" aria-hidden="true"></i>
                      </c:if>
                      <s:message code="nView"/>
                  </a>
              </th>
              <th><s:message code="td.action"/></th>
          </tr>
      </thead>
        <tbody id="table-all-post">
        <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
        <c:forEach var="post" items="${list}" varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${post.user.userName}</td>
                <td><a href="<s:url value="/post?id=${post.id}"/>">${post.title}</a></td>
                <td> ${dateUtil.format(post.timePost,sessionScope.dateFormat)}</td>
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
                    <a href="<s:url value="/manager-post-delete?page=${paramPage}&id=${post.id}"/>"
                       title="<s:message code="delete" />"
                       onclick="return window.confirm('<s:message code="confirm.delete.post"/>')"> <i
                            class="fa fa-trash-o"></i></a>
                    <a href="<s:url value="/update?id=${post.id}"/>" title="<s:message code="edit" />"><i
                            class="fa fa-pencil-square-o mgl-15"></i></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
            <jsp:include page="../paginations/pagi_3.jsp">
                <jsp:param name="pageTarget" value="/manager-post"/>
                <jsp:param name="pageSearch" value="/manager-post-search"/>
            </jsp:include>
    </div>
</c:if>
<script src="<s:url value="public/asserts/js/main.js"/>" type="text/javascript"></script>