<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
    <!-- Bootstrap CSS -->
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <script src="/jQuery/sortSurname.js"></script>
    <script src="/jQuery/discharge.js"></script>
</head>
<body>
<div class="bd-example">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Error!</h4>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title1">${errMsq}</h4>
                    <c:forEach items="${prescList}" var ="presc">
                    <h5 class="error-events">Id: ${presc.id} Patient: ${presc.patient.surname}</h5>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
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
    <!-- Pagination links in spring mvc. -->
    <ul class="pagination pagination-sm">
        <li class="page-item"><a class="page-link" href="/init/1">1</a></li>
        <li class="page-item"><a class="page-link" href="/init/2">2</a></li>
        <li class="page-item"><a class="page-link" href="/init/3">3</a></li>
    </ul>
    <a href="/patient/add"><button>Create patient</button></a>
    <br>
    <br>
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Log off"/>
        <security:csrfInput/>
    </form>
</div>
</body>

</html>
