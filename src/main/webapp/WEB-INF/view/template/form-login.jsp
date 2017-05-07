<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="col-sm-6 col-xs-12">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

        <div id="form-login" class="modal">

            <form name='loginForm' class="modal-content animate" action="<c:url value='/j_spring_security_check' />" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('form-login').style.display='none'" class="close" title="Close Modal">&times;</span>
                    <%--<img src="img_avatar2.png" alt="Avatar" class="avatar">--%>
                </div>

                <div class="container">
                    <div class="col-xs-12">
                        <label><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" id="username" required>
                    </div>

                    <div class="col-xs-12">
                        <label><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" id="password" required>
                    </div>

                    <div class="col-xs-12">
                        <button type="submit" onclick="return login()">Login</button>
                        <input type="checkbox" checked="checked"> Remember me
                        <span class="psw">Forgot <a href="#">password?</a></span>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />

                </div>

                <%--<div class="container">--%>
                <%--<div class="col-xs-12">--%>
                <%--<button type="button" onclick="document.getElementById('form-login').style.display='none'" class="cancelbtn">Cancel</button>--%>
                <%----%>
                <%--</div>--%>
                <%--</div>--%>
            </form>
        </div>

    </div>
</div>
<script>
    // Get the modal
    var modal = document.getElementById('form-login');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
