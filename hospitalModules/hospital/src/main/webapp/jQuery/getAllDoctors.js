$(document).ready(function() {

    $('#doctor').autocomplete({
        serviceUrl: '/allDoctors',
        paramName: "surname",
        delimiter: ",",
        transformResult: function(response) {

            return {
                suggestions: $.map($.parseJSON(response), function(item) {

                    return { value: item.id+': '+item.surname };
                })

            };

        }

    });

});