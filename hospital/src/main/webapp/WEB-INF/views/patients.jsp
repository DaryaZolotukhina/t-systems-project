<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
</head>
<body>
<h1>Patient List</h1>
<br/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
        </tr>
        <c:forEach items="${listPatients}" var ="patient">
            <c:if test = "${patient.isDeleted != true}">
            <tr>
                <td><a href="/patient/${patient.id}">${patient.id}</a></td>
                <td>${patient.surname}</td>
                <td>${patient.name}</td>
                <td>${patient.patronymic}</td>
                <td><a href="/updateDeletePatient/${patient.id}"><button>Delete</button></a></td>
                <td><a href="/update/${patient.id}"><button>Update</button></a></td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
    <br>
    <a href="/patient/add"><button>Create user</button></a>
</div>
</body>

</html>