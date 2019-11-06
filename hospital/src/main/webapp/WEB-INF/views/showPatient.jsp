<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
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
    <tr>
        <td>${prescription.procMed.title}</td>
            <td>${prescription.period} weeks</td>
            <td>${prescription.dose}</td>
        <c:choose>
            <c:when test="${prescription.isDone == true}">
                <td>yes</td>
            </c:when>
            <c:when test="${prescription.isDone != true}">
                <td>no</td>
            </c:when>
        </c:choose>
        <c:if test = "${prescription.isDone != true}">
            <td><a href="/prescription/${patient.id}/${prescription.id}"><button>Complete</button></a></td>
        </c:if>
        </tr>
    </c:forEach>
</table>
</div>
<div class="col-sm-8">
    <table class="table table-hover">
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
    <c:forEach  items="${events}" var ="event">
        <tr>
            <td>${event.procMed.title}</td>
            <td>${event.dateTimeEvent}</td>
            <td>${event.statusEvent.title}</td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
<div style="float: left;
clear: both;">
    <br>
    <a href="/patients"><button>Back</button></a>
</div>
</body>
</html>
