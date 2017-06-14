<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<s:url value="${param.action}" var="action"/>
<form:form action="${action}" >
    <label class="fs-20 capitalize" >${messageSource.getMessage("title",null,locale)}:</label>
    <span class="error">
        <c:if test="${requestScope.error != null}">
            ${messageSource.getMessage(requestScope.error,null,locale)}
        </c:if>
    </span>
    <%--<form:errors path="title"/>--%>
    <input name="title" id="idTitle" type="text" class="input-xs mgb-40 title" style=";margin-bottom: 30px" value="${param.title}"><br>
   <div>
        <textarea class="ckeditor" cols="80" id="content" name="content" rows="50">
                ${param.content}
        </textarea>
   </div>
    <select name="status" class="form-control" id="status" >
        <c:if test="${param.status == 1 }">
            <option value="1" selected><s:message code="public"/></option>
            <option value="0"><s:message code="private"/></option>
        </c:if>
        <c:if test="${param.status == 0 }">
            <option value="1"><s:message code="public"/></option>
            <option value="0" selected><s:message code="private"/></option>
        </c:if>
        <c:if test="${param.status !=1 && param.status != 0 }">
            <option value="1"><s:message code="public"/></option>
            <option value="0"><s:message code="private"/></option>
        </c:if>
    </select>
    <input class="hide" name="link-image" id="link-image" type="text" >
    <input class="hide" name="alt-image" id="alt-image" type="text" >
    <input type="submit" value="<s:message code="save" />" class="mgt-25 btn btn-default"
           onclick="return checkForm.getImages()" onsubmit="return checkForm.getImages()">
</form:form>