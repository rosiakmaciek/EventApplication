<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie użytkowników</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
    <form:form modelAttribute="user" method="post">
        <br>
        Imię użytkownika: <br><form:input path="firstName"/><br>
        Nazwisko użytkownika: <br><form:input path="lastName"/><br>
        Email użytkownika: <br><form:input path="email"/><br><br>
        <input type="submit" value="Dodaj" class="button"/>

    </form:form>

    <a href="/admin/panel">Wróć do panelu admina</a>
</div>
</body>
</html>
