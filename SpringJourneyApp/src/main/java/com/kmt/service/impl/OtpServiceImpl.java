/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Otp;
import com.kmt.repository.OtpRepository;
import com.kmt.service.OtpService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class OtpServiceImpl implements OtpService{
    
    @Autowired
    private OtpRepository otpRepo;

    @Override
    public void addOtp(Otp o) {
        this.otpRepo.addOtp(o);
    }

    @Override
    public Otp getOtpByEmailId(int emailId) {
        return this.otpRepo.getOtpByEmailId(emailId);
    }

    @Override
    public Otp getOtpById(int id) {
        return this.otpRepo.getOtpById(id);
    }

    @Override
    public void deleteOtpById(int id) {
        this.otpRepo.deleteOtpById(id);
    }
    
    
    
}
