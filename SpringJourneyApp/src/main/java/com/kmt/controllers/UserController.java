/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.kmt.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kieum
 */
@Controller
public class UserController {

    @Autowired
    private UserService userSer;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", this.userSer.getUsers());
        return "users";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addOrUpdateUser";
    }

    @PostMapping("/user/add/submit")
    public String saveUser(@ModelAttribute("user") User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userSer.addOrUpdateUser(user);

        return "redirect:/users";
    }

}
