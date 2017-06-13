<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="templates/headers/head.jsp"/>
    <!-- Navigation -->
   <jsp:include page="templates/navbars/navbar.jsp">
       <jsp:param name="active" value="home"/>
   </jsp:include>
    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <!-- Blog Entries Column -->
                <div class="col-xs-12 ">
                    <jsp:useBean id="dateUtil" class="utils.date.DateFormatUtil" scope="session"/>
                  <p class="error">
                      <c:if test="${requestScope.error != null}">
                          ${messageSource.getMessage(requestScope.error,null,locale)}
                      </c:if>
                      <c:if test="${param.error != null}">
                          ${messageSource.getMessage(param.error,null,locale)}
                      </c:if>
                  </p>
                 <!--------------content-->
                 <jsp:include page="templates/components/content.jsp"/>

                 <!------end content------>
                    <!---pagination------->
                <jsp:include page="templates/paginations/pagi_1.jsp">
                    <jsp:param name="pageTarget" value="/home"/>
                </jsp:include>

                </div>
        </div>
        <!-- /.row -->

        <hr>
    </div>
    <script src="<s:url value="public/asserts/js/search.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>