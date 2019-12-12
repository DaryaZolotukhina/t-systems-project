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
            }
        },
        messages: {
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