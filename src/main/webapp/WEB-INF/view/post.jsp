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
                <span class="lead">
                by <a href="#">${post.user.userName}</a>${post.status}
                </span>
                <hr>
                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span><span class="margin-left-5" >Posted on</span>  ${post.timePost}</p>

                <hr>

                <%--<!-- Preview Image -->--%>
                <%--<img class="img-responsive" src="http://placehold.it/900x300" alt="">--%>

                <hr>

                <!-- Post Content -->
                ${post.content}
                <hr>

                Like: <img id="like" onclick="A.like(${post.id},'dislike','#like','#not-like')" src="<s:url value="public/asserts/images/like.png"/>" alt="like"  class="hide">
                <img onclick="A.like(${post.id},'like','#not-like','#like')" src="<s:url value="public/asserts/images/notlike.png"/>" alt="like" id="not-like">
                </button><button class="btn-sm btn-xs" id="number-like">${post.numberLike}</button>  view: <button class="btn-sm btn-xs">${post.numberView} </button>

               <c:if test="${sessionScope.username!=null}">
                    <a href="/update?action=update&id=${post.id}">Update</a> <a href="/update?action=delete">Xoa</a>
                </c:if>

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
        var a;
    jQuery(document).ready(function($)
    {
         A={
            like:function(id,msg,$this,target)
            {
                searchViaAjax(id,msg);
                console.log(target);
                $($this).addClass('hide');
                $(target).removeClass('hide');
            }
        }
    });

    function searchViaAjax(id,msg) {

    var search = {};
    search['id']=id;
    search["msg"] = msg;
    search["code"] = "200";
    $.ajax({
        type : "POST",
            contentType : "application/json",
            url : "/like",
            data : JSON.stringify(search),
            dataType : 'json',
            timeout : 1000,
            success : function(data) {
            console.log("SUCCESS: ", data);
            $("#number-like").text(data['numberLike']);
            display(data);
            },
        error : function(e) {
             console.log("ERROR: ", e);
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
    </script>

</body>

</html>