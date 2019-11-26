function CompleteFunction(o,idP,idPresc) {
    var str = '/prescription/' + idP + '/' + idPresc;
    $.ajax({
        type: 'GET',
        url: str,
        success: function(result) {
            console.log(result);
            $(".events1 tbody").empty();
            for (i=0;i<result.length;i++) {
                if(!result[i].isDeleted) {
                    if(result[i].procedure!=null)
                    $('<tr>').html("<td>" + result[i].procedure.title + "</td><td>" + $.format.date(result[i].dateTimeEvent,
                        "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title + "</td>").appendTo('.events1');
                    else
                        $('<tr>').html("<td>" + result[i].medicine.title + "</td><td>" + $.format.date(result[i].dateTimeEvent,
                            "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title + "</td>").appendTo('.events1');

                }
            }
        }
    });
    var p=o.parentNode.parentNode;
    p.parentNode.removeChild(p);
}