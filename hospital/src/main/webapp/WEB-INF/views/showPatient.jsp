<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <title>Patient Info</title>
</head>
<body>
<h1>Patient Info</h1>
<img src="/css/avatar_male_l.png" alt="Photo" />
<h2>${patient.surname} ${patient.name} ${patient.patronymic}</h2>
<div class="info">
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
<div class="tables">
<table border="1" class="prescEvents">
    <tr>
        <th colspan="4">Prescriptions</th>
    </tr>
<tr>
    <th>Procedure or medicine</th>
    <th>Period</th>
    <th>Dose</th>
    <th>Completed</th>
</tr>
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
<div class="tables">
<table border="1" class="prescEvents">
    <tr>
        <th colspan="3">Events</th>
    </tr>
<tr>
    <th>Procedure or medicine</th>
    <th>Date and time</th>
    <th>Status</th>
</tr>
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