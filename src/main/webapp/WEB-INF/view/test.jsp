<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: linhtran
  Date: 14/05/2017
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<jsp:useBean id="ok" scope="page" class="Service.ConfigurationService"/>--%>
    <%pageContext.setAttribute("date",new Date());%>

    numberViewPost=:${ConfigurationService.find(1).toString()}
    <jsp:useBean id="dateformate" class="Utils.DateFormatUtil"/>
    <%--${dateformate.format(pageContext.getAttribute("date"),"hh:MM:ss dd-MM-yyyy")}--%>
</body>
</html>
