<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery.autocomplete.min.js"></script>
    <script src="/jQuery/getAllDoctors.js"></script>
     <title>Create Patient Page</title>
</head>
<body>
<script>
    function AddPatientFunction() {
        var patient = {}
        patient["surname"]= $("#surname").val();
        patient["name"]= $("#name").val();
        patient["patronymic"]= $("#patronymic").val();
        patient["insuranceNum"]= $("#insuranceNum").val();
        patient["staffId"]= $("#doctor").val().split(':')[0];
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url: '/patient/add',
            data : JSON.stringify(patient),
            dataType : 'json',
            timeout : 100000,
            success : function(response) {
                console.log('1');
                document.location.href = 'http://localhost:18080/patients', true;
            },
            error : function(e) {
                console.log("ERROR: ", e);
                console.log('2');
            },
            done : function() {
                console.log('3');
            }
        });
    }
</script>
<style>.autocomplete-suggestions{background:#ffffff;}</style>
<h1 class="ml-3 mb-4 mt-2">Create patient</h1>
<form id="myform" class="col-sm-8">
    <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Name">
        <label for="patronymic">Patronymic</label>
        <input type="text" class="form-control" id="patronymic" name="patronymic" placeholder="Patronymic">
        <label for="insuranceNum">Insurance number</label>
        <input type="text" class="form-control" id="insuranceNum" name="insuranceNum" placeholder="Insurance number">
        <label for="doctor">Doctor surname</label>
        <input type="text" class="form-control mdb-autocomplete" id="doctor" name="doctor" placeholder="Doctor surname">
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    </div>
    <!--<input type="submit" value="OK">-->
   <input type="button" value="OK" onclick="AddPatientFunction()">
    </div>
</form>

</body>
</html>