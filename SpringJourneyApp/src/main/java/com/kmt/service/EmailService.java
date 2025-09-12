/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Email;

/**
 *
 * @author kieum
 */
public interface EmailService {
    Email getEmailById(int id);
    void sendOtpEmail(int emailId, String otpCode);
}
