<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Raport końcowy</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
<h1>Raport końcowy wydarzenia</h1>
<table id="users">
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Login</th>
        <th>Pokój</th>
    </tr>
<c:forEach items="${allUsers}" var="singleUser">
    <tr>
        <td>${singleUser.firstName}</td>
        <td>${singleUser.lastName}</td>
        <td>${singleUser.login}</td>
        <td>${singleUser.room.id}</td>
    </tr>
</c:forEach>
</table>
    <br>
    <a href="/admin/panel">Wróć do panelu admina</a>
</div>
</body>
</html>
