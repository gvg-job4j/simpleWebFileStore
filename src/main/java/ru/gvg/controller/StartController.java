package ru.gvg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Valeriy Gyrievskikh
 * @since 26.03.2020
 */
@Controller
public class StartController {

    @GetMapping("/")
    public String openIndex(Model model) {
        model.addAttribute("message", "Its start page!");
        return "index";
    }
}
