package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void AdminsController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String tableUsers(@ModelAttribute("user") User user, Model model, Authentication authentication) {
        model.addAttribute("usersSet", userService.allUsers());
        model.addAttribute("principal", authentication);
        model.addAttribute("rolesList", roleService.getAllRoles());
        model.addAttribute("admin", userService.loadUserByUsername(authentication.getName()));
        return "admin";
    }

    @GetMapping("/addUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "/addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("listRolesNew") ArrayList<Long> roles) {
        userService.addUser(user, roleService.getRolesListById(roles));
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String changeUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/changeUser";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam("listRoles") ArrayList<Long> roles) {
        List<Role> listRole = roleService.getRolesListById(roles);
        userService.changeUser(id, user, listRole);
        return "redirect:/admin";
    }

    @DeleteMapping("/edit/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}