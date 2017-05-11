<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${postList.size()==0}">
    <h1>Zero Post not Approve</h1>
</c:if>
<c:if test="${postList.size()>0}">
    <h1>List Post Have Not Approve</h1>

    <table class="responstable" >

        <tr>
            <th data-th="Driver details"><span>Author</span></th>
            <th class="text-center">Title</th>
            <th>Time-Post</th>
            <th>Action</th>
        </tr>
        <tbody id="table-post-approve">
            <c:forEach var="post"   items="${postList}">
                <tr >
                    <td>${post.user.userName}</td>
                    <td>${post.title}</td>
                    <td>${post.timePost}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="A.getPostImprove('action=approve&id='+${post.id},null)"> <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                        <a href="javascript:void(0)" onclick="A.getPostImprove('action=delete&id='+${post.id},null)"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                        <a href="/update?action=update&id=${post.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>

                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <div>
        <ul class="pager">
            <li class="previous">
                <a href="/user?page=">&larr; Back</a>
            </li>
            <li class="next">
                <c:if test="${postList.size()!=0}">
                    <a href="/user?page=">Next &rarr;</a>
                </c:if>
            </li>
        </ul>
    </div>
</c:if>
