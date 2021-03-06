function DischargeFunction(o,idP) {
    var str = '/dischargePatient/' + idP;
    $.ajax({
        type: 'GET',
        url: str,
        success: function(result) {
            console.log(result);
            if (typeof result.prescriptionList == 'undefined')
            {
                console.log(1);
                document.location.href = 'http://localhost:8080/patients', true;
            }
            else {
                var prescriptions='';
                for (i=0;i<result.prescriptionList.length;i++) {
                    prescriptions=prescriptions+"Id: "+result.prescriptionList[i].id+', Period: '+result.prescriptionList[i].period+" weeks, Dose: "+result.prescriptionList[i].dose+";";
                }
                $('#myModal').modal('show');
                var modal = $('#myModal')
                modal.find('.modal-title').text('HTTP Status'+result.errCode)
                modal.find('.modal-title1').text(result.errMsg)
                modal.find('.error-events').text(prescriptions)
            }
        }
    });
}