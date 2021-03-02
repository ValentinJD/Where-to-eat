var urlMeals = "profile/meals/";

function updateFilteredTable(id) {
    $.ajax({
        type: "GET",
        url: urlMeals,
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function deleteRowMeal(id, restaurantId) {
    if (confirm('Are you sure?')) {
        $.ajax({
            url: context.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            updateTableMeals(restaurantId);
            successNoty("Deleted");
        });
    }
}

$(function () {
        makeEditable({
            ajaxUrl: "profile/meals/",
            datatableApi: $("table.display").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "description"
                    },
                    {
                        "data": "price"
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
        });
    }
);

var restaurantId;

function updateTableMeals(restaurantId) {
    this.restaurantId = restaurantId;
    $.get("profile/meals/" + restaurantId, updateTableMealsByData);
}

function updateTableMealsByData(data) {
    $('#' + restaurantId).DataTable().clear().rows.add(data).draw();
}

function saveMeal() {
    $.ajax({
        type: "POST",
        url: "profile/meals",
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTableMeals(100002);
        successNoty("Saved");
    });
}

function addMeal(restaurantId) {
    // form.find('#restaurantId').val(restaurantId);
    form.find(":input").val("");
    $("#editRow").modal();
}