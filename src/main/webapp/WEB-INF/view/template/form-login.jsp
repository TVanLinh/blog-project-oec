<div id="form-login" class="modal">

    <form class="modal-content animate" action="/action_page.php">
        <div class="imgcontainer">
            <span onclick="document.getElementById('form-login').style.display='none'" class="close" title="Close Modal">&times;</span>
            <img src="img_avatar2.png" alt="Avatar" class="avatar">
        </div>

        <div class="container">
           <div class="col-xs-12">
               <label><b>Username</b></label>
               <input type="text" placeholder="Enter Username" name="uname" required>
           </div>

            <div class="col-xs-12">
                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required>
           </div>

            <div class="col-xs-12">
                <button type="submit">Login</button>
                <input type="checkbox" checked="checked"> Remember me
           </div>



        </div>

        <div class="container" style="background-color:#f1f1f1">
            <div class="col-xs-12">
                <button type="button" onclick="document.getElementById('form-login').style.display='none'" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </div>
    </form>
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
