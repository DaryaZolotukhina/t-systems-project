<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <link type="text/css" href="/css/style1.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/css/jquery.validate.min.js"></script>
    <script src="/css/additional-methods.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery.autocomplete.min.js"></script>
    <script src="/jQuery/getAllDoctors.js"></script>
    <script src="/jQuery/updatePatient.js"></script>
    <script src="/jQuery/validate.js"></script>
    <title>Edit Patient Page</title>
</head>
<body>
<style>.autocomplete-suggestions{background:#ffffff;}</style>
<div class="container">
    <div class="row justify-content-md-end">
        <nav class="col-sm-5 navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd; border:1px solid #B0C4DE;">
            <a class="navbar-brand">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/welcome">Home page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/patients">Patients list</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login.jsp">Login page</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="row justify-content-md-center">
<h1 class="ml-3 mb-4 mt-2">Edit patient</h1>
    </div>
    <div class="row justify-content-md-center">
<form id="myform" class="col-sm-6">
    <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" value="${patient.surname}">
        <br>
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${patient.name}">
        <br>
        <label for="patronymic">Patronymic</label>
        <input type="text" class="form-control" id="patronymic" name="patronymic" placeholder="Patronymic" value="${patient.patronymic}">
        <br>
        <label for="insuranceNum">Insurance number</label>
        <input type="text" class="form-control" id="insuranceNum" name="insuranceNum" placeholder="Insurance number" value="${patient.insuranceNum}">
        <br>
        <label for="doctor">Doctor surname</label>
        <input type="text" class="form-control mdb-autocomplete" id="doctor" name="doctor" placeholder="Doctor surname" value="${patient.staff.id}: ${patient.staff.surname}">
    </div>
    <input type="button" id="btn-ok" value="OK" onclick="UpdatePatientFunction(${patient.id})" disabled="true">
</form>
</div>
</div>
</body>
</html>
