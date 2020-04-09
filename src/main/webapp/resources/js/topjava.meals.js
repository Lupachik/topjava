var ajaxUrl = "ajax/profile/meals/" ;
var datatableApi;

// $(document).ready(function () {
$(function () {
    makeEditable({
            url: ajaxUrl,
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
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByDate);
}

function clearFilter(){
    $("#filter")[0].reset();
    $.get(mealUrl, updateTableByDate)
}

