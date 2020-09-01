package ru.gvg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gvg.dao.UserDAO;


/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2020
 */
@Controller
public class StartController {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/")
    public String openIndex(Model model) {
        model.addAttribute("message", "Database connected!");
        return "index";
    }

    @GetMapping("/users")
    public String setUsers(Model model) {
        model.addAttribute("users", userDAO.getUsers());
        return "usersPage";
    }
}
