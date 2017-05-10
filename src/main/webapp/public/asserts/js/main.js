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

CKEDITOR.replace( 'content' );

var A;

    jQuery(document).ready(function($) {
        $("#like").on("click",function(event) {
            ($(this).html("<p>Goodbye self</p>"));
            window.alert("ok men");
            event.preventDefault();
            enableSearchButton(true)
            searchViaAjax();
        });

    });

function searchViaAjax() {

    var search = {};
    search["msg"] = "ok";
    search["code"] = "4444";

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/like",
        data : JSON.stringify(search),
        dataType : 'json',
        timeout : 1000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });

}

function enableSearchButton(flag) {
    $("#like").prop("disabled", flag);
}

function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(data, null, 4) + "</pre>";
    $('#like').html(json);
}