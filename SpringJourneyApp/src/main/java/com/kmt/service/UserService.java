/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author kieum
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers();
    User getUserByUsername(String username);
}
