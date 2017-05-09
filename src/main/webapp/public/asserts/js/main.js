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


(function ($)
{
    var A={
      like:function like(id) {
          window.alert('ok');
          $.ajax({
              type : "POST",
              contentType : "application/text",
              url : "/like",
              data : {"id":id},
              daype : 'text',
              timeout : 1000,
              success : function(data) {
                  console.log("SUCCESS: ", data);
                  display(data);
                  $("#like").text("ok");
              },
              error : function(e) {
                  console.log("ERROR: ", e);
                  display(e);
              },
              done : function(e) {
                  console.log("DONE");
              }
          });
      }
    };
})(jQuery);
