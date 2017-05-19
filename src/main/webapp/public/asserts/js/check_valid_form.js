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

function checkTitle(tagret)
{
    var title=$(tagret).val();
    if(title.trim()==="")
    {
        return false;
    }
    if(title.length>200)
    {
        alert("Max length 200..!")
        return false;
    }
    return true;
}

function getImages()
{
    var input=document.getElementById('cke_122_textInput');
    var outPut=document.getElementById('link-image');

    var altInput=document.getElementById('cke_129_textInput');
    var atlOutPut=document.getElementById('alt-image');
    if(input!=null)
    {
        outPut.value=input.value;
    }
    if(altInput!=null)
    {
        atlOutPut.value=altInput.value;
    }
    // alert(altInput);
    var title=document.getElementById("idTitle").value;
    if(title.trim()==="")
    {
        alert("Title not null..!");
        return false;
    }
    if(title.length>200)
    {
        alert("Max length 200..!");
        return false;
    }
    var content=document.getElementById("content").value;
    if(content.trim()==="")
    {
        alert("Content not null..!");
        return false;
    }
    return true;
}