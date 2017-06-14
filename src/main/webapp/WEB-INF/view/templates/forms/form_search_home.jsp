<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search-home" class="search-hide">
    <form action="<s:url value="/view-search"/>" onsubmit="return mySearch.checkFormSearchValid('#search1')">
        <div class="input-group">
            <input type="text" name="query_search" id="search1" class="form-control"
                   onsubmit="return mySearch.checkFormSearchValid('#search1')"
                   placeholder="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('title',null,locale)}">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit" onclick="return mySearch.checkFormSearchValid('#search1')" onsubmit="return mySearch.checkFormSearchValid('#search1')">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </form>
</div>
<div id="place-holder" class="">

</div>