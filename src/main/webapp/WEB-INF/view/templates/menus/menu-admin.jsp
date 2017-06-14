<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--<ul class="list-group menu-admin">--%>
    <%--<li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="<s:url value="/manager-post"/>"><img src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10">${messageSource.getMessage("managerPost",null,locale)}</a></li>--%>
    <%--<li class="list-inline btn btn-success mgr-10  mgb-15"><a href="<s:url value="/configuration"/>"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>--%>
    <%--<li class="list-inline btn btn-warning mgl-10  mgb-15"> <a href="<s:url value="/manager-user"/>"><i class="glyphicon glyphicon-user mgr-10"></i>${messageSource.getMessage("managerUser",null,locale)}</a></li>--%>
<%--</ul>--%>

<%--<img  src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10 mgl--20">--%>

<a class="btn btn-danger menu-item  mgb-15 " href="<s:url value="/admin"/>">
    <i class="fa fa-book mgr-10 mgl--20" aria-hidden="true"></i>
    <s:message code="post.not.approve"/>
</a>
<a class="btn btn-danger menu-item  mgb-15" href="<s:url value="/manager-post"/>">
    <i class="fa fa-book mgr-10 mgl--20" aria-hidden="true"></i>
    <s:message code="managerPost"/>
</a>
<a class="btn btn-success menu-item  mgb-15" href="<s:url value="/configuration"/>">
    <span class="glyphicon glyphicon-cog mgr-10"></span>
    <s:message code="configSystem"/>
</a>

<a class=" btn btn-warning menu-item  mgb-15" href="<s:url value="/manager-user"/>">
    <i class="glyphicon glyphicon-user mgr-10"></i><s:message code="managerUser"/>
</a>
