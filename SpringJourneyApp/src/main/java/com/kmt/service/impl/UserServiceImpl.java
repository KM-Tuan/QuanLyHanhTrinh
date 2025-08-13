/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kmt.pojo.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.kmt.repository.UserRepository;
import com.kmt.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kieum
 */
@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<User> getUsers() {
        return this.userRepo.getUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid username");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();
        return username != null ? userRepo.getUserByUsername(username) : null;
    }

    @Override
    public void addOrUpdateUser(User user) {
        if (user.getId() != null) { // Trường hợp update
            User existingUser = this.userRepo.getUserById(user.getId());

            // Nếu có file mới upload -> cập nhật avatar
            if (user.getFile() != null && !user.getFile().isEmpty()) {
                try {
                    Map res = cloudinary.uploader().upload(user.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    existingUser.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Cập nhật các trường khác
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setRole(user.getRole());
            existingUser.setIsActive(user.getIsActive());

            // Xử lý email
            if (user.getEmail() != null) {
                if (existingUser.getEmail() == null) {
                    existingUser.setEmail(user.getEmail());
                } else {
                    existingUser.getEmail().setEmail(user.getEmail().getEmail());
                }
                existingUser.getEmail().setUserId(existingUser);
            }

            // Xử lý phone
            if (user.getPhone() != null) {
                if (existingUser.getPhone() == null) {
                    existingUser.setPhone(user.getPhone());
                } else {
                    existingUser.getPhone().setPhone(user.getPhone().getPhone());
                }
                existingUser.getPhone().setUserId(existingUser);
            }

            this.userRepo.addOrUpdateUser(existingUser);
        } else { // Trường hợp thêm mới
            if (user.getFile() != null && !user.getFile().isEmpty()) {
                try {
                    Map res = cloudinary.uploader().upload(user.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    user.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            this.userRepo.addOrUpdateUser(user);
        }
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepo.deleteUserById(id);
    }

    @Override
    public User register(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setRole(User.UserRole.valueOf(params.get("role")));

        if (!avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.userRepo.register(u);
    }
}
