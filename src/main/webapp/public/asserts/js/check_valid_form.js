var checkForm;

jQuery(document).ready(function ($) {
   checkForm={
       checkLogin:function () {
           login();
       },
       getImages:function () {
          return getImages2();
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

function getImages2() {
    var linkInput=$('#cke_123_textInput');
    var altInput=$('#cke_130_textInput');
    if(linkInput.length>0)
    {
        $('#link-image').val(linkInput.val());
    }
    if(altInput.length>0)
    {
        $('#alt-image').val(altInput.val());
    }

    // var title=$('#idTitle');
    // var cont=$("#content");
    //
    // if(title.val().trim()==="")
    // {
    //     alert("Title not null..!");
    //     return false;
    // }
    // if(title.val().length>200)
    // {
    //     alert("Max length 200..!");
    //     return false;
    // }
    // if(cont.val().trim()==="")
    // {
    //     alert("Content not null..!");
    //     return false;
    // }
    return true;
}


//
// function  checkFormInsertUser() {
//     var userName=document.getElementById("userName").value;
//     var passWord=document.getElementById("passWord").value;
//     var rePassWord=document.getElementById("rePassWord").value;
//     var listRole=document.getElementById("listRole").value;
//     if(userName.trim()=="")
//     {
//         window.alert("Enter user name !");
//         return false;
//     }
//     if(passWord.trim()=="")
//     {
//         window.alert("Enter pass word !");
//         return false;
//     }
//     if(passWord.trim()!==""&&passWord.trim()!=rePassWord.trim())
//     {
//         window.alert("The two passwords do not overlap !");
//         return false;
//     }
//     if(listRole==="")
//     {
//         window.alert("Not select role !");
//         return false ;
//     }
//     return true;
// }


function checkFormInsertUser(msgUser, msgPass1, msgPass2, msgSelect, action) {
    var userName=document.getElementById("userName").value;
    var passWord=document.getElementById("passWord").value;
    var rePassWord=document.getElementById("rePassWord").value;
    var listRole=document.getElementById("listRole").value;

    if(userName.trim()=="")
    {
        window.alert(msgUser);
        return false;
    }
    if (action != 'update') {
        if (passWord.trim() === "") {
            window.alert(msgPass1);
            return false;
        }
    }
    if (passWord.trim() != "" && passWord.trim() != rePassWord.trim())
    {
        window.alert(msgPass2);
        return false;
    }
    if (listRole === "")
    {
        window.alert(msgSelect);
        return false ;
    }
    return true;
}


function checkFormValidPassWord(msgOldPass, msgPass, msg) {
    var passWord=document.getElementById("passWord").value;
    var rePassWord=document.getElementById("rePassWord").value;
    var oldPassWord=document.getElementById("oldPassWord").value;
    if(oldPassWord.trim()==="")
    {
        window.alert(msgOldPass);
        return false;
    }
    if(passWord.trim()==="")
    {
        window.alert(msgPass);
        return false;
    }
    if(passWord.trim()!==""&&passWord.trim()!=rePassWord.trim())
    {
        window.alert(msg);
        return false;
    }
    return true;
}

function checkFormValidPassWordUser(msgOldPass, msgPass, msg) {
    var passWord = $("#passWord").val();
    var rePassWord = $("#rePassWord").val();
    var oldPassWord = $("#oldPassWord").val();

    if (oldPassWord.trim() === "") {
        return false;
    }
    if (passWord.trim() === "") {
        window.alert(msgPass);
        return false;
    }
    if (passWord.trim() !== "" && passWord.trim() != rePassWord.trim()) {
        window.alert(msg);
        return false;
    }
    return true;
}