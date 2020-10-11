<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Удалить</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
                <a class="nav-link" href="<c:url value='/login?logout=true'/>">${user.username}| Выйти</a>
        </ul>
    </div>
</div>
<form action="<c:url value='delete'/>" method='POST'>
    <strong>Название:</strong><br><br>
    <c:forEach var="accident" items="${accidents}">
           <c:out value="${accident.name}"/>
        <input type="hidden" name="id" value="${accident.id}">
        <input name="submit" type="submit" value="Удалить" /><br><br>
    </c:forEach>
</form>
</body>
</html>

