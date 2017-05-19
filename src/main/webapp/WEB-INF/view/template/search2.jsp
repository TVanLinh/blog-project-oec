<div class="well" >
    <form action="${param.urlTarget}">
        <div class="input-group">
            <input type="text" class="form-control"  id="search" name="title" onkeyup="${param.action}">
            <span class="input-group-btn">
               <%--<a href="javascript:void(0)" id="link-search">--%>
                   <button class="btn btn-default" type="submit" onclick="return mySearch.cheackSearch('#search')">
                    <span class="glyphicon glyphicon-search"></span>
                    </button>
               <%--</a>--%>
            </span>
        </div>
    </form>
</div>

<%--<script>--%>
    <%--jQuery(document).ready(function ($) {--%>
        <%--$("#search").keyup(function () {--%>
            <%--$("#link-search").attr("href","/view-search?title="+$("#search").val());--%>
            <%--console.log( $("#search").val());--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>