<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title Login Page</title>
</head>
<body>
<h1>Login page</h1>

<c:if test="${not empty param.login_error}">
  <span style="color: red; ">
    Your login attempt was not successful, try again.<br/><br/>
    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
  </span>
</c:if>

<form name="frm" action="<c:url value='login'/>" method="post" class="col-sm-3">
    <div class="form-group">
        <label for="username">Username:</label>
        <input class="form-control" id="username" name="username">
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pwd" name="password">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
           value="<c:out value="${_csrf.token}"/>"/>
</form>


</body>

</html>