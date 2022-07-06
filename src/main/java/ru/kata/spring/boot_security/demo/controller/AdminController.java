package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServices;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServices userServices;
    private final RoleService roleService;

    public AdminController(UserServices userServices, RoleServiceImpl roleService) {
        this.userServices = userServices;
        this.roleService = roleService;
    }

    @GetMapping
    private String index(@ModelAttribute("user") User user, Model model){

        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("authenticationUser", authenticationUser());
        model.addAttribute("users", userServices.getAllUsers());
        return "admin/index";
    }

    private User authenticationUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityDetails securityDetails = (UserSecurityDetails) authentication.getPrincipal();

        return securityDetails.getUser();
    }

}
