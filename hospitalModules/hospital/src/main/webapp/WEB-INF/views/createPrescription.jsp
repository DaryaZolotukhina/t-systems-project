<%--
  Created by IntelliJ IDEA.
  User: Dasha
  Date: 14.11.2019
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create prescription</title>
    <script src="/jQuery/jquery-3.4.1.min.js"></script>
    <script src="/jQuery/jquery.autocomplete.min.js"></script>
   <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/> -->
    <link rel="stylesheet" href="/bootstrap/bootstrap3.3.2.min.css"/>
    <link type="text/css" href="/css/style1.css" rel="stylesheet">
     <script type="text/javascript" src="/bootstrap/bootstrap3.3.2.min.js"> </script>
    <!--  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>-->
    <script src="/css/jquery.validate.min.js"></script>
    <script src="/css/additional-methods.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css" type="text/css"/>
    <script src="/jQuery/createPrescription.js"></script>
</head>
<body>
<script>
    function AddPrescriptionFunction(id) {
        var prescription = {}
        prescription["diagnosis"]= $("#diagnosis").val();
        prescription["period"]= $("#periodSelect").val();
        prescription["daySchedule"]= $("#daySchedule").val();
        prescription["weekSchedule"]= $("#weekSchedule").val();
        prescription["diagnosisType"]= $("#diagnosisType").val();
        prescription["procedureSelect"]= $("#procedureSelect").val();
        prescription["medicineSelect"]= $("#medicineSelect").val();
        prescription["staffId"]= $("#doctorSelect").val().split(':')[0];
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url: '/createPrescription/'+id,
            data : JSON.stringify(prescription),
            dataType : 'json',
            timeout : 100000,
            success : function(response) {
                console.log('1');
                document.location.href = 'http://localhost:18080/patient/'+id, true;
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
    function GenerateSourceProcedure(o,diagnosis) {
        $('#medicineSelect').find('option').remove();
        $('#procedureSelect').find('option').remove();
        var str = '/allProcedureForDiagnosis/'+diagnosis;
        $.ajax({
            type: 'GET',
            url: str,
            success: function(result) {
                console.log(result)
                var arr=[];
                for (i=0;i<result.length;i++) {
                    arr.push(result[i].title);
                }
                console.log(arr)
                for (i=0;i<arr.length;i++) {
                    var newOption = new Option(arr[i], arr[i], true, true);
                    $('#procedureSelect').append(newOption);
                }
            }

        });
    }
    function GenerateSourceMedicine(o,diagnosis) {
        $('#medicineSelect').find('option').remove();
        $('#procedureSelect').find('option').remove();
        var str = '/allMedicineForDiagnosis/'+diagnosis;
        $.ajax({
            type: 'GET',
            url: str,
            success: function(result) {
                console.log(result)
                var arr=[];
                for (i=0;i<result.length;i++) {
                    arr.push(result[i].title);
                }
                console.log(arr)
                for (i=0;i<arr.length;i++) {
                    var newOption = new Option(arr[i],arr[i], true, true);
                    $('#medicineSelect').append(newOption);
                }
            }

        });
    }
    function GenerateSourceDoctor(o,procedure) {
        $('#doctorSelect').find('option').remove();
        if (procedure == undefined) {
            var str = '/allDoctors';
            $.ajax({
                type: 'GET',
                url: str,
                success: function (result) {
                    console.log(result)
                    var arr = [];
                    for (i = 0; i < result.length; i++) {
                        arr.push(result[i].id+': '+result[i].surname);
                    }
                    console.log(arr)
                    for (i = 0; i < arr.length; i++) {
                        var newOption = new Option(arr[i], arr[i], true, true);
                        $('#doctorSelect').append(newOption);
                    }
                }

            });
        } else
            {
            var str = /doctorsForProcedure/ + procedure;
            $.ajax({
                type: 'GET',
                url: str,
                success: function (result) {
                    console.log(result)
                    var arr = [];
                    for (i = 0; i < result.length; i++) {
                        arr.push(result[i].id+': '+result[i].surname);
                    }
                    console.log(arr)
                    for (i = 0; i < arr.length; i++) {
                        var newOption = new Option(arr[i], arr[i], true, true);
                        $('#doctorSelect').append(newOption);
                    }
                }

            });
        }
    }

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
                diagnosisType: {
                    required: true,
                    minlength: 3,
                    lettersonly: true
                },
                diagnosis: {
                    required: true,
                    minlength: 3,
                    lettersonly: true
                }
            },
            messages: {
            }
        });

        $("#diagnosisType,#diagnosis,#periodSelect,#daySchedule,#weekSchedule,#procedureSelect," +
            "#medicineSelect,#doctorSelect").on("blur", function(){
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
<div class="container">
    <div class="row justify-content-md-center">
<h1 class="ml-3 mb-4 mt-2">Create prescription</h1>
    </div>
    <div class="row justify-content-md-center">
<form id="myform" action="/createPrescription/${id}" method="post" class="col-sm-6">
    <div class="form-group">
        <label for="diagnosisType">Type of diagnosis</label>
        <label for="diagnosisType">(Choose from the available)</label>
        <input type="text" class="form-control mdb-autocomplete" id="diagnosisType" name="diagnosisType" placeholder="Type of diagnosis">
        <label for="diagnosis">Title of diagnosis</label>
        <input type="text" class="form-control" id="diagnosis" name="diagnosis" placeholder="Title of diagnosis">
        <input type="button" id="butProc" value="Choose procedure" onclick="GenerateSourceProcedure(this,$('#diagnosisType').val())">
        <input type="button" id="butMed" value="Choose medicine" onclick="GenerateSourceMedicine(this,$('#diagnosisType').val())">
        <div class="procOrMedSelect">
        <label for="procedureSelect">Procedure or medicine</label>
        </div>
        <div class="procSelect">
        <select class="form-control" name="procedureSelect" id="procedureSelect">
        </select>
        </div>
        <div class="medSelect">
        <select class="form-control" name="medicineSelect" id="medicineSelect">
        </select>
        </div>
        <input type="button" id="butDoctor" value="Choose doctor" onclick="GenerateSourceDoctor(this,$('#procedureSelect').val())">
        <div class="doctorSelect">
            <label for="doctorSelect">Doctor</label>
        </div>
        <div class="doctorSelect">
            <select class="form-control" name="doctorSelect" id="doctorSelect">
            </select>
        </div>
        <label for="but1">Repeat per</label>
        <label class="radio-inline"><input type="radio" name="optradio" checked id="but1"/>Day</label>
        <label class="radio-inline"><input type="radio" name="optradio" id="but2" />Week</label>
        <label for="periodSelect">Period</label>
       <select class="form-control" name="periodSelect" id="periodSelect">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
        </select>
        <div class="day">
        <label for="procedureMedicine">Day schedule</label>
        <select id="daySchedule" name="daySchedule" multiple="multiple">
            <option value="00:00">00:00</option>
            <option value="01:00">01:00</option>
            <option value="01:00">02:00</option>
            <option value="03:00">03:00</option>
            <option value="04:00">04:00</option>
            <option value="05:00">05:00</option>
            <option value="06:00">06:00</option>
            <option value="07:00">07:00</option>
            <option value="08:00">08:00</option>
            <option value="09:00">09:00</option>
            <option value="10:00">10:00</option>
            <option value="11:00">11:00</option>
            <option value="12:00">12:00</option>
            <option value="13:00">13:00</option>
            <option value="14:00">14:00</option>
            <option value="15:00">15:00</option>
            <option value="16:00">16:00</option>
            <option value="17:00">17:00</option>
            <option value="18:00">18:00</option>
            <option value="19:00">19:00</option>
            <option value="20:00">20:00</option>
            <option value="21:00">21:00</option>
            <option value="22:00">22:00</option>
            <option value="23:00">23:00</option>
        </select>
        </div>
        <div class="week">
        <label for="procedureMedicine">Week schedule</label>
        <select id="weekSchedule" name="weekSchedule" multiple="multiple">
            <option value="Monday">Monday</option>
            <option value="Tuesday">Tuesday</option>
            <option value="Wednesday">Wednesday</option>
            <option value="Thursday">Thursday</option>
            <option value="Friday">Friday</option>
            <option value="Saturday">Saturday</option>
            <option value="Sunday">Sunday</option>
        </select>
            </div>
        <input type="button" id="btn-ok" value="OK" onclick="AddPrescriptionFunction(${id})" disabled="true">
    </div>
</form>
    </div>
</div>
</body>
</html>
