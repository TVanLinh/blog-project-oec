<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<a class="btn btn-danger menu-item  mgb-15 " href="<s:url value="/admin"/>">
    <i class="fa fa-book mgr-10 mgl--20" aria-hidden="true"></i>
    <s:message code="post.not.approve"/>
</a>
<a class="btn btn-danger menu-item  mgb-15" href="<s:url value="/manager-post"/>">
    <i class="fa fa-book mgr-10 mgl--20" aria-hidden="true"></i>
    <s:message code="managerPost"/>
</a>
<a class="btn btn-success menu-item  mgb-15 " style="padding-left: 3px" href="<s:url value="/configuration"/>">
    <span class="glyphicon glyphicon-cog mgr-10"></span>
    <s:message code="configSystem"/>
</a>

<a class=" btn btn-warning menu-item  mgb-15 " style="padding-left: 5px" href="<s:url value="/manager-user"/>">
    <i class="glyphicon glyphicon-user mgr-10"></i><s:message code="managerUser"/>
</a>
