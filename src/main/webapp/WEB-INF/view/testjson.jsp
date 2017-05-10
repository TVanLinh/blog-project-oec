<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <spring:url value="public/jquery/jquery-3.2.1.min.js" var="jqueryJs" />
    <script src="${jqueryJs}"></script>

</head>
<body>
    <button id="like">click</button>

    <button id="feedback">click</button>

    <%--<script type="text/javascript">--%>
        <%--jQuery(document).ready(function($) {--%>
            <%--$("#like").on("click",function(event) {--%>
                <%--($(this).html("<p>Goodbye self</p>"));--%>
                <%--window.alert("ok men");--%>
                <%--event.preventDefault();--%>
                <%--enableSearchButton(true)--%>
                <%--searchViaAjax();--%>
            <%--});--%>

        <%--});--%>

        <%--function searchViaAjax() {--%>

            <%--var search = {};--%>
            <%--search["msg"] = "ok";--%>
            <%--search["code"] = "4444";--%>

            <%--$.ajax({--%>
                <%--type : "POST",--%>
                <%--contentType : "application/json",--%>
                <%--url : "/like",--%>
                <%--data : JSON.stringify(search),--%>
                <%--dataType : 'json',--%>
                <%--timeout : 1000,--%>
                <%--success : function(data) {--%>
                    <%--console.log("SUCCESS: ", data);--%>
                    <%--display(data);--%>
                <%--},--%>
                <%--error : function(e) {--%>
                    <%--console.log("ERROR: ", e);--%>
                    <%--display(e);--%>
                <%--},--%>
                <%--done : function(e) {--%>
                    <%--console.log("DONE");--%>
                    <%--enableSearchButton(true);--%>
                <%--}--%>
            <%--});--%>

        <%--}--%>

        <%--function enableSearchButton(flag) {--%>
            <%--$("#like").prop("disabled", flag);--%>
        <%--}--%>

        <%--function display(data) {--%>
            <%--var json = "<h4>Ajax Response</h4><pre>"--%>
                <%--+ JSON.stringify(data, null, 4) + "</pre>";--%>
            <%--$('#like').html(json);--%>
        <%--}--%>
    <%--</script>--%>

    <spring:url value="public/asserts/js/main.js" var="main" />
    <script src="${main}"></script>
</body>
</html>
