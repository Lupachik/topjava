package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        log.info("create {} for user {}", meal, userId);
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        log.info("get {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll for user {}", userId);
        return repository.getAll(userId);
    }

    public Collection<Meal> getAll(LocalDateTime startDateTime, LocalDateTime endDateTime,int userId) {
        log.info("getAll of dateTime ({} - {}) for user {}", startDateTime, endDateTime, userId);
        return repository.getAll(startDateTime, endDateTime, userId);
    }

    public void update(Meal meal, int userId) {
        log.info("update {} for user {}", meal, userId);
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }
}