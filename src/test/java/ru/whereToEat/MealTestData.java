package ru.whereToEat;

import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import java.util.Arrays;
import java.util.List;


public class MealTestData {
    public static TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingFieldsComparator();

    public static final int NOT_FOUND = 10;
    public static final int PERCHINI_ID = 100002;
    public static final int BAR_AND_GRIL_ID = 100003;
    public static final int TRI_OLENYA_ID = 100004;
    public static final int MEDALYONY_IZ_GOVYADINY_ID = 100005;


    public static final Restaurant PERCHINI = new Restaurant();
    public static final Restaurant BAR_AND_GRIL = new Restaurant();
    public static final Restaurant TRI_OLENYA = new Restaurant();

    static {
        PERCHINI.setId(PERCHINI_ID);
        BAR_AND_GRIL.setId(BAR_AND_GRIL_ID);
        TRI_OLENYA.setId(TRI_OLENYA_ID);
    }

    public static final Meal MEAL_FOR_GET = new Meal(100005,"Медальоны из говядины", 140.25f, PERCHINI);

    public static final Meal MEAL1 = new Meal(100005,"Медальоны из говядины", 140.25f, PERCHINI);
    public static final Meal MEAL2 = new Meal(100006,"Язык по милански", 170.99f, PERCHINI);
    public static final Meal MEAL3 = new Meal(100007,"Стейк Перчини", 170.99f, PERCHINI);
    public static final Meal MEAL4 = new Meal(100008,"Стейк Флан", 349.00f, BAR_AND_GRIL);
    public static final Meal MEAL5 = new Meal(100009,"Филе Миньон", 429.00f, BAR_AND_GRIL);
    public static final Meal MEAL6 = new Meal(100010,"Стейк Мачете", 325.60f, BAR_AND_GRIL);
    public static final Meal MEAL7 = new Meal(100011,"Фирменный стейк «Три оленя»", 253.20f, TRI_OLENYA);
    public static final Meal MEAL8 = new Meal(100012,"Рибай стейк из мраморной говядины Black Angus", 356.12f, TRI_OLENYA);
    public static final Meal MEAL9 = new Meal(100013,"Медальоны из телячьей вырезки с бефстроганов под сморчками на картофельном пюре", 394.53f, TRI_OLENYA);


    public static final List<Meal> MEALS = List.of(MEAL9,MEAL8, MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static final List<Meal> MEALS_PERCHINI = List.of(MEAL3, MEAL2, MEAL1);

    public static Meal getNew() {
        return new Meal("новая еда", 100f, PERCHINI);
    }

    public static Meal getUpdated() {
        return new Meal(100005,"Обновленная еда", 200f, PERCHINI);
    }
}
