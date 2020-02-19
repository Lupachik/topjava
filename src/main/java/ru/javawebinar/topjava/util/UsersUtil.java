package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final int USER_ID = 101;
    public static final int ADMIN_ID = 102;

    public static final List<User> USERS = Arrays.asList(
            new User(null, "User", "user@topjava.ru", "123456", Role.ROLE_USER),
            new User(null, "Admin", "admin@topjava.ru", "qwerty", Role.ROLE_ADMIN)
    );
}
