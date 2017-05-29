<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="well" >
    <form action="${param.urlTarget}" onsubmit="return checkFormValid()">
        <div class="input-group">
            <input type="text" class="form-control"  id="search" name="title" onkeyup="${param.action}"  onsubmit="checkFormValid()">
            <span class="input-group-btn">
               <%--<a href="javascript:void(0)" id="link-search">--%>
                   <button class="btn btn-default" type="submit" onclick="return checkFormValid()" onsubmit="return checkFormValid()">
                    <span class="glyphicon glyphicon-search"></span>
                    </button>
               <%--</a>--%>
            </span>
        </div>
    </form>
</div>

<script src="<s:url value="public/asserts/js/search.js"/> "></script>