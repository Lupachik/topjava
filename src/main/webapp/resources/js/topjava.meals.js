var mealAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

// https://jquery-docs.ru/jQuery.ajax/#using-converters
// https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/60_ajax/ajax-tuning.html
// $.ajaxSetup({
//     converters:{
//         "text json": function (textValue) {
//             let jsonValue = JSON.parse(textValue);
//             $(jsonValue).each(function () {
//                 this.dateTime = this.dateTime.replace('T', ' ').substring(0, 16);
//             });
//             return jsonValue;
//         }
//     }
// });

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return date.replace('T', ' ').substring(0, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess);
            }
        }),
        updateTable: updateFilteredTable
    });
    $('#dateTime').datetimepicker({
        format:'Y-m-d H:i'
    });
    $('#startDate').datetimepicker({
        format:'Y-m-d',
        timepicker:false
    });
    $('#endDate').datetimepicker({
        format:'Y-m-d',
        timepicker:false
    });
    $('#startTime').datetimepicker({
        format:'H:i',
        datepicker:false
    });
    $('#endTime').datetimepicker({
        format:'H:i',
        datepicker:false
    });
});