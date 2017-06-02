<!-- Blog Search class="well" Well -->
<div >
    <%--<h4>Blog Search</h4>--%>
    <form>
        <div class="input-group">
            <input type="text" class="form-control" onkeyup="${param.action}" id="search">
            <%--<input type="text" class="form-control" onkeyup="searchUser.userSearch('/search-user','#search','#table-all-user')" id="search">--%>
            <%--<input type="text" class="form-control" onclick="searchUser.userSearch()" id="search">--%>
            <span class="input-group-btn">
                <button class="btn btn-default" type="button">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
        </span>
        </div>
    </form>
</div>