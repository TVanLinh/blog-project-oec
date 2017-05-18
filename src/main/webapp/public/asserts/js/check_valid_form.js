var checkForm;

jQuery(document).ready(function ($) {
   checkForm={
       checkLogin:function () {
           login();
       }
   };
});

function  login() {
    var username=$("#username").val();
    var password=$("#password").val();
    if(username.trim()==""||password.trim()=="")
    {
        alert("Tên tài khoản hoặc mật khẩu không đúng .!");
        return false;
    }
    return true;
}
