/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Email;
import com.kmt.repository.EmailRepository;
import com.kmt.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailSer;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Email getEmailById(int id) {
        return emailSer.getEmailById(id);
    }

    @Override
    public void sendOtpEmail(int emailId, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kieuminhtuan2112003@gmail.com");
        message.setTo(this.getEmailById(emailId).getEmail());
        message.setSubject("[THÔNG BÁO] - MÃ OTP XÁC NHẬN ĐĂNG KÝ TÀI KHOẢN KMT-TECH");
        message.setText("Mã OTP của bạn là: " + otpCode + ". OTP có hiệu lực trong 5 phút.\nVui lòng không chia sẻ mã này cho bất kỳ ai!");
        mailSender.send(message);
    }

}
