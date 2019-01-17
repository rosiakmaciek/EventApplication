<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin panel</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
    <h1>Panel admina</h1><br>

    <a href="/admin/addUser">Dodawanie użytkowników</a><br>
    <a href="/admin/deleteUser">Usuwanie użytkowników</a><br>
    <a href="/admin/addRoom">Dodawanie pokojów</a><br>
    <a href="/admin/deleteRoom">Usuwanie pokojów</a><br>
    <a href="/admin/raport">Stwórz raport</a><br>
    <br>
    <a href = "/">Wyloguj się</a>
</div>
</body>
</html>