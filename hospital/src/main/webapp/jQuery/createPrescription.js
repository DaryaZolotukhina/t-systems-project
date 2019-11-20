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