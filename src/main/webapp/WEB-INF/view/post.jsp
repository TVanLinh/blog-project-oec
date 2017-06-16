<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
    <!-- Navigation -->
   <jsp:include page="templates/navbars/navbar.jsp"/>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">

               <jsp:include page="templates/components/content-view-post-page.jsp"/>

                <%--<hr>--%>
                <div id="comment">
                   <%--<jsp:include page="templates/components/comment.jsp"/>--%>
               </div>

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div>
                <jsp:include page="templates/slidebar/slidebar-post.jsp">
                    <jsp:param name="action" value="mySearch.formSearch('/view-search?title=','#search')"/>
                    <jsp:param name="urlTarget" value="/view-search"/>
                </jsp:include>
            </div>


        </div>
        <!-- /.row -->

        <hr>



    </div>
    <script src="<s:url value="/public/asserts/js/main.js"/>"></script>
    <script src="<s:url value="/public/asserts/js/main.js"/>"></script>
    <script src="<s:url value="/public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>