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
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <script src="/jQuery/events.js"></script>
    <script src="/jQuery/sortEventsDate.js"></script>
    <script src="/jQuery/discharge.js"></script>
    <title>Patient Info</title>
</head>
<body>
<div class="bd-example">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">New message</h4>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title1">New message</h4>
                    <h5 class="error-events"></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
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
<h1 class="ml-3 mb-4 mt-2">Patient Info</h1>
        </div>
        <div class="row justify-content-md-center">
<img src="/css/avatar_male_l.png" alt="Photo" class="ml-3 mb-4"/>
        </div>
            <div class="row justify-content-md-center">
<h2 class="mb-4 ml-3">${patient.surname} ${patient.name} ${patient.patronymic}</h2>
            </div>
        <div class="row justify-content-md-center">
<div class="col-sm-3 mb-4 pb-3 pt-3 ml-3 border border-secondary box">
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
        </div>
            <div class="row justify-content-md-center">
<div class="mb-4 ml-3">
    <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <input type="button" value="Discharge" onclick="DischargeFunction(this,${patient.id})">
    <input type="button" value="Create prescription" onclick="location.href = '/prescriptions/${patient.id}'">
    </c:if>
</div>

    </div>
<div class="mb-5">
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th style="text-align: center !important;" colspan="7">Prescriptions</th>
        </tr>
        <tr>
            <th>Type of diagnosis</th>
            <th>Title of diagnosis</th>
            <th>Procedure or medicine</th>
            <th>Period</th>
            <th>Dose</th>
            <th>Completed</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach  items="${prescriptions}" var ="prescription">
            <c:if test = "${prescription.isDone != true}">
            <tr>
                <td>${prescription.diagnosis.diagnosisType.title}</td>
                <td>${prescription.diagnosis.title}</td>
                <c:if test = "${prescription.procedure != null}">
                    <td>${prescription.procedure.title}</td>
                </c:if>
                <c:if test = "${prescription.medicine != null}">
                    <td>${prescription.medicine.title}</td>
                </c:if>
                <td>${prescription.period} weeks</td>
                <td>${prescription.dose}</td>
                <td>no</td>
                <td><input type="button" value="Complete" onclick="CompleteFunction(this,${patient.id},${prescription.id})"></td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div>
    <table class="table table-hover table-bordered events1">
        <thead>
        <tr>
            <th colspan="3" style="text-align: center !important;">Events</th>
        </tr>
        <tr>
            <th>Procedure or medicine</th>
            <th>Date and time   <input type="button" value="&#8595;" onclick="SortEventsDate(this,${patient.id},'desc')"><input type="button" value="&#8593;" onclick="SortEventsDate(this,${patient.id},'asc')"></th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${events}" var ="event">
            <tr>
                <c:if test = "${event.procedure != null}">
                <td>${event.procedure.title}</td>
                </c:if>
                <c:if test = "${event.medicine != null}">
                    <td>${event.medicine.title}</td>
                </c:if>
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
</div>
</body>
</html>
