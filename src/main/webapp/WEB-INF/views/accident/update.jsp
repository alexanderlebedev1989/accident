<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form action="<c:url value='update'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <td><input type='text' name='address' value="${accident.address}"></td>
            <td><input type="hidden" name="id" value="${accident.id}"></td>
        </tr>
        <tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
