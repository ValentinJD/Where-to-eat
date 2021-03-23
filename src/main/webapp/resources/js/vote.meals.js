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

var arrRestId;
var i = -1;

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
                        "orderable": false,
                        "render": renderMealEditBtn
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
    $.get("profile/meals/one/" + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        form.find("input[name='" + "restaurantId" + "']").val(restaurantId);
        $('#editRow').modal();
    });
}

function initarrRestId() {
    $.get("profile/restaurants/", function (d) {
        arrRestId = d;
    });
}

function renderMealEditBtn(data, type, row) {
    initarrRestId();

    if (type === "display") {
      let s = i + 1;
      let id = arrRestId[s];
        return "<a onclick='updateRow(" + row.id + ',' + id + ");'><span class='btn btn-primary'></span></a>";

        /*
        * <a class="btn btn-primary"
                                   href="meals/update?mealId=<c:out value="${meal1.id}&restaurantId=${restaurant.id}"/>"
                                   class="c"><spring:message code="common.update"/></a>
        * */
    }
}

function renderMealDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowMeal(" + row.id + ");'><span class='fa fa-remove'></span></a>";

        /*
        * <a onclick="deleteRowMeal(${meal1.id}, ${restaurant.id})" class="btn btn-danger"

                                   class="c"><spring:message code="common.delete"/></a>*/
    }
}