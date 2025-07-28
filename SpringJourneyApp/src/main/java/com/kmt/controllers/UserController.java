/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.kmt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kieum
 */
@Controller
public class UserController {
    @Autowired
    private UserService userSer;
    
    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", this.userSer.getUsers());
        return "users";
    }
    
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
    
}
