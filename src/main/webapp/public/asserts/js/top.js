$(document).ready(function(){
    var $_top=$("#top");
    if($_top.length>0){
        $(window).scroll(function(){
            var e =$(window).scrollTop();
            if(e>200){
                $_top.show(1000);
            }else{
                $_top.hide(1000);
            }
        });
    }
    $_top.on('click',function()
    {
        $('body,html').animate(
            {
                scrollTop:0
            }
        );
    });
});