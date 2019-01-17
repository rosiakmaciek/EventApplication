<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie pokoju</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
    <br>
    <form:form modelAttribute="room" method="post">

        Ilość osób w pokoju: <br> <form:input path="capacity"/><br><br>
        Ilość pokojów: <br> <input type="text" name="numberOfRooms"><br><br>
        <input type="submit" value="Dodaj" class="button"/>

    </form:form>

    <a href="/admin/panel">Wróć do panelu admina</a>
</div>
</body>
</html>
