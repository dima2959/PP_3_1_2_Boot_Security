package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String index(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityDetails userDetails = (UserSecurityDetails) authentication.getPrincipal();

        model.addAttribute("authenticationUser", userDetails.getUser());
        model.addAttribute("user", userDetails.getUser());

        return "user/index";
    }

}
