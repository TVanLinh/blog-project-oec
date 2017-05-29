<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${postList.size()>0}">
    <table class="responstable" id="">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><a href="/admin?orderBy=id_user"><img src="<s:url value="public/asserts/images/sort.png" />"> <span class="dp-inline">${messageSource.getMessage("td.author",null,locale)}</span></a></th>
                <th class="text-center"><a href="/admin?orderBy=title"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.title",null,locale)}</a></th>
                <th><a href="/admin?orderBy=time_post"><img src="<s:url value="public/asserts/images/sort.png" />" class="mgr-5">${messageSource.getMessage("td.timePost",null,locale)}</a></th>
                <th>${messageSource.getMessage("td.action",null,locale)}</th>
            </tr>
        </thead>
        <tbody id="table-post-approve">
        <c:forEach var="post"   items="${postList}"  varStatus="loop">
            <tr >
                <td>${loop.index+1}</td>
                <td>${post.user.userName}</td>
                <td><a href="/post?id=${post.id}">${post.title}</a></td>
                <td>${post.timePost}</td>
                <td>
                    <%--<a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post-approve?action=approve&id='+${post.id},null)"> <span class="glyphicon glyphicon-ok mgr-10"></span></a>--%>
                    <%--<a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post-approve?action=delete&id='+${post.id},null)"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>--%>
                    <a href="/admin?page=${requestScope.page}&action=approve&id=${post.id}" > <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                    <a href="/admin?page=${requestScope.page}&action=delete&id=${post.id}" onclick="return window.confirm('Are you sure you want to delete this post?')"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                    <a href="/update?action=update&id=${post.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>

        <ul class="pager">
            <jsp:useBean id="numberView" class="utils.NumberViewSort"/>
            <c:if test="${requestScope.querySearch==null}">
                <c:if test="${requestScope.page>=2}">
                    <li class="previous">
                        <a href="/admin?page=${requestScope.page-1}">&larr; ${messageSource.getMessage("back",null,locale)}</a>
                    </li>
                </c:if>

                <c:if test="${requestScope.page<=requestScope.totalPost/numberView.getNumberView()}">
                    <li class="next">
                        <c:if test="${postList.size()!=0}">
                            <a href="/admin?page=${requestScope.page+1}">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                        </c:if>
                    </li>
                </c:if>
            </c:if>

            <c:if test="${requestScope.querySearch!=null}">
                <c:if test="${requestScope.page>=2}">
                    <li class="previous">
                        <a href="/admin-search-post-approve?page=${requestScope.page-1}&query_search=${requestScope.querySearch}">&larr; ${messageSource.getMessage("back",null,locale)}</a>
                    </li>
                </c:if>

                <c:if test="${requestScope.page<=requestScope.totalPost/numberView.getNumberView()}">
                    <li class="next">
                        <c:if test="${postList.size()!=0}">
                            <a href="/admin-search-post-approve?page=${requestScope.page+1}&query_search=${requestScope.querySearch}">${messageSource.getMessage("next",null,locale)} &rarr;</a>
                        </c:if>
                    </li>
                </c:if>
            </c:if>
        </ul>
    </div>

</c:if>
<script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/Sortable-HTML-Tables-jQuery-sortable-js/sortable.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>