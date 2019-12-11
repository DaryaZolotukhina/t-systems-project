<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" href="/css/styles.css" rel="stylesheet">
    <link type="text/css" href="/css/style1.css" rel="stylesheet">
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/css/validator.min.js"></script>
    <script src="/css/jquery.validate.min.js"></script>
    <script src="/css/additional-methods.min.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <script src="/jQuery/jquery.autocomplete.min.js"></script>
    <script src="/jQuery/getAllDoctors.js"></script>
    <script src="/jQuery/addPatient.js"></script>
     <title>Create Patient Page</title>
</head>
<body>
<script>
    $(function() {
        $.validator.setDefaults({
            errorClass: 'help-block',
            highlight: function(element) {
                $(element)
                    .closest('.form-group')
                    .addClass('has-error');
            },
            unhighlight: function(element) {
                $(element)
                    .closest('.form-group')
                    .removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                if (element.prop('type') === 'checkbox') {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
    $("#myform").validate({
        rules: {
            name: {
                required: true,
                minlength: 1,
                lettersonly: true
            },
            surname: {
                required: true,
                minlength: 1,
                lettersonly: true
            },
            patronymic: {
                required: true,
                minlength: 1,
                lettersonly: true
            },
            insuranceNum: {
                required: true,
                minlength: 3,
                digits: true
            },
            doctor: {
                required: true,
                minlength: 1
            },
        },
        messages: {
            surname: {
                required: 'Please enter an email address.'
            }
        }
    });

        $("#doctor,#name,#surname,#patronymic,#insuranceNum").on("blur", function(){
            if($("#myform").valid())
            {
                $("#btn-ok").prop( "disabled", false );
            }
            else
                $("#btn-ok").prop( "disabled", true );
        });
    });
</script>
<style>.autocomplete-suggestions{background:#ffffff;}</style>
<h1 class="ml-3 mb-4 mt-2">Create patient</h1>
<form id="myform" class="col-sm-8">
    <div class="form-group">
        <label for="surname">Surname</label>
        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" value="${patient.surname}">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Name">
        <label for="patronymic">Patronymic</label>
        <input type="text" class="form-control" id="patronymic" name="patronymic" placeholder="Patronymic">
        <label for="insuranceNum">Insurance number</label>
        <input type="text" class="form-control" id="insuranceNum" name="insuranceNum" placeholder="Insurance number">
        <label for="doctor">Doctor surname</label>
        <input type="text" class="form-control mdb-autocomplete" id="doctor" name="doctor" placeholder="Doctor surname">
    </div>
    <input type="button" id="btn-ok" value="OK" onclick="AddPatientFunction()" disabled="true">
</form>

</body>
</html>