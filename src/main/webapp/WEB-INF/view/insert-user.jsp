<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>


<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>

<div class="container-fluid">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-lg-2 col-md-3 col-xs-12">
            <jsp:include page="templates/menus/menu-admin.jsp"/>
        </div>


        <div class="col-lg-8 col-md-8 col-xs-12">
            <c:if test="${requestScope.errors != null}">
                <div class="alert alert-danger mgt-20">
                    <c:forEach var="item" items="${requestScope.errors}">
                        <s:message code="${item}"/>
                    </c:forEach>
                </div>
            </c:if>
            <div class="mgt-20" id="error">

            </div>
            <s:url value="/action-insert-user" var="action"/>
            <form:form method="post" action="${action}" modelAttribute="userForm">
                <div class="form-group">
                    <label for="userName"><s:message code="name"/>: <span class="color-red fs-20 mgl-5">*</span>
                    </label>
                    <form:input type="text" class="form-control" id="userName" path="user.userName"/>
                </div>
                <div class="form-group">
                    <label for="passWord"><s:message code="passWord"/>: <span
                            class="color-red fs-20 mgl-5">*</span></label>
                    <form:input type="password" class="form-control" name="passWord" id="passWord"
                                path="user.passWord"/>
                </div>
                <div class="form-group">
                    <label for="rePassWord"><s:message code="rePassWord"/>: <span class="color-red fs-20 mgl-5">*</span></label>
                    <form:input type="password" class="form-control" id="rePassWord" path="rePassWord"/>
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime"><s:message code="role"/>: <span
                            class="color-red fs-20 mgl-5">*</span></label>
                    <form:select class="form-control" multiple="multiple" id="listRole" path="user.roleList">
                        <option value="ROLE_USER">ROLE_USER</option>
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    </form:select>

                        <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <input type="submit" class="btn btn-default" value="<s:message code="save" />" id="abc">

            </form:form>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- /.row -->
    <hr>


</div>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>
<script>
    jQuery(document).ready(function ($) {

        function checkValidFormInsertUser() {

            var error = $("#error");
            var userName = $("#userName").val();
            var passWord = $("#passWord").val();
            var rePassWord = $("#rePassWord").val();
            var oldPassWord = $("#oldPassWord").val();
            var listRole = $("#listRole").val();

            if (userName.trim() === "") {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.username_not_blank"/>');
                return false;
            }

            if (passWord.trim() == "") {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.password_not_blank"/>');
                return false;
            }

            if (passWord.trim() !== "" && passWord.trim() !== rePassWord.trim()) {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.overlap_password"/>');
                return false;
            }
            if (listRole === "") {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.role_not_blank"/>');
                return false;
            }
            return true;
        }

        $("#abc").on('click', function () {
            return checkValidFormInsertUser();
        });
    });
</script>

