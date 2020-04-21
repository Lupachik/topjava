package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;

//https://mkyong.com/spring-mvc/spring-mvc-form-handling-example/

@Component
public class UserDuplEmailValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserTo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserTo userTo = (UserTo) o;

        if(userService.getByEmail(userTo.getEmail()) != null){
            errors.rejectValue("email", "user.duplemail");
        }

    }
}
