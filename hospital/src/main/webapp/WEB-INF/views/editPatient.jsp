<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Patient Page</title>
</head>
<body>

<form name="patient" action="/updatePatient" method="post">
    <p>Surname</p>
    <input title="Surname" type="text" name="surname" value="${patient.surname}">
    <p>Name</p>
    <input title="Name" type="text" name="name" value="${patient.name}">
    <p>Patronymic</p>
    <input title="Patronymic" type="text" name="patronymic" value="${patient.patronymic}">
    <input type="submit" value="OK">
</form>

</body>
</html>
