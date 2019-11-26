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
    <input type="button" value="Discharge" onclick="DischargeFunction(this,${patient.id})">
    <input type="button" value="Create prescription" onclick="location.href = '/createPrescription/${patient.id}'">
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
<div class="col-sm-8">
    <table class="table table-hover events1">
        <thead>
        <tr>
            <th colspan="3">Events</th>
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
                <c:if test = "${prescription.medicine != null}">
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
</body>
</html>
