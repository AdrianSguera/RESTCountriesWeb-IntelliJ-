<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>RestCountries</h1>
<ul>
    <c:forEach items="${regions}" var="region">
        <li><a href="${pageContext.request.contextPath}/?region=${region}">${region}</a></li>
    </c:forEach>
</ul>
<hr>
<c:if test="${not empty countries}">
    <div>
        <c:forEach items="${countries}" var="country">
            <img src="${country.flag}" alt="">
        </c:forEach>
    </div>
</c:if>
</body>
</html>
