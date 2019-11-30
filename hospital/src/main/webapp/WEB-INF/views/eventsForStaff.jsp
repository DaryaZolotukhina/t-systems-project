<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <script src="/jQuery/sortSurname.js"></script>
</head>
<body>
<h1>Events List</h1>
<br/>
<div class="col-sm-8">
    <table class="table table-hover patientsTb">
        <thead>
        <tr>
            <th>Patient</th>
            <th>Procedure or medicine</th>
            <th>Time</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var ="event">
                <tr>
                    <td></td>
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
</body>

</html>