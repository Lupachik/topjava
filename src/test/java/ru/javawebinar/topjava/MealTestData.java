package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(USER_MEAL_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(USER_MEAL_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(USER_MEAL_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(USER_MEAL_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(USER_MEAL_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL6 = new Meal(USER_MEAL_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL7 = new Meal(USER_MEAL_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal MEAL8 = new Meal(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 30, 9, 0), "Админ Завтрак", 510);
    public static final Meal MEAL9 = new Meal(ADMIN_MEAL_ID + 1, of(2020, Month.JANUARY, 30, 14, 0), "Админ Обед", 1000);
    public static final Meal MEAL10 = new Meal(ADMIN_MEAL_ID + 2, of(2020, Month.JANUARY, 30, 21, 0), "Админ Ужин", 500);


}
