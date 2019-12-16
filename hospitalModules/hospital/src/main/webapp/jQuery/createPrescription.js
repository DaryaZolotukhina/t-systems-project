$(document).ready(function() {

    $('#procedureMedicine').autocomplete({
        serviceUrl: '/allProcMed',
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

    $('#diagnosisType').autocomplete({
        serviceUrl: '/diagnosis/types',
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

$(document).ready(function() {
    $('.procSelect').hide();
});

$(document).on("click","#butProc",function() {
    $('.procSelect').show();
    $('.medSelect').hide();
});
$(document).on("click","#butMed",function() {
    $('.procSelect').hide();
    $('.medSelect').show();
});
$(document).on("click","#but1",function() {
    $('.day').show();
    $('.week').hide();
});
$(document).on("click","#but2",function() {
    $('.day').hide();
    $('.week').show();
});