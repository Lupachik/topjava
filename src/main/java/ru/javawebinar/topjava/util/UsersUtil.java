package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("Sergei", "ser@topjava.com", "123456"),
            new User("Ivan", "iv@topjava.com", "qwerty"),
            new User("Alexei", "al@topjava.com", "a123456"),
            new User("Vladimir", "Vl@topjava.com", "Vl123456")
    );
}
