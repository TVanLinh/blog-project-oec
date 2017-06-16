<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<jsp:include page="templates/headers/head.jsp"/>

<jsp:include page="templates/navbars/navbar.jsp"/>

<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <s:url var="action" value="/change-pass-word"/>
        <div class="col-xs-12">

            <c:if test="${requestScope.error != null}">
                <div class="alert alert-success mgt-20">
                    <s:message code="${requestScope.error}"/>
                </div>
            </c:if>

            <c:if test="${requestScope.errors != null}">
                <div class="alert alert-danger mgt-20">
                    <c:forEach var="item" items="${requestScope.errors}">
                        <s:message code="${item}"/>
                    </c:forEach>
                </div>
            </c:if>

            <div class="mgt-20" id="error">

            </div>
            <form:form method="post" action="${action}" commandName="userForm">
                <form:input path="user.id" type="hidden" value="${requestScope.user.id}" required="true"/>
                <form:input path="user.userName" type="hidden" value="${requestScope.user.userName}"/>
                <form:input path="user.passWord" type="hidden" value="${requestScope.user.passWord}"/>
                <div class="form-group">
                    <label for="oldPassWord"><s:message code="passWord"/> <s:message code="old"/>: <span
                            class="color-red pdl-5 fs-20">*</span></label>
                    <form:input path="oldPassWord" type="password" class="form-control " name="oldPassWord" id="oldPassWord"/>
                </div>

                <div class="form-group">
                    <label for="passWord"><s:message code="passWord"/> <s:message code="new"/>:<span
                            class="color-red pdl-5 fs-20">*</span></label>
                    <input:input path="newPassWord" type="password" class="form-control " name="passWord" id="passWord"/>
                </div>

                <div class="form-group">
                    <label for="passWord"><s:message code="rePassWord"/>: <span class="color-red  pdl-5 fs-20">*</span></label>
                    <form:input path="rePassWord" type="password" class="form-control " name="rePassWord" id="rePassWord"/>
                </div>

                <input type="submit" class="btn btn-default" value="<s:message code="save"/>" id="abc">

            </form:form>
        </div>
    </div>
    <hr>
</div>
<%--<p id="demo" onclick="myFunction()">Click me.</p>--%>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="public/asserts/js/check_valid_form.js"/>"></script>
<jsp:include page="templates/footers/footer.jsp"/>
<script>
    jQuery(document).ready(function ($) {

        function checkUpdateValid() {

            var error = $("#error");
            var passWord = $("#passWord").val();
            var rePassWord = $("#rePassWord").val();
            var oldPassWord = $("#oldPassWord").val();

            if (oldPassWord.trim() === "") {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.password_not_blank"/>');
                return false;
            }

            if (passWord.trim() === "") {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.password_not_blank"/>');
                return false;
            }
            if (passWord.trim() !== "" && passWord.trim() !== rePassWord.trim()) {
                error.addClass("alert alert-danger");
                error.text('<s:message code="validation.field.overlap_password"/>');
                return false;
            }
            return true;
        }

        $("#abc").on('click', function () {
            return checkUpdateValid();
        });
    });
</script>
