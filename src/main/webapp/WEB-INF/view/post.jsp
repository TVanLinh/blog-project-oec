<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="template/head.jsp"/>

<body>
    <script src="<s:url value="public/jquery/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
    <!-- Navigation -->
   <jsp:include page="template/navbar.jsp"/>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->
                <!-- Title -->
                <h2>${post.title}</h2>
                <span class="lead">Post by <a href="#">${post.user.userName}</a>${post.status}</span>
                <hr>
                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-5" >Posted on</span>  ${post.timePost}</p>
                <hr>

                <!-- Post Content -->
                ${post.content}

                <jsp:useBean id="cookieUtils" type="Utils.CookieUtils" scope="session" class="Utils.CookieUtils"/>
                <hr>
                    Like:
                <c:if test="${cookie.status_like_post==null ||cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==false}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/notlike.png"/>" alt="not like"  class="">
                </c:if>

                <c:if test="${cookie.status_like_post!=null&&cookieUtils.isLike(post.id,cookie.status_like_post.value)==true}">
                    <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/like.png"/>" alt="like"  class="">
                </c:if>

                </button><button class="btn-sm btn-xs" id="number-like">${post.numberLike}</button>

                view: <button class="btn-sm btn-xs">${post.numberView} </button>

               <c:if test="${sessionScope.username!=null}">
                    <a id="action-update" href="/update?action=update&id=${post.id}">Update</a> <a id="action-" onclick="return window.confirm('Are you sure you want to delete this post?')" href="/delete-post?id=${post.id}">Xoa</a>
                </c:if>
                <hr>
                <div id="comment">
                   <jsp:include page="template/comment.jsp"/>
               </div>

            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div>
                <jsp:include page="template/slidebar-post.jsp"/>
            </div>


        </div>
        <!-- /.row -->

        <hr>

        <jsp:include page="template/footer.jsp"/>

    </div>
    <script type="text/javascript">
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
        }
    </script>


    <script type="text/javascript">
        var A;
    jQuery(document).ready(function($)
    {
         A={
                like:function(id,msg,$this,target)
                {
                    actionLike(id,msg);
                    console.log(target);
                },
                getPost:function (msg,page) {
                    getPostSlidebar(msg,page);
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
        console.log("numberConf "+data.numberConf);
        console.log(content);
        $("#content-slidebar").html(content);
        $("#nextPost").attr("onclick","A.getPost('get-post-page-post',"+data.numberPage+")");
        $("#previousPost").attr("onclick","A.getPost('get-post-page-post',"+(data.numberPage-2)+")");
        console.log( $("#nextPost"));
      //  alert("ok");
    }
    </script>

</body>

</html>