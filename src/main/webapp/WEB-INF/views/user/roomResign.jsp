<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pokój został wybrany</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
<h1>Pokój został wybrany.</h1><br>

<a href = "/user/sendInvite">Wyślij zaproszenie do swojego pokoju</a><br>
<a href = "/user/roomResign">Rezygnacja i ponowny wybór pokoju</a><br><br>
<a href = "/">Wyloguj się</a>
</div>
</body>
</html>
