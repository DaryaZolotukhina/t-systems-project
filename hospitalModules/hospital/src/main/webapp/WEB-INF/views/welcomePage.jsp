<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Welcome page</title></head>
<script src="/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
<body>
<br>
<h1>Welcome!</h1>
<br>
<div class="list-group col-sm-3">
    <a href="<c:url value="/patients"/>" class="list-group-item">
        Patients list
    </a>
    <a href="<c:url value="/events/${pageContext.request.remoteUser}"/>" class="list-group-item">
        Events list
    </a>
</div>
</body>
</html>