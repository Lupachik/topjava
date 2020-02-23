package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get(){
        Meal meal = service.get(USER_MEAL_ID, USER_ID);
        assertThat(meal).isEqualToComparingFieldByField(MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotYour() throws Exception {
        service.get(USER_MEAL_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        service.delete(USER_MEAL_ID, USER_ID);
        service.get(USER_MEAL_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotYour() {
        service.delete(USER_MEAL_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenHalfOpen() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
        Meal updated = getMealUpdated();
        service.update(updated, USER_ID);
        assertThat(service.get(USER_MEAL_ID,USER_ID)).isEqualToComparingFieldByField(updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotYour() {
        Meal updated = getMealUpdated();
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
    }
}