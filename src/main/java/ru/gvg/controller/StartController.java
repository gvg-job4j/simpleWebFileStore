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

    @GetMapping("/")
    public String openIndex(Model model) {
        return "login";
    }

    @GetMapping("/login")
    public String openLogin(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String openSignup(Model model) {
        return "signup";
    }

    @GetMapping("/users")
    public String setUsers(Model model) {
        model.addAttribute("users", userDAO.getUsers());
        return "usersPage";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password) {
        User user = userDAO.getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/users";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password) {
        User user = userDAO.getUser(email);
        if (user == null) {
            user = new User(name, email, password);
            if (userDAO.createUser(user)) {
                return "redirect:/users";
            }
        }
        return "redirect:/";
    }

}
