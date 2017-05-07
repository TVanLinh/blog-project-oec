function  login() {
    var username=document.getElementsByName("username").value;
    var password=document.getElementsByName("username").value;
    console.log("ok");
    console.log("pass:"+username);
    console.log(password);

    if(username.trim()==""||password.trim()=="")
    {
        alert("username and password not null");
        return false;
    }
    return true;
}