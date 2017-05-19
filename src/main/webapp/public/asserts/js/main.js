var A;
jQuery(document).ready(function($) {
    A={
        like:function(id,msg,$this,target)
        {
            actionLike(id,msg);
            console.log(target);
        },
        getPost:function (msg,page) {
            getPostSlidebar(msg,page);
        },
        getPostImprove:function (msg,page) {
            getPostTableRemove(msg,page);
            location.reload();
        }
    }
});

function actionLike(id,msg) {

    var data = {};
    data['id']=id;
    data["msg"] = msg;
    data["code"] = "200";
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/like",
        data : JSON.stringify(data),
        dataType : 'json',
        timeout : 1000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            $("#number-like").text(data['numberLike']);
            $("#like").attr("src",data['statusImg']);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });

}

function  getPostSlidebar(msg,page) {
    var data ={};
    data['msg']=msg;
    data['numberPage']=page;
    $.ajax({
        type:"POST",
        contentType:"application/json",
        data : JSON.stringify(data),
        url:"/get-post",
        dataType:"json",
        timeout:1000,
        success:function (data) {
            console.log(data);
            dsplayPostSlidebar(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function dsplayPostSlidebar(data) {
    var content="";
    var postList=data.posts;
    if(postList.length==0&&data.numberPage-2<0)
    {
        $('#previousPost').addClass('hide');
        return;
    }
    for(var i=0;i<postList.length;i++)
    {
        content+= "<li class='media'>";
        if(postList[i].image!=null)
        {
            content+= "<a class='pull-left' href='/post?id="+postList[i].id+"'>"+"<img class='media-object pdb-15' src='"+postList[i].image.link+"'"+"alt='...'/>"
                +"</a>";
        }
        content+="<div class='media-body'>"+"<h4 class='media-heading'>"+postList[i].title+"</h4>"
            +"<p class='by-user color-main2'>By"+ postList[i].user.userName+"</p>"
            +"<a href='/post?id="+postList[i].id+"'>"+"read more..</a>"
            +"</div>"
            +"</li>";
    }
    if(data.numberPage-2<=0)
    {
        $('#previousPost').addClass('hide');
    }else(data.numberPage-2>0)
    {
        $('#previousPost').removeClass('hide');
    }

    if((postList.length<data.numberConf&&data.numberPage>1))
    {
        $('#nextPost').addClass('hide');
    }else
    {
        $('#nextPost').removeClass('hide');
    }
    $("#content-slidebar").html(content);
    $("#nextPost").attr("onclick","A.getPost('get-post-page-post',"+data.numberPage+")");
    $("#previousPost").attr("onclick","A.getPost('get-post-page-post',"+(data.numberPage-2)+")");

}




function  getPostTableRemove(msg,page) {
    var data ={};
    data['msg']=msg;
    data['numberPage']=page;
    $.ajax({
        type:"POST",
        contentType:"application/json",
        data : JSON.stringify(data),
        url:msg,
        dataType:"json",
        timeout:10000,
        success:function (data) {
            console.log(data);
            showPostAdmin(data,"#table-post-approve",displayTablePostImprove(data.posts));
            showPostAdmin(data,"#table-all-post",displayTablePost(data.posts));
            // displayTablePost(data.posts);
            $("#numberApprove").text(data.numberApprove);
            $("#totalPost").text(data.totalPost);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function showPostAdmin(data,idContent,content) {

    var postList=data.posts;
    if(postList.length==0)
    {
        $(idContent).html(content);
        return;
    }
    $(idContent).html(content);
}

function  displayTablePostImprove(postList) {
   var content="";
   if(postList===null||postList.length<=0)
   {
       return content;
   }
   var del;
   var approve;
   for(var i=0;i<postList.length;i++) {
       del="'/admin-post?action=delete&id="+ postList[i].id+"'";
       approve="'/admin-post?action=approve&id="+ postList[i].id+"'";
       console.log(del+"----------"+approve);
       content += "<tr>" +
               "<td>"+(i+1)+"</td>"
               +
           "<td>" + postList[i].user.userName + "</td>" +
           "<td><a href='/post?id="+postList[i].id+"'>" + postList[i].title +"</a></td>" +
           "<td>" + formaDate(postList[i].timePost) + "</td>" +
           "<td>" +
           "<a href='javascript:void(0)' "+" onclick=A.getPostImprove("+ approve +","+"1"+")" + ">" +

           "<span class='glyphicon glyphicon-ok mgr-10'></span>" +
           "</a>" +
           "<a href='javascript:void(0)' " +" onclick=A.getPostImprove("+ del +","+"1"+")"+ ">" +
           "<span class='glyphicon glyphicon-remove mgl-10'></span>" +
           "</a>" +
           "<a href='/update?action=update&id=" + postList[i].id + "'>" +
           "<img class='mgt--5 mgl-10' src='public/asserts/images/edit.gif' alt/>" +
           "</a>" +
           "</td>" +
           "</tr>";
   }
   return content;
}

function formaDate(str) {
    var date=new Date(str);
    return   date.getDate()+"-"+date.getMonth()+"-"+date.getFullYear()+" "+
        date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();

}

function  displayTablePost(postList) {
    var content="";
    if(postList===null||postList.length<=0)
    {
        return content;
    }
    var del;
    for(var i=0;i<postList.length;i++) {
        var approve="No";
        var status="private";
        del="'/manager-get-all-post-delete?id="+ postList[i].id+"'";
        if(postList[i].status==1)
        {
            status="public";
        }
        if(postList[i].approve==1)
        {
            approve="Yes";
        }
        content += "<tr>" +
            "<td>"+(i+1)+"</td>"
            +
            "<td>" + postList[i].user.userName + "</td>" +
            "<td><a href='/post?id="+postList[i].id+"'>" + postList[i].title +"</a></td>" +
            "<td>" + formaDate(postList[i].timePost) + "</td>" +
            "<td>"+status+"</td>"+
            "<td>"+approve+"</td>"+
            "<td>"+postList[i].numberLike+"</td>"+
            "<td>"+postList[i].numberView+"</td>"+
            "<td>" +
                "<a href='javascript:void(0)' " +" onclick=A.getPostImprove("+ del +","+"1"+")"+ ">" +
                "<span class='glyphicon glyphicon-remove mgl-10'></span>" +
                "</a>" +
                "<a href='/update?action=update&id=" + postList[i].id + "'>" +
                "<img class='mgt--5 mgl-10' src='public/asserts/images/edit.gif' alt/>" +
                "</a>" +
            "</td>" +
            "</tr>";
    }
    return content;
}


