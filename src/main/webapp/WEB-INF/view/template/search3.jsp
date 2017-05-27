<!-- Blog Search class="well" Well -->
<div >
    <%--<h4>Blog Search</h4>--%>
        <form method="get" action="${param.action}">
            <div class="input-group">
                <input type="text" class="form-control"  id="search" name="query_search">
                <span class="input-group-btn">
                <button class="btn btn-default" type="submit">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
                </span>
            </div>
        </form>
</div>