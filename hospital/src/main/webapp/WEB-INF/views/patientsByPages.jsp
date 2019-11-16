<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <!-- <script src="/jQuery/sortSurname.js"></script> -->
</head>
<body>
<script>
    function SortSurname(o,pageId,order) {
        var str = '/sortSurname/' + pageId + '/' + order;
        $.ajax({
            type: 'GET',
            url: str,
            success: function(result) {
                console.log(result);
                $(".patientsTb tbody").empty();
                for (i=0;i<result.length;i++) {
                    if (!(result[i].isDischarged))
                        if (!(result[i].isDeleted))
                    {
                        $('<tr>').html("<td>" + result[i].id + "</td><td>" + result[i].surname +
                            "</td><td>" + result[i].name + "</td><td>" + result[i].patronymic +
                            "</td><td><a href=\"/updateDeletePatient/" + result[i].id +
                            " \"><button>Delete</button></a></td><td><a href=\"/update/" + result[i].id +
                            " \"><button>Update</button></a></td>").appendTo('.patientsTb');
                    }
                }
            }
        });
    }
</script>
<h1>Patient List</h1>
<br/>
<div class="col-sm-8">
    <table class="table table-hover patientsTb">
        <thead>
        <tr>
            <th>Id</th>
            <th>Surname   <input type="button" value="&#8595;" onclick="SortSurname(this,${pageId},'desc')"><input type="button" value="&#8593;" onclick="SortSurname(this,${pageId},'asc')"></th>
            <th>Name</th>
            <th>Patronymic</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPatients}" var ="patient">
            <c:if test = "${patient.isDeleted != true && patient.isDischarged != true}">
                <tr>
                    <td><a href="/patient/${patient.id}">${patient.id}</a></td>
                    <td>${patient.surname}</td>
                    <td>${patient.name}</td>
                    <td>${patient.patronymic}</td>
                    <td><a href="/updateDeletePatient/${patient.id}"><button>Delete</button></a></td>
                    <td><a href="/update/${patient.id}"><button>Update</button></a></td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="/patient/add"><button>Create user</button></a>
    <!-- Pagination links in spring mvc. -->
    <ul class="pagination pagination-sm">
        <li class="page-item"><a class="page-link" href="/init/1">1</a></li>
        <li class="page-item"><a class="page-link" href="/init/2">2</a></li>
        <li class="page-item"><a class="page-link" href="/init/3">3</a></li>
    </ul>
</div>
</body>

</html>