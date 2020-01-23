package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // создаем список
        List<UserMealWithExcess> list = new ArrayList<>();
        //создаем Map, где key - день, а value - условие по каллориям
        Map<LocalDate, Boolean> map = new HashMap<>();
        // создаем Set по дням и для нахождения суммы калорий проходим сетом по листу и записываем в мапу
        Set<LocalDate> set = new HashSet<>();
        for (UserMeal meal: meals){
            set.add(meal.getDateTime().toLocalDate());
        }
        for (LocalDate localDate: set){
            int sum = 0;
            for(UserMeal meal: meals){
                if(localDate.equals(meal.getDateTime().toLocalDate())) sum += meal.getCalories();
            }
            boolean excess = sum <= caloriesPerDay;
            map.put(localDate, excess);
        }

        // запускаем цикл по коллекции для проверки условий и формирования списка
        for (UserMeal meal : meals){
            if(TimeUtil.isBetweenInclusive(meal.getDateTime().toLocalTime(), startTime, endTime))
                list.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), map.get(meal.getDateTime().toLocalDate())));
        }

        // TODO return filtered list with excess. Implement by cycles
        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
