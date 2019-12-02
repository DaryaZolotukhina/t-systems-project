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
</head>
<body>
<script>
function CompleteFunction(idEvent,idStaff,status) {
var str = '/changeStatus/' + idEvent + '/' + idStaff+'/'+status;
$.ajax({
type: 'GET',
url: str,
success: function(result) {
console.log(result);
$(".events1 tbody").empty();
for (i=0;i<result.length;i++) {

    if(result[i].procedure!=null)
    $('<tr>').html("<td><a href=\"/patient/" + result[i].idPatient + " \">"+result[i].surnamePatient+"</a></td><td>" + result[i].procedure+
    "</td><td>" + $.format.date(result[i].dateTimeEvent,
        "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title+
    "</td><td><button id='but1'>Complete</button></td><td><button id='but2'>Cancel</button></a></td>").appendTo('.events1');
    else
    $('<tr>').html("<td><a href=\"/patient/" + result[i].idPatient + " \">"+result[i].surnamePatient+"</a></td><td>" + result[i].medicine+
        "</td><td>" + $.format.date(result[i].dateTimeEvent,
            "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title+
        "</td><td><button id='but1'>Complete</button></td><td><button id='but2'>Cancel</button></a></td>").appendTo('.events1');
   // $(document).on("click","#but1",CompleteFunction(result[i].id,result[i].idStaff,'completed'));
  //  $(document).on("click","#but2",CompleteFunction(result[i].id,result[i].idStaff,'canceled'));
    }


}
    });
    //var p=o.parentNode.parentNode;
   // p.parentNode.removeChild(p);
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
                    <td id="td1"><a href="/patient/${event.idPatient}">${event.surnamePatient}</a></td>
                    <c:if test = "${event.procedure != null}">
                        <td id="td2">${event.procedure}</td>
                    </c:if>
                    <c:if test = "${event.medicine != null}">
                        <td id="td3">${event.medicine}</td>
                    </c:if>
                    <td id="td4">${event.dateTimeEvent}</td>
                    <td id="td5">${event.statusEvent.title}</td>
                    <td><input type="button" value="Complete" onclick="CompleteFunction(${event.id},${event.idStaff},'completed')"></td>
                    <td><input type="button" value="Cancel" onclick="CompleteFunction(${event.id},${event.idStaff},'canceled')"></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>