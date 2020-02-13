package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImplMemory implements MealDao{
    private AtomicInteger count = new AtomicInteger(0);
    private Map<Integer,Meal> meals;

    public MealDaoImplMemory() {
        meals = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Meal meal) {
        int id = count.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }
}
