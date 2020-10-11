<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Обновить</title>
</head>
<body>
<div class="container">
    <div class="row">
            <a class="nav-link" href="<c:url value='/login?logout=true'/>">${user.username}| Выйти</a>
    </div>
</div>
<form action="<c:url value='save'/>" method='POST'>
    Название: <input type='text' name='name' value="${accident.name}"><br><br>
    Описание: <input type='text' name='text' value="${accident.text}"><br><br>
    Адрес: <input type='text' name='address' value="${accident.address}"><br><br>
    Тип: <select name="type.id">
    <c:forEach var="type" items="${types}">
        <option value="${type.id}">${type.name}</option>
    </c:forEach>
</select><br/><br/>
    Статьи:
    <select name="rIds" multiple>
        <c:forEach var="rule" items="${rules}" >
            <option value="${rule.id}">${rule.name}</option>
        </c:forEach>
    </select><br/><br/>
    <input type="hidden" name="id" value="${accident.id}">
    <input name="submit" type="submit" value="Сохранить" />
</form>
</body>
</html>
