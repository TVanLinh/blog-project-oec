<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${postList.size()==0}">
    <h1>Zero Post not Approve</h1>
</c:if>
<c:if test="${postList.size()>0}">
    <h1>List Post Have Not Approve</h1>

    <table class="responstable" >

        <tr>
            <th data-th="Driver details"><span>STT</span></th>
            <th data-th="Driver details"><span>Author</span></th>
            <th class="text-center">Title</th>
            <th>Time-Post</th>
            <th>Action</th>
        </tr>
        <tbody id="table-post-approve">
            <c:forEach var="post"   items="${postList}"  varStatus="loop">
                <tr >
                    <td>${loop.index+1}</td>
                    <td>${post.user.userName}</td>
                    <td><a href="/post?id=${post.id}">${post.title}</a></td>
                    <td>${post.timePost}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post?action=approve&id='+${post.id},null)"> <span class="glyphicon glyphicon-ok mgr-10"></span></a>
                        <a href="javascript:void(0)" onclick="A.getPostImprove('/admin-post?action=delete&id='+${post.id},null)"> <span class="glyphicon glyphicon-remove mgl-10"></span></a>
                        <a href="/update?action=update&id=${post.id}"><img class="mgt--5 mgl-10" src="<s:url value="public/asserts/images/edit.gif"/>" alt=""></a>

                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <div>
        <c:if test="${requestScope.totalPost/10>0}">
            <ul class="pagination">
                <c:forEach var="i"  begin="0" end="${requestScope.totalPost/10}">
                    <c:if test="${i==0}">
                        <li class="active"><a  href="javascript:void(0)" onclick="A.getPostImprove('/approve-post',${i})">${i}</a></li>
                    </c:if>
                    <c:if test="${i!=0}">
                        <li><a href="javascript:void(0)" onclick="A.getPostImprove('/approve-post',${i})">${i}</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </c:if>
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
