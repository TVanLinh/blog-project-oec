<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search-home" class="search-hide">
    <form action="<s:url value="/view-search"/> " onsubmit="return checkFormValid()">
        <div class="input-group">
            <input type="text" name="title" id="search" class="form-control" placeholder="${messageSource.getMessage('searchBy',null,locale)} ${messageSource.getMessage('title',null,locale)}" >
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit" onsubmit="return checkFormValid()" onclick="return checkFormValid()">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </form>
</div>
<div id="place-holder" class="mgb-30">

</div>