<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/headers/head.jsp"/>
<body>

<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-12">

            <jsp:include page="templates/menus/menu-admin.jsp"/>
            <spring:url var="action" value="/processConfiguration"/>

            <form:form ACTION="${action}" METHOD="post" commandName="configuration">
                <div class="form-group">
                    <label for="titleBlog">${messageSource.getMessage("titleBlog",null,locale)}:</label>
                    <input:input path="webTitle" type="text" class="form-control " name="titleBlog" id="titleBlog" value="${requestScope.conf.webTitle}"/>
                </div>
                <div class="form-group">
                    <label for="formatTime" id="formatTime" >${messageSource.getMessage("fomatDate",null,locale)}:</label>
                    <jsp:useBean id="confService" class="service.ConfigurationService"/>
                    <form:select path="dateFormat" class="form-control"  name="formatTime" >
                        <c:if test="${confService.isHaveFormatTime(requestScope.conf,'HH:mm:ss dd/MM/yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" selected>HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy">HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf,'HH:mm:ss dd-MM-yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" selected>HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf,'dd/MM/yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" >HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy" selected>dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf,'dd-MM-yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" >HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy" >dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy" selected>dd-MM-yyyy</option>
                        </c:if>

                    </form:select>

                    <%--<input type="text" class="form-control pd-0" name="formatTime" id="formatTime">--%>
                </div>
                <div class="form-group">
                    <label for="numberPost">${messageSource.getMessage("numberView",null,locale)}:</label>
                    <form:input path="numberViewPost" type="number" min="1"  value="${requestScope.conf.numberViewPost}" class="form-control pd-0" name="numberPost" id="numberPost"/>
                </div>
                <input type="submit" class="btn btn-default" value="${messageSource.getMessage("save",null,locale)}">
                <p class="pd-10 error">
                    <c:if test="${requestScope.errors != null}">
                        <c:forEach var="item" items="${requestScope.errors}">
                            ${messageSource.getMessage(item,null,locale)}
                        </c:forEach>
                    </c:if>

                    <c:if test="${requestScope.success != null}">
                        ${messageSource.getMessage(requestScope.success,null,locale)}
                    </c:if>
                </p>
            </form:form>
        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<jsp:include page="templates/footers/footer.jsp"/>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
