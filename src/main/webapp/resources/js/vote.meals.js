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
            ajaxUrl: urlMeals,
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
                        "data": "id",
                        "defaultContent": "Edit",
                        "orderable": false,
                        "render": renderMealEditBtn
                    },
                    {
                        "data": "restaurantId",
                        "defaultContent": "Delete",
                        "orderable": false,
                        "render": renderMealDeleteBtn
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
    $.get(urlMeals + restaurantId, updateTableMealsByData);
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
        updateTableMeals(restaurantId);
        successNoty("Saved");
    });
}



function addMeal(restaurantId) {
    form.find(":input").val("");
    $("#restaurantId").val(restaurantId);
    this.restaurantId = restaurantId;
    $("#editRow").modal();
}

function updateRowMeal(id, restaurantId) {
    this.restaurantId = restaurantId;
    $.get(urlMeals + "one/" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        form.find("input[name='" + "restaurantId" + "']").val(restaurantId);
        $('#editRow').modal();
    });
}

function renderMealEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRowMeal(" + row.id + ',' + row.restaurantId + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderMealDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowMeal(" + row.id + ',' + row.restaurantId + ");'><span class='fa fa-remove'></span></a>";
    }
}