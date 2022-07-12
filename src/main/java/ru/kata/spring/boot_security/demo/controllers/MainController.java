package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import java.security.Principal;


@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "admin/index";
    }

    @GetMapping("/admin/new")
    public String newUser(User user) {
        return "admin/new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesList") String [] roles,
                         @ModelAttribute("password") String password) {
        userService.save(user, roles, password);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PutMapping("/admin/{id}/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesList") String [] roles,
                         @ModelAttribute("password") String password) {

        userService.save(user, roles, password);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}



