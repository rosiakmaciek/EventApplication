<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User panel</title>
    <link href="<c:url value="/stylesheet/css.css" />" rel="stylesheet">
</head>
<body>
<div class="logDiv">
<table id="doubletable">
    <h1>Witaj, ${username}!</h1>
    <tr>
        <td>
            <a href="/user/chooseRoom">Zarezerwuj pokój</a><br>
        </td>
        <td>
            <h3>Zaproszenia: </h3>
            <table id="invitertable">

            <c:forEach items="${myInvitations}" var="singleInvitation">
                <tr>
                    <th>
                Od: ${singleInvitation.invitedWho}<br>
                    </th>
                </tr>
                <tr>
                    <td>
                        <a href="/user/addFromInvite?id=${singleInvitation.whichRoom.id}">Skorzystaj z zaproszenia</a>
                    </td>
                </tr>
            </c:forEach>

            </table>

        </td>
    </tr>

</table>
</div>
</body>
</html>
