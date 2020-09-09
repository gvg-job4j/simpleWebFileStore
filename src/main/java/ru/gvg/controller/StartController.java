package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gvg.dao.UserDAO;
import ru.gvg.model.User;


/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2020
 */
@Controller
public class StartController {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String openLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String openSignup() {
        return "signup";
    }

//    @GetMapping("/index")
//    public String openIndex(){
//        return "index";
//    }

    @GetMapping("/users")
    public String setUsers(Model model) {
        model.addAttribute("users", userDAO.getUsers());
        return "usersPage";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {
        String pageName = "login";
        User user = userDAO.getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            pageName = "redirect:/users";
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
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {
        String pageName = "signup";
        User user = userDAO.getUser(email);
        if (user == null) {
            user = new User(name, email, password);
            if (userDAO.createUser(user)) {
                pageName = "redirect:/users";
            } else {
                model.addAttribute("message", "Cant create user!");
            }
        } else {
            model.addAttribute("message", "User found, please login!");
        }
        return pageName;
    }
}
