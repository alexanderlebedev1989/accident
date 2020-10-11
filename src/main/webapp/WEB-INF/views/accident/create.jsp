<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>Создать</title>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
                <a class="nav-link" href="<c:url value='/login?logout=true'/>">${user.username}| Выйти</a>
        </ul>
    </div>
</div>
<form  action="<c:url value='/save'/>" method='POST'>
    Название: <input type='text' name='name'><br/><br/>
    Описание: <input type='text' name='text'><br/><br/>
    Адрес: <input type='text' name='address'><br/><br/>
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
    <input name="submit" type="submit" value="Сохранить"/>
</form>
</body>
</html>