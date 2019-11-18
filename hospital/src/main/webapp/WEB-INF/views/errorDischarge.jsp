<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Error Page</title>
 <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="container">
<div class="jumbotron alert-danger">
    <h1>HTTP Status ${error.errCode}</h1>
    <h2> ${error.errMsg}</h2>
    <c:forEach  items="${error.prescriptionList}" var ="prescription">
    <h2>Id: ${prescription.id}, ${prescription.procedureMedicine.title}, ${prescription.period} weeks, ${prescription.dose}</h2>
    </c:forEach>
</div>
</div>
</body>
</html>
