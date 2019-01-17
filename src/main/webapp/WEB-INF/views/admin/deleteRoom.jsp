<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usuwanie pokojów</title>
    <title>Usuwanie użytkowników</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
    <br>
    <form:form modelAttribute="room" method="post">

        Wybierz nazwę pokoju do usunięcia: <br><br>
        <form:select path="id" class="styled-select semi-square">
            <form:options items="${rooms}" itemLabel="name" itemValue="id"/>
        </form:select><br><br>
        <input type="submit" value="Usuń" class="button"/>

    </form:form><br>

    <a href="/admin/panel">Wróć do panelu admina</a>
</div>
</body>
</html>
