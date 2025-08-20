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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        userSer.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa user thành công!");

        return "redirect:/users"; // Quay về danh sách
    }
    
    @PostMapping("/users/add/submit")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        try {
            userSer.addOrUpdateUser(user); // Gọi service xử lý tất cả
            return "redirect:/users";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("user", user);
            return "addOrUpdateUser"; // trang form
        }
    }

    @GetMapping("/users/add/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = userSer.getUserById(id);
        if (user == null) {
            return "redirect:/users"; // Nếu không tìm thấy user, quay về danh sách
        }
        model.addAttribute("user", user);
        return "addOrUpdateUser";
    }

}
