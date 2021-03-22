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
        updateTableMeals(restID);
        successNoty("Saved");
    });
}

var restID;

function addMeal(restaurantId) {
    form.find(":input").val("");
    $("#restaurantId").val(restaurantId);
    restID = restaurantId;
    $("#editRow").modal();
}

function updateRowMeal(id, restaurantId) {
    restID = restaurantId;
    $.get("profile/meals/one/" + id , function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        form.find("input[name='" + "restaurantId" + "']").val(restaurantId);
        $('#editRow').modal();
    });
}