/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.User;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface UserRepository {
    List<User> getUsers();
    User getUserByUsername(String username);
    User getUserById(int id);
    void addOrUpdateUser(User user);
    void deleteUserById(int id);
    User register(User u);
}
