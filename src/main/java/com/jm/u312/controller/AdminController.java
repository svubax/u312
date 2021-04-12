package com.jm.u312.controller;

import com.jm.u312.model.User;
import com.jm.u312.service.RoleService;
import com.jm.u312.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
        if (roles != null)
            user.setRoles(Arrays.stream(roles)
                    .map(roleService::getRoleByName)
                    .collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @PatchMapping("/update")
    public String update(User user,
                         @RequestParam(value = "rolesUpdate", required = false) String... roles) {
        if (roles != null)
            user.setRoles(Arrays.stream(roles)
                    .map(roleService::getRoleByName)
                    .collect(Collectors.toSet()));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
