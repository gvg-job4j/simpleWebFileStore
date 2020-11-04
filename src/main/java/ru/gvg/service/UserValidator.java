package ru.gvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gvg.model.User;

/**
 * @author Valeriy Gyrievskikh
 * @since 10.09.2020
 */
@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserDAO(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findUserByEmail((user.getEmail())) != null) {
            errors.rejectValue("email", "", "Already in use!");
        }

    }
}
