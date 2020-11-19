package com.jalolov.task_311.controllers;

import com.jalolov.task_311.model.User;
import com.jalolov.task_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class UsersControllers {

    private final UserService userService;

    @Autowired
    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "users/user";
    }
}
