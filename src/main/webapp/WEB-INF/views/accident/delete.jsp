<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
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

