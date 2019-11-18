$(document).ready(function() {

    $('#doctor').autocomplete({
        serviceUrl: '${pageContext.request.contextPath}/allDoctors',
        paramName: "surname",
        delimiter: ",",
        transformResult: function(response) {

            return {
                suggestions: $.map($.parseJSON(response), function(item) {

                    return { data: item.id, value: item.surname, name:item.name,
                        patronymic:item.patronymic, isDeleted:item.isDeleted, isDoctor:item.isDoctor  };
                })

            };

        }

    });

});