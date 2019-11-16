function SortSurname(o,pageId,order) {
    var str = '/sortSurname/' + pageId + '/' + order;
    $.ajax({
        type: 'GET',
        url: str,
        success: function(result) {
            console.log(result);
            $(".patientsTb tbody").empty();
            for (i=0;i<result.length;i++) {
                if (!(result[i].isDeleted && result[i].isDischarged)) {
                    $('<tr>').html("<td>" + result[i].id + "</td><td>" + result[i].surname +
                        "</td><td>" + result[i].name + "</td><td>" + result[i].patronymic +
                        "</td><td><a href=\"/updateDeletePatient/" + result[i].id +
                        " \"><button>Delete</button></a></td><td><a href=\"/update/" + result[i].id +
                        " \"><button>Update</button></a></td>").appendTo('.patientsTb');
                }
            }
        }
    });
}