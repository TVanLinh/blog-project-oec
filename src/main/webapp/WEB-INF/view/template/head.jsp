<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:include page="import_libary.jsp"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="user" content="">

    <title>Blog By Linh Tran</title>

    <!-- Bootstrap Core CSS -->
    <link href="<s:url value="public/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <%--<link href="public/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet"/>--%>

    <!-- Custom CSS -->
    <link href="<s:url value="public/bootstrap/css/blog-post.css"/>" rel="stylesheet" type="text/css"/>
     <link href="<s:url value="public/asserts/css/modal.css"/>" rel="stylesheet" type="text/css"/>
     <link href="<s:url value="public/asserts/css/helper_class.css"/>" rel="stylesheet" type="text/css"/>
     <link href="<s:url value="public/asserts/css/main.css"/>" rel="stylesheet" type="text/css"/>
     <link href="<s:url value="public/asserts/css/table-list-post.css"/>" rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]-z-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" type="text/javascript"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" type="text/javascript"></script>
    <!--[endif]-->
    <script src="<s:url value="public/jquery/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
    <script src="<s:url value="public/ckeditor/ckeditor.js"/>" type="text/javascript"></script>

</head>