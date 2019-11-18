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
                    $('<tr>').html("<td>" + result[i].procedureMedicine.title + "</td><td>" + $.format.date(result[i].dateTimeEvent, "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title + "</td>").appendTo('.events1');
                }
            }
        }
    });
    var p=o.parentNode.parentNode;
    p.parentNode.removeChild(p);
}