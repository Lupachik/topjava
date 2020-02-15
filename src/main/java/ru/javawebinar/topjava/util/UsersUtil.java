package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final int USER_ID = SecurityUtil.authUserId();
    public static final int ADMIN_ID = SecurityUtil.authUserId() + 1;

    public static final List<User> USERS = Arrays.asList(
            new User(USER_ID, "User", "user@topjava.ru", "123456", Role.ROLE_USER),
            new User(ADMIN_ID, "Admin", "admin@topjava.ru", "qwerty", Role.ROLE_ADMIN)
    );
}
