/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Otp;
import com.kmt.pojo.User;
import com.kmt.service.EmailService;
import com.kmt.service.OtpService;
import com.kmt.service.UserService;
import com.kmt.utils.JwtUtils;
import jakarta.ws.rs.core.MediaType;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private EmailService emailSer;

    @Autowired
    private OtpService otpSer;

    @PostMapping(path = "/users", consumes = MediaType.MULTIPART_FORM_DATA)
    public ResponseEntity<String> register(
            @RequestParam Map<String, String> params,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {

        User u = this.userDetailsService.register(params, avatar);

        String otpCode = String.valueOf(new Random().nextInt(900000) + 100000);
        Otp otp = new Otp();
        otp.setEmailId(u.getEmail());
        otp.setOtpCode(otpCode);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpSer.addOtp(otp);

        emailSer.sendOtpEmail(u.getEmail().getId(), otpCode);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Đăng ký thành công. OTP đã gửi đến email của bạn.");
    }

    @PostMapping("users/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam("emailId") int emailId,
            @RequestParam("otp") String otpCode) {

        Otp o = otpSer.getOtpByEmailId(emailId);

        if (o.getOtpCode().equals(otpCode) && o.getExpiryTime().isAfter(LocalDateTime.now())) {
            User user = userDetailsService.getUserByEmailId(emailId);
            user.setIsActive(true);
            userDetailsService.addOrUpdateUser(user);

            otpSer.deleteOtpById(o.getId());

            return ResponseEntity.ok("Xác nhận thành công. Tài khoản đã được kích hoạt!");
        } else {
            return ResponseEntity.badRequest().body("OTP không hợp lệ hoặc đã hết hạn.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User u) {
        if (u.getUsername() == null || u.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username hoặc password không được để trống");
        }

        if (this.userDetailsService.authenticate(u.getUsername(), u.getPassword())) {
            try {
                String token = JwtUtils.generateToken(u.getUsername());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }

    @RequestMapping("/secure/profile")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<User> getProfile(Principal principal) {
        return new ResponseEntity<>(this.userDetailsService.getUserByUsername(principal.getName()), HttpStatus.OK);
    }
}
