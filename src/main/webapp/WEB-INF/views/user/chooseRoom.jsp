<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wybierz pokoj</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
<h1>Witaj, ${username}!</h1>

<form:form modelAttribute="room" method="post">

    Wybierz pokój w którym chcesz zamieszkać: <br><br>
    <form:select path="id" class="styled-select semi-square">
        <form:options items="${rooms}" itemLabel="name" itemValue="id"/>
    </form:select><br><br>

    <input type="submit" value="Wybierz" class="button"/>

</form:form>
<br>
<a href = "/">Wyloguj się</a>
</div>
</body>
</html>
