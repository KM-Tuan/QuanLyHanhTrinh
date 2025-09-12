/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Otp;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface OtpService {
    void addOtp(Otp o);
    Otp getOtpByEmailId(int emailId);
    Otp getOtpById(int id);
    void deleteOtpById(int id);
}
