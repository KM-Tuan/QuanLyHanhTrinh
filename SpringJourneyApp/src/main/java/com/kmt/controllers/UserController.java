/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Email;
import com.kmt.pojo.Phone;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        User user = userSer.getUserById(id);
        
        userSer.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa user thành công!");

        return "redirect:/users"; // Quay về danh sách
    }

    @PostMapping("/user/add/submit")
    public String addOrUpdateUser(@ModelAttribute("user") User user, @RequestParam("emailAddress") String emailAddress, @RequestParam("phoneNumber") String phoneNumber) {

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Gán Email và Phone cho User
        Email email = new Email();
        email.setEmail(emailAddress);
        email.setUserId(user);

        Phone phone = new Phone();
        phone.setPhone(phoneNumber);
        phone.setUserId(user);

        user.setEmail(email);
        user.setPhone(phone);

        userSer.addOrUpdateUser(user);

        return "redirect:/users";
    }

}
