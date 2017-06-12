<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${postList.size()>0}">
    <table class="responstable  " id="">

      <thead>
          <tr>
              <th data-th="Driver details"><span>STT</span></th>
              <th data-th="Driver details"><a href="<s:url value="/manager-post?orderBy=id_user"/>"><img src="<s:url value="public/asserts/images/sort.png" />"><span class="dp-inline">${messageSource.getMessage("td.author",null,locale)}</span></a></th>
              <th class="text-center"><a href="<s:url value="/manager-post?orderBy=title"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.title",null,locale)}</a></th>
              <th><a href="<s:url value="/manager-post?orderBy=time_post"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.timePost",null,locale)}</a></th>
              <th><a href="<s:url value="/manager-post?orderBy=status"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.status",null,locale)}</a></th>
              <th><a href="<s:url value="/manager-post?orderBy=approve"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.approve",null,locale)}</a></th>
              <th><a href="<s:url value="/manager-post?orderBy=number_like"/>"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("nLike",null,locale)}</a></th>
              <th><a href="<s:url value="/manager-post?orderBy=number_view"/>"><img src="<s:url value="public/asserts/images/sort.png" />">${messageSource.getMessage("nView",null,locale)}</a></th>
              <th>${messageSource.getMessage("td.action",null,locale)}</th>
          </tr>
      </thead>
        <tbody id="table-all-post">
        <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
        <c:forEach var="post"   items="${postList}"  varStatus="loop">
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
                    <%--<a href="javascript:void(0)" onclick="A.getPostImprove('<s:urlmanager-get-all-post-delete?id='+${post.id},null)"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>--%>
                    <%--<a href="/update?action=update&id=${post.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a> --%>
                    <a href="<s:url value="/manager-post-delete?page=${requestScope.page}&id=${post.id}"/>" title="${messageSource.getMessage("delete",null,locale)}" onclick="return window.confirm('${messageSource.getMessage("confirm.delete.post",null,locale)}')"> <i class="fa fa-trash-o"></i></a>
                        <a href="<s:url value="/update?action=update&id=${post.id}"/>" title="${messageSource.getMessage("edit",null,locale)}"><i class="fa fa-pencil-square-o mgl-15"></i></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <div>
            <jsp:include page="../paginations/pagi_3.jsp">
                <jsp:param name="page" value="/manager-post"/>
                <jsp:param name="pageSearch" value="/manager-post-search"/>
            </jsp:include>
    </div>

</c:if>

<script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/asserts/js/main.js"/>" type="text/javascript"></script>