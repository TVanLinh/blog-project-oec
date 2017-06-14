<%@ include file="../components/import_libary.jsp" %>
<c:if test="${param.page != null}">
    <c:set var="paramPage" value="${param.page}" scope="page"/>
</c:if>

<c:if test="${param.page == null}">
    <c:set var="paramPage" value="1" scope="request"/>
</c:if>

<s:url value="${param.target}" var="action"/>

<select name="number" class="form-control" id="numberView" onclick="myFunction('${action}',${param.search})">

    <c:if test="${param.number != 10 && param.number != 15 && param.number != 30 && param.number != 50 && param.number != 100}">
        <option value="10" selected>10</option>
        <option value="15">15</option>
        <option value="30">30</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </c:if>
    <c:if test="${param.number == 10}">
        <option value="10" selected>10</option>
        <option value="15">15</option>
        <option value="30">30</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </c:if>
    <c:if test="${param.number == 15}">
        <option value="10">10</option>
        <option value="15" selected>15</option>
        <option value="30">30</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </c:if>
    <c:if test="${param.number == 30}">
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="30" selected>30</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </c:if>
    <c:if test="${param.number == 50}">
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="30">30</option>
        <option value="50" selected>50</option>
        <option value="100">100</option>
    </c:if>
    <c:if test="${param.number == 100}">
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="30">30</option>
        <option value="50">50</option>
        <option value="100" selected>100</option>
    </c:if>
</select>

<script>
    function myFunction(pageTarget, query) {
        var x = document.getElementById("numberView").value;
        if (query === null || query === '') {
            window.location.href = pageTarget + "?number=" + x;
        } else {
            window.location.href = pageTarget + "?number=" + x + "&query_search=" + query;
        }

    }
</script>
