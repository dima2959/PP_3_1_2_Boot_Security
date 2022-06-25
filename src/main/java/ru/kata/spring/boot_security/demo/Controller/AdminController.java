package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Services.AdminServices;
import ru.kata.spring.boot_security.demo.Services.RoleService;
import ru.kata.spring.boot_security.demo.Services.RoleServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminServices adminServices;
    private final RoleService roleService;

    public AdminController(AdminServices adminServices, RoleServiceImpl roleService) {
        this.adminServices = adminServices;
        this.roleService = roleService;
    }

    @GetMapping
    private String index(Model model){
        model.addAttribute("users", adminServices.getAllUsers());
        return "admin/index";
    }

    @PostMapping
    private String createUser(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.getAllRoles());
            return "admin/new";
        }

        adminServices.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    private String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @GetMapping("/{id}")
    private String viewUser(@PathVariable int id, Model model){

        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", adminServices.getUser(id));
        return "admin/view";
    }

    @PatchMapping("/{id}")
    private String updateUser(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult,Model model){

        if(!bindingResult.hasErrors())
            adminServices.updateUser(user);

        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/view";
    }

    @DeleteMapping("/{id}")
    private String deleteUser(@PathVariable int id){

        adminServices.deleteUser(id);
        return "redirect:/admin";
    }
}
