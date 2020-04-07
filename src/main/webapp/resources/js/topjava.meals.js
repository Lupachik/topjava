var mealUrl = "ajax/profile/meals/" ;

// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: mealUrl,
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            })
        }
    );
});

function updateFilteredTable(){
    $.ajax({
        type: "GET",
        url: mealUrl + "filter",
        data: $("#filter").serialize()
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function clearFilter(){
    $("#filter")[0].reset();
    updateTable()
}

