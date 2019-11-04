<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
<h1>User Info</h1>
<table>
    <tr>
        <td>Id</td>
        <td>${patient.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${patient.name}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${patient.surname}</td>
    </tr>
    <tr>
        <td>Age</td>
        <td>${patient.patronymic}</td>
    </tr>
    <c:forEach  items="${prescriptions}" var ="prescription">
    <tr>
        <td>Age</td>
        <td>${prescription.id}</td>
            <td>${prescription.events}</td>
    </tr>
    </c:forEach>
    </table>
    <br>
    <a href="/patients">Back</a>
</body>
</html>