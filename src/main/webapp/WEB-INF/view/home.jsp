<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="templates/headers/head.jsp"/>
    <!-- Navigation -->
   <jsp:include page="templates/navbars/navbar.jsp"/>
    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <!-- Blog Entries Column -->
                <div class="col-xs-12 ">

                    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>

                 <!--------------content-->
                 <jsp:include page="templates/components/content.jsp"/>

                 <!------end content------>
                    <!---pagination------->
                <jsp:include page="templates/paginations/pagi_1.jsp">
                    <jsp:param name="page" value="/home"/>
                </jsp:include>

                </div>
            <!-- Blog Sidebar Widgets Column -->
            <%--<jsp:include page="templates/slidebar/slidebar.jsp">--%>
                <%--<jsp:param name="action" value="mySearch.formSearch('/view-search?title=','#search')"/>--%>
                <%--<jsp:param name="urlTarget" value="/view-search"/>--%>
            <%--</jsp:include>--%>

        </div>
        <!-- /.row -->

        <hr>
    </div>
    <script src="<s:url value="public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>