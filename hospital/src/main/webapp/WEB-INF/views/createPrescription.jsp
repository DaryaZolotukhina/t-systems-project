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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
    <link rel="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css" type="text/css"/>
</head>
<body>
<script>
    $(document).ready(function() {

        $('#procMed').autocomplete({
            serviceUrl: '${pageContext.request.contextPath}/allProcMed',
            paramName: "title",
            delimiter: ",",
            transformResult: function(response) {

                return {
                    suggestions: $.map($.parseJSON(response), function(item) {

                        return { value: item.title, data: item.id };
                    })

                };

            }

        });

    });
    $(document).ready(function() {
        $('#daySchedule').multiselect();
    });
    $(document).ready(function() {
        $('#weekSchedule').multiselect();
    });
    $(document).ready(function() {
        $('.week').hide();
    });
    $(document).on("click","#but1",function() {
        $('.day').show();
        $('.week').hide();
    });
    $(document).on("click","#but2",function() {
        $('.day').hide();
        $('.week').show();
    });
</script>
<style>.autocomplete-suggestions{background:#ffffff;}</style>
<h1 class="ml-3 mb-4 mt-2">Create prescription</h1>
<form id="myform" action="/createPrescription/${id}" method="post" class="col-sm-8">
    <div class="form-group">
        <label for="procMed">Procedure or medicine</label>
        <input type="text" class="form-control mdb-autocomplete" id="procMed" name="procMed" placeholder="Procedure or medicine">
        <label for="procMed">Repeat per</label>
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
        <label for="procMed">Day schedule</label>
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
        <label for="procMed">Week schedule</label>
        <select id="weekSchedule" name="weekSchedule" multiple="multiple">
            <option value="Monday">Monday</option>
            <option value="Tuesday">Tuesday</option>
            <option value="Wednesday">Wednesday</option>
            <option value="Thursday">Thursday</option>
            <option value="Friday">Friday</option>
            <option value="Saturday">Saturday</option>
            <option value="Sunday">Sunday</option>
        </select>
            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            </div>
        <input type="submit" value="OK">
    </div>
</form>
</body>
</html>
