var mySearch;
jQuery(document).ready(function($) {

    mySearch= {
        userSearch: function (url,query,idContent) {
            loadUserSeacrh(url,query,idContent);
        },
        postSearch:function (url,query,idContent) {
            loadSearchPost(url,query,idContent);
        },
        formSearch:function (url,id) {
            setValueInput(url,id);
        },
        checkSearch:function (id) {
            cheackSearch(id);
        }
    };

});


function loadUserSeacrh(url,query,idContent) {
    var value=$(query).val();
    var data={};
    data['msg']=value;
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : url,
        data : JSON.stringify(data),
        dataType : 'json',
        timeout : 1000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            $(idContent).html(displayTableUser(data.userList));
            console.log(displayTableUser(data.userList));
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}


function  displayTableUser(userList) {
    var content="";
    if(userList===null||userList.length<=0)
    {
        return content;
    }

    var img= "<img class='mgt--5 mgl-10' src='public/asserts/images/edit.gif' alt/>";
    for(var i=0;i<userList.length;i++) {
        var role="";
        for(var j=0;j<userList[i].roleList.length;j++)
        {
            role+=userList[i].roleList[j].role+"   ";
        }
        console.log("role  "+role);
        content+=
            "<tr >"+
                "<td>"+(i+1)+"</td>"+
                "<td>"+userList[i].userName+"</td>"+
                "<td>"+userList[i].passWord+"</td>"+
                "<td>"+role+"</td>"+
                "<td>"+
                     "<a href='/manager-user?action=delete?&id="+userList[i].id+" onclick='return window.confirm('Are you sure you want to delete this post?')'>"+
                        "<span class='glyphicon glyphicon-remove mgl-10'></span>" +
                     "</a>"+
                     "<a href='/update-user?id"+userList[i].id+"'>"+
                        img+
                    "</a>"+
                "</td>"+
            "</tr>";
    }
    return content;
}

function  loadSearchPost(url,query,idContent) {
    var value=$(query).val();
    var data={};
    data['msg']=value;
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : url,
        data : JSON.stringify(data),
        dataType : 'json',
        timeout : 1000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            if(idContent==="#table-all-post")
            {
                $(idContent).html(displayTablePost(data.posts));
            }else
            {
                $(idContent).html(displayTablePostImprove(data.posts));
            }

        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function setValueInput(url,id) {
   if($(id).length>0)
   {
       $(id).keyup(function () {
           $("#link-search").attr("href",url+$(id).val());
           console.log($(id).val());
       });
       $(id).change(function () {
           $("#link-search").attr("href",url+$(id).val());
           console.log($(id).val());
       });
   }
}

function cheackSearch(id) {
    if($(id).val().trim()==="")
    {
        return false;
    }
    return true;
}

function  checkFormValid() {
    var value=document.getElementById("search").value;
    console.log(value);
    if(value.trim()==="")
    {
        window.alert("Enter key word to search..!")
        return false;
    }
    return true;
}