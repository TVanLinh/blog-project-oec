<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<jsp:include page="templates/headers/head.jsp"/>
<style>
    .menu-item:nth-child(3)
    {
        color: #ffff5d;
    }
</style>
<!-- Navigation -->
<jsp:include page="templates/navbars/navbar.jsp"/>
<%--<%@page session="true"%>--%>
<!-- Page Content -->
<div class="container-fluid">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-lg-2 col-md-3 col-xs-12">
            <jsp:include page="templates/menus/menu-admin.jsp"/>
        </div>

        <div class="col-lg-8 col-md-8 col-xs-12">

            <%--<jsp:include page="templates/menus/menu-admin.jsp"/>--%>
            <spring:url var="action" value="/process-configuration"/>

            <jsp:useBean id="confService" scope="page" class="service.ConfigurationService"/>

            <form:form ACTION="${action}" METHOD="post" commandName="configForm">

                <div class="form-group">
                    <label for="titleBlog"><s:message code="titleBlog"/>:</label>
                    <input:input path="title" type="text" class="form-control " name="titleBlog" id="titleBlog"
                                 value="${requestScope.conf['title'].getValue()}"/>
                </div>

                <div class="form-group">
                    <label for="formatTime" id="formatTime"><s:message code="fomatDate"/>:</label>
                    <form:select path="dateFormat" class="form-control"  name="formatTime" >
                        <c:if test="${confService.isHaveFormatTime(requestScope.conf['date_format'],'HH:mm:ss dd/MM/yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" selected>HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy">HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf['date_format'],'HH:mm:ss dd-MM-yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" selected>HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf['date_format'],'dd/MM/yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" >HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy" selected>dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        </c:if>

                        <c:if test="${confService.isHaveFormatTime(requestScope.conf['date_format'],'dd-MM-yyyy')}">
                            <option value="HH:mm:ss dd/MM/yyyy" >HH:mm:ss dd/MM/yyyy</option>
                            <option value="HH:mm:ss dd-MM-yyyy" >HH:mm:ss dd-MM-yyyy</option>
                            <option value="dd/MM/yyyy" >dd/MM/yyyy</option>
                            <option value="dd-MM-yyyy" selected>dd-MM-yyyy</option>
                        </c:if>

                    </form:select>

                </div>

                <div class="form-group">
                    <label for="numberPost"><s:message code="numberView"/>:</label>
                    <form:input path="numberView" type="number" min="1"
                                value="${requestScope.conf['number_post_view'].getValue()}" class="form-control pd-0"
                                name="numberPost" id="numberPost"/>
                </div>

                <input type="submit" class="btn btn-default" value="<s:message code="save" />">

                <p class="pd-10 error">
                    <c:if test="${requestScope.errors != null}">
                        <c:forEach var="item" items="${requestScope.errors}">
                            <s:message code="${item}"/>
                        </c:forEach>
                    </c:if>

                    <c:if test="${requestScope.success != null}">
                        <s:message code="${requestScope.success}"/>
                    </c:if>
                </p>
            </form:form>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- /.row -->
    <hr>
</div>
<jsp:include page="templates/footers/footer.jsp"/>
<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>
