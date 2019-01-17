<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Witaj na stronie głównej aplikacji eventowej</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
    <h1>Logowanie</h1>

    <form:form modelAttribute="user" method="post">

        Podaj login:<br>
        <form:input path="login"/><br>
        Podaj hasło:<br>
        <form:password path="password"/><br>
        <form:errors path="*" cssClass="errors"/><br>
        <input type="submit" value="Wyslij" class="button"/>

    </form:form>
</div>
</body>
</html>
