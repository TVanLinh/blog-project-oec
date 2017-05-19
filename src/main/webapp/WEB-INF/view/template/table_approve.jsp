<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${postList.size()>0}">
    <table class="display" id="showTable">
        <thead>
            <tr>
                <th data-th="Driver details"><span>STT</span></th>
                <th data-th="Driver details"><span>${messageSource.getMessage("td.author",null,locale)}</span></th>
                <th class="text-center">${messageSource.getMessage("td.title",null,locale)}</th>
                <th>${messageSource.getMessage("td.timePost",null,locale)}</th>
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
                    <a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post-approve?action=approve&id='+${post.id},null)"> <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                    <a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post-approve?action=delete&id='+${post.id},null)"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                    <a href="/update?action=update&id=${post.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div>
        <%--<c:if test="${requestScope.totalPost/10>1}">--%>
            <%--<ul class="pagination">--%>
                <%--<c:forEach var="i"  begin="1" end="${requestScope.totalPost/10}">--%>
                    <%--<c:if test="${i==0}">--%>
                        <%--<li class="active"><a  href="javascript:void(0)" onclick="A.getPostImprove('/approve-post',${i})">${i}</a></li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${i!=0}">--%>
                        <%--<li><a href="javascript:void(0)" onclick="A.getPostImprove('/approve-post',${i})">${i}</a></li>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</c:if>--%>
    </div>
</c:if>
<script src="<s:url value="public/data-table-plugin/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/Sortable-HTML-Tables-jQuery-sortable-js/sortable.js"/>" type="text/javascript"></script>
<script src="<s:url value="public/asserts/js/sort.js"/>" type="text/javascript"></script>