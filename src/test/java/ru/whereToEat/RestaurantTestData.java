package ru.whereToEat;

import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantTestData {

    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator("menu");

    public static final int NOT_FOUND = 10;
    public static final int PERCHINI_ID = 100002;
    public static final int BAR_AND_GRIL_ID = 100003;
    public static final int TRI_OLENYA_ID = 100004;

    public static final Restaurant PERCHINI = new Restaurant(PERCHINI_ID, "Перчини Пицца & Паста", 0);
    public static final Restaurant BAR_AND_GRIL = new Restaurant(BAR_AND_GRIL_ID, "Бар & Гриль МясО", 0);
    public static final Restaurant TRI_OLENYA = new Restaurant(TRI_OLENYA_ID, "Три оленя", -2);

    public static final List<Restaurant> RESTAURANTS =
            Arrays.asList(PERCHINI, BAR_AND_GRIL, TRI_OLENYA);

    public static Restaurant getNew() {
        return new Restaurant(null, "новый Перчини", 0);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(PERCHINI_ID, "Обновленный Перчини", 0);
    }

}
