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
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() != null) {
            // Cập nhật user cũ
            User existingUser = userSer.getUserById(user.getId());

            // Cập nhật email nếu có
            if (existingUser.getEmail() != null && user.getEmail() != null) {
                existingUser.getEmail().setEmail(user.getEmail().getEmail());
                user.setEmail(existingUser.getEmail());
            }

            // Cập nhật phone nếu có
            if (existingUser.getPhone() != null && user.getPhone() != null) {
                existingUser.getPhone().setPhone(user.getPhone().getPhone());
                user.setPhone(existingUser.getPhone());
            }

            // Giữ lại mật khẩu và username cũ nếu không đổi
            user.setPassword(existingUser.getPassword());
            user.setUsername(existingUser.getUsername());
        } else {
            // Thêm mới user: mã hóa mật khẩu
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        // Thiết lập quan hệ 2 chiều với Email và Phone
        if (user.getEmail() != null) {
            user.getEmail().setUserId(user);
        }
        if (user.getPhone() != null) {
            user.getPhone().setUserId(user);
        }

        // Lưu hoặc cập nhật user
        userSer.addOrUpdateUser(user);

        return "redirect:/users";
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
