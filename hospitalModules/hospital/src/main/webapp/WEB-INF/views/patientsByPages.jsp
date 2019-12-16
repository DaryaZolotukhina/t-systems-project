<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Patient List</title>
    <!-- Bootstrap CSS -->
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/jQuery/jquery-dateformat.min.js"></script>
    <script src="/jQuery/sortSurname.js"></script>
</head>
<body>
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
<h1 class="ml-3 mb-3 mt-2">Patients List</h1>
    </div>
<br/>
<div class="row justify-content-md-center">
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
                    <td><c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}"><a href="/patient/delete/${patient.id}"><button>Delete</button></a></c:if></td>
                    <td><c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}"><a href="/patient/update/${patient.id}"><button>Update</button></a></c:if></td>
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
    <c:if test="${pageContext.request.isUserInRole('ROLE_DOCTOR')}">
    <a href="/patient/add"><button>Create patient</button></a>
    </c:if>
    <br>
    <br>
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Log off"/>
        <security:csrfInput/>
    </form>
</div>
</div>
</body>

</html>