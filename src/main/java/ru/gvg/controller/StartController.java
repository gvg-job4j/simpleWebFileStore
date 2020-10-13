package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.gvg.model.User;
import ru.gvg.service.UserService;
import ru.gvg.service.UserValidator;

import java.util.List;


/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2020
 */
@Controller
public class StartController {

    private UserService userService;

    private UserValidator userValidator;
//    private ObjectError error;

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String openLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String openSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/users")
    public String setUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "usersPage";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {
        String pageName = "login";
        User user = userService.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("id", user.getId());
            pageName = "redirect:/files";
        } else {
            if (user == null) {
                model.addAttribute("message", "User not found!");
            } else {
                model.addAttribute("message", "Wrong password!");
            }
        }
        return pageName;
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") User newUser,
                         Model model,
                         BindingResult result) {
        String pageName = "signup";
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
//            model.addAttribute("user", newUser);
            model.addAttribute("passwordError", "Passwords not equals!");
            return pageName;
        }
        userValidator.validate(newUser, result);
        if (!result.hasErrors()) {
            if (userService.saveUser(newUser)) {
                pageName = "redirect:/users";
//            } else {
//                model.addAttribute("message", "Cant create user!");
            }
        } else {
            for (FieldError error : result.getFieldErrors()) {
                if (error.getField().equals("email")) {
                    model.addAttribute("emailMessage", error.getDefaultMessage());
                }
            }
        }
        return pageName;
    }

}
