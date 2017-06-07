<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form:form action="${pageContext.request.contextPath}/write-post"  commandName="post" >--%>
    <%--<label class="fs-20 text-center capitalize">${messageSource.getMessage("title",null,locale)}:</label>--%>
    <%--&lt;%&ndash;<form:errors path="title"/>&ndash;%&gt;--%>
    <%--<input name="title" id="idTitle" type="text" class="input-xs mgb-40 title" style="margin-bottom: 30px"><br>--%>
    <%--<textarea class="ckeditor" cols="80" id="content" name="content" rows="30">--%>
                             <%--Blog create By Linh Tran--%>
                        <%--</textarea>--%>
    <%--<select name="status" class="form-control" id="status" >--%>
        <%--<option value="1" >${messageSource.getMessage("public",null,locale)}</option>--%>
        <%--<option value="0">${messageSource.getMessage("private",null,locale)}</option>--%>
    <%--</select>--%>
    <%--<input class="hide" name="link-image" id="link-image" type="text" >--%>
    <%--<input class="hide" name="alt-image" id="alt-image" type="text" >--%>
    <%--<input type="submit"  value="${messageSource.getMessage("save",null,locale)}" class="mgt-25 btn btn-default" onclick="return getImages()" onsubmit="return getImages()">--%>
<%--</form:form>--%>

<s:url value="${param.action}" var="action"/>
<form:form action="${action}"  commandName="post">
    <label class="fs-20 capitalize">${messageSource.getMessage("title",null,locale)}:</label>
    <span class="error">${requestScope.error}</span>
    <%--<form:errors path="title"/>--%>
    <input name="title" id="idTitle" type="text" class="input-xs mgb-40 title" style=";margin-bottom: 30px" value="${param.title}"><br>
   <div>
        <textarea class="ckeditor" cols="80" id="content" name="content" rows="50">
                ${param.content}
        </textarea>
   </div>
    <select name="status" class="form-control" id="status" >
        <option value="1" >${messageSource.getMessage("public",null,locale)}</option>
        <option value="0">${messageSource.getMessage("private",null,locale)}</option>
    </select>
    <input class="hide" name="link-image" id="link-image" type="text" >
    <input class="hide" name="alt-image" id="alt-image" type="text" >
    <input type="submit"  value="${messageSource.getMessage("save",null,locale)}" class="mgt-25 btn btn-default" onclick="return checkForm.getImages()" onsubmit="return checkForm.getImages()">
</form:form>