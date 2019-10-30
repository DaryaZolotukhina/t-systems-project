<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
</head>
<body>
<h1>Patient List</h1>

<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
            <th>Diagnosis</th>
        </tr>
        <c:forEach  items="${listPatients}" var ="patient">
            <tr>
                <td>${patient.id}</td>
                <td>${patient.surname}</td>
                <td>${patient.name}</td>
                <td>${patient.patronymic}</td>
                <td>${patient.diagnosis}</td>
                <td><a href="/remove/${patient.id}">Delete</a></td>
                <td><a href="/update/${patient.id}">Update</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/patient/add">Create user</a>
</div>
</body>

</html>