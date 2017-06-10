<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>


<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-xs-12">
            <form method="post" action="<s:url value="/change-pass-word"/> "  onsubmit="return checkFormValidPassWord()">
                <div class="form-group">
                    <label for="oldPassWord">${messageSource.getMessage("passWord",null,locale)} ${messageSource.getMessage("old",null,locale)}:</label>
                    <input type="password" class="form-control " name="oldPassWord" id="oldPassWord">
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("passWord",null,locale)} ${messageSource.getMessage("new",null,locale)}:</label>
                    <input type="password" class="form-control " name="passWord" id="passWord">
                </div>
                <div class="form-group">
                    <label for="passWord">${messageSource.getMessage("rePassWord",null,locale)}:</label>
                    <input type="password" class="form-control " name="rePassWord" id="rePassWord">
                </div>
                <input type="submit" class="btn btn-default" value="${messageSource.getMessage("save",null,locale)}" onsubmit="return checkFormValidPassWord()" onclick="return checkFormInsertUser()">
                <p class="pd-10 error">
                    <c:if test="${requestScope.error != null}">
                        ${messageSource.getMessage(requestScope.error,null,locale)}
                    </c:if>
                </p>
            </form>
        </div>
        <!-- Blog Sidebar Widgets Column -->
        <div>
            <%--<jsp:include page="templates/slidebar.jsp"/>--%>
        </div>
    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<%--<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>--%>
<jsp:include page="templates/footers/footer.jsp"/>

