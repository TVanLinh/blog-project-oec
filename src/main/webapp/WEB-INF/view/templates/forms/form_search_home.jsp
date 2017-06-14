<%@include file="../components/import_libary.jsp" %>
<div id="search-home" class="search-hide">
    <form action="<s:url value="/view-search"/>" onsubmit="return mySearch.checkFormSearchValid('#search1')">
        <div class="input-group">
            <input type="text" name="query_search" id="search1" class="form-control"
                   onsubmit="return mySearch.checkFormSearchValid('#search1')"
                   placeholder="<s:message code="searchBy" /> <s:message code="title" />">
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