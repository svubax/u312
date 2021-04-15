package com.jm.u312.controller;

import com.jm.u312.model.User;
import com.jm.u312.service.RoleService;
import com.jm.u312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String admin(@ModelAttribute("newUser") User newUser,
                        @ModelAttribute("updatedUser") User updatedUser,
                        Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "admin";
    }
    @PostMapping("/add")
    public String create(User user, @RequestParam(value = "rolesAdd", required = false) String... roles) {
        userService.addUser(user, roles);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @PatchMapping("/update")
    public String update(User user, @RequestParam(value = "rolesUpdate", required = false) String... roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }
}
