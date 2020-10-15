package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.gvg.model.User;
import ru.gvg.service.FileService;
import ru.gvg.service.UserService;
import ru.gvg.service.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2020
 */
@Controller
public class StartController {

    @Value("${file.directory}")
    private String fileDirectory;

    private UserService userService;

    private FileService fileService;

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

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
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
                         HttpServletRequest request,
                         BindingResult result) {
        String pageName = "signup";
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords not equals!");
            return pageName;
        }
        userValidator.validate(newUser, result);
        if (!result.hasErrors()) {
            if (userService.saveUser(newUser)) {
                String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                Path path = Paths.get(rootDirectory + fileDirectory + File.separator + newUser.getId().toString());
                fileService.createDirectory(path);
                pageName = "redirect:/files";
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
