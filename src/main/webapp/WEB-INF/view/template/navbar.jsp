<nav class="navbar navbar-inverse " style="border-radius: 0 !important;">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Linh Tran Blog</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Page 1-1</a></li>
                    <li><a href="#">Page 1-2</a></li>
                    <li><a href="#">Page 1-3</a></li>
                </ul>
            </li>
            <li><a href="#">Page 2</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#" onclick="document.getElementById('form-login').style.display='block'"><span class="glyphicon glyphicon-log-in" ></span> Login</a></li>
            <li><a href="#"><img src="/images/US.gif" alt="USA"></a></li>
            <li><a href="#"><img src="/images/VI.gif"></a></li>
        </ul>
    </div>
</nav>

<jsp:include page="form-login.jsp"/>