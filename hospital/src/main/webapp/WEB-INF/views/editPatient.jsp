<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery.autocomplete.min.js"></script>
    <script src="/jQuery/getAllDoctors.js"></script>
    <title>Create Patient Page</title>
</head>
<body>
<style>.autocomplete-suggestions{background:#ffffff;}</style>

<h1 class="ml-3 mb-4 mt-2">Edit patient</h1>
<form id="myform" action="/updatePatient" method="post" class="col-sm-8">
    <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" value="${patient.surname}">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${patient.name}">
        <label for="patronymic">Patronymic</label>
        <input type="text" class="form-control" id="patronymic" name="patronymic" placeholder="Patronymic" value="${patient.patronymic}">
        <label for="insuranceNum">Insurance number</label>
        <input type="text" class="form-control" id="insuranceNum" name="insuranceNum" placeholder="Insurance number" value="${patient.insuranceNum}">
        <label for="doctor">Doctor surname</label>
        <input type="text" class="form-control mdb-autocomplete" id="doctor" name="doctor" placeholder="Doctor surname" value="${patient.staff.surname}">
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </div>
    <input type="submit" value="OK">
    </div>
</form>

</body>
</html>
