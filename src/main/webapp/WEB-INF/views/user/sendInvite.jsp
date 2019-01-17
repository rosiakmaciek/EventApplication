<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wysłanie zaproszenia innemu użytkownikowi</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
<h1>Wyślij zaproszenie</h1>

<form:form modelAttribute="user" method="post">

    <form:select path="id" class="styled-select semi-square">
        <form:options items="${peopleToChoose}" itemLabel="login" itemValue="id"/>
    </form:select><br><br>

    <input type="submit" value="Wyślij zaproszenie" class="button"/>

</form:form>

<a href = "/">Wyloguj się</a>
</div>
</body>
</html>
