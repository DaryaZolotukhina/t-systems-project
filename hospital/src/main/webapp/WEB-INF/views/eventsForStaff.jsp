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
<script>
function CompleteFunction(o,idEvent,idStaff,status) {
var str = '/changeStatus/' + id + '/' + idStaff+'/'+status;
$.ajax({
type: 'GET',
url: str,
success: function(result) {
console.log(result);
$(".events1 tbody").empty();
for (i=0;i<result.length;i++) {
if(result[i].procedure!=null)
$('<tr>').html("<td>" + result[i].patient.surname + "</td><td>" + result[i].procedure.title+
    "</td><td>" + result[i].dateTimeEvent + "</td><td>" + result[i].statusEvent.title+
    "</td>").appendTo('.events1');
    else
    $('<tr>').html("<td>" + result[i].patient.surname + "</td><td>" + result[i].medicine.title+
        "</td><td>" + result[i].dateTimeEvent + "</td><td>" + result[i].statusEvent.title+
        "</td>").appendTo('.events1');
    }
    }
    });
    var p=o.parentNode.parentNode;
    p.parentNode.removeChild(p);
    }
</script>
<h1>Events List</h1>
<br/>
<div class="col-sm-8">
    <table class="table table-hover events1">
        <thead>
        <tr>
            <th>Patient</th>
            <th>Procedure or medicine</th>
            <th>Time</th>
            <th>Status</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${events}" var ="event">
                <tr>
                    <td><a href="/patient/${event.patient.id}">${event.patient.surname}</a></td>
                    <c:if test = "${event.procedure != null}">
                        <td>${event.procedure.title}</td>
                    </c:if>
                    <c:if test = "${event.medicine != null}">
                        <td>${event.medicine.title}</td>
                    </c:if>
                    <td>${event.dateTimeEvent}</td>
                    <td>${event.statusEvent.title}</td>
                    <td><input type="button" value="Complete" onclick="CompleteFunction(this,${event.id},${event.staff.id},'completed')"></td>
                    <td><input type="button" value="Cancel" onclick="CompleteFunction(this,${event.id},'canceled')"></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>