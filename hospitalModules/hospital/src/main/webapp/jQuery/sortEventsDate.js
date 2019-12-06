function SortEventsDate(o,idPat,order) {
    var str = '/sortEventsDate/' + idPat + '/' + order;
    $.ajax({
        type: 'GET',
        url: str,
        success: function(result) {
            console.log(result);
            $(".events1 tbody").empty();
            for (i=0;i<result.length;i++) {
                $('<tr>').html("<td>" + result[i].procedureMedicine.title + "</td><td>" + $.format.date(result[i].dateTimeEvent, "yyyy-MM-dd HH:mm:ss.SSS") + "</td><td>" + result[i].statusEvent.title + "</td>").appendTo('.events1');
            }
        }
    });
}