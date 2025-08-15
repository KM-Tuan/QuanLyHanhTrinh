/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kieum
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers();
    User getUserByUsername(String username);
    User getUserById(int id);
    User getCurrentUser();
    String getCurrentUsername();
    void addOrUpdateUser(User user);
    void deleteUserById(int id);
    User register(Map<String, String> params, MultipartFile avatar);
    boolean authenticate(String username, String password);
}
