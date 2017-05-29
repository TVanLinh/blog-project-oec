<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${userList.size()>0}">
    <table class="responstable" >

        <tr>
            <th data-th="Driver details"><span>STT</span></th>
            <th data-th="Driver details"><span>${messageSource.getMessage("name",null,locale)}</span></th>
            <th class="text-center">${messageSource.getMessage("passWord",null,locale)}</th>
            <th>${messageSource.getMessage("role",null,locale)}</th>
            <th>${messageSource.getMessage("td.action",null,locale)}</th>
        </tr>
        <tbody id="table-all-user">
        <jsp:useBean id="roleService" scope="page" class="service.RoleService"/>
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
        <%--<c:if test="${requestScope.totalPost/10>0}">--%>
            <%--<ul class="pagination">--%>
                <%--<c:forEach var="i"  begin="0" end="${requestScope.totalPost/10}">--%>
                    <%--<c:if test="${i==0}">--%>
                        <%--<li class="active"><a  href="javascript:void(0)" onclick="A.getPostImprove('/manager-get-all-post',${i})">${i}</a></li>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${i!=0}">--%>
                        <%--<li><a href="javascript:void(0)" onclick="A.getPostImprove('/manager-get-all-post',${i})">${i}</a></li>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</c:if>--%>
    </div>
    <script>
        $(document).ready(function () {
            $("li").on("click",function () {
                $("li").removeClass('active');
                $(this).addClass("active");
            });
        });
    </script>
</c:if>
