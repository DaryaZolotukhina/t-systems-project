<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <script src="/jQuery/events.js"></script>
    <title>Patient Info</title>
</head>
<body>
<h1 class="ml-3 mb-4 mt-2">Patient Info</h1>
<img src="/css/avatar_male_l.png" alt="Photo" class="ml-3 mb-4"/>
<h2 class="mb-4 ml-3">${patient.surname} ${patient.name} ${patient.patronymic}</h2>
<div class="col-sm-3 mb-4 pb-3 pt-3 ml-3 border border-secondary box bg-light">
    <table>
        <tr>
            <td><b>Id</b></td>
            <td>${patient.id}</td>
        </tr>
        <tr>
            <td><b>InsuranceNum</b></td>
            <td>${patient.insuranceNum}</td>
        </tr>
    </table>
</div>
<div class="mb-4 ml-3">
<a href="/dischargePatient/${patient.id}"><button>Discharge</button></a>
</div>
<div class="col-sm-8 mb-5">
    <table class="table table-hover">
        <thead>
        <tr>
            <th colspan="4">Prescriptions</th>
        </tr>
        <tr>
            <th>Procedure or medicine</th>
            <th>Period</th>
            <th>Dose</th>
            <th>Completed</th>
        </tr>
        </thead>
        <c:forEach  items="${prescriptions}" var ="prescription">
            <c:if test = "${prescription.isDone != true}">
            <tr>
                <td>${prescription.procMed.title}</td>
                <td>${prescription.period} weeks</td>
                <td>${prescription.dose}</td>
                <td>no</td>
                <td><input type="button" value="Complete" onclick="CompleteFunction(this,${patient.id},${prescription.id})"></td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div class="col-sm-8">
    <table class="table table-hover events1">
        <thead>
        <tr>
            <th colspan="3">Events</th>
        </tr>
        <tr>
            <th>Procedure or medicine</th>
            <th>Date and time</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${events}" var ="event">
            <tr>
                <td>${event.procMed.title}</td>
                <td>${event.dateTimeEvent}</td>
                <td>${event.statusEvent.title}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
<div class="mb-4 ml-3">
    <a href="/patients"><button>Back</button></a>
</div>
</body>
</html>
