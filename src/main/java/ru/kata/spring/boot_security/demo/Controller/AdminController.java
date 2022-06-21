package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Services.AdminServices;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping
    private String index(Model model){
        model.addAttribute("users", adminServices.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/new")
    private String newUser(@ModelAttribute("user") User user){
        return "user/new";
    }

    @PostMapping("/")
    private String createUser(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "admin/new";

        adminServices.createUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/{id}")
    private String viewUser(@PathVariable int id, Model model){

        model.addAttribute("user", adminServices.getUser(id));
        return "admin/view";
    }

    @PatchMapping("/{id}")
    private String updateUser(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult){

        if(!bindingResult.hasErrors())
            adminServices.updateUser(user);

        return "admin/view";
    }

    @DeleteMapping("/{id}")
    private String deleteUser(@PathVariable int id){

        adminServices.deleteUser(id);
        return "redirect:/admin";
    }
}
