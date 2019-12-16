function UpdatePatientFunction(id) {
    var patient = {}
    patient["surname"]= $("#surname").val();
    patient["name"]= $("#name").val();
    patient["patronymic"]= $("#patronymic").val();
    patient["insuranceNum"]= $("#insuranceNum").val();
    patient["staffId"]= $("#doctor").val().split(':')[0];
    patient["id"]= id;
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url: '/patient/update/'+id,
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