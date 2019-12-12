<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <title>Title Login Page</title>
</head>
<body>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="row justify-content-md-center">
    <h1>Login page</h1>
    </div>

    <div class="row justify-content-md-center">
    <c:if test="${not empty param.login_error}">
  <span style="color: red; ">
    Your login attempt was not successful, try again.<br/><br/>
    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
  </span>
</c:if>
    </div>

    <div class="row justify-content-md-center">
    <form name="frm" action="<c:url value="login"/>" method="post" class="col-sm-3">
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
    </div>
</div>
</body>

</html>