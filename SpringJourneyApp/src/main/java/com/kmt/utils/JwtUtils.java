/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.utils;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;

/**
 *
 * @author kieum
 */
public class JwtUtils {
    // SECRET nên được lưu bằng biến môi trường,
    private static final String SECRET = "12345678901234567890123456789012"; //Chuỗi string dùng để ký và xác thực token 32 ký tự (AES key)
    private static final long EXPIRATION_MS = 86400000; // 1 ngày

    public static String generateToken(String username) throws Exception {
        JWSSigner signer = new MACSigner(SECRET);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username) //Chủ thể của token
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_MS)) //Hết hạn sau 1 ngày
                .issueTime(new Date()) //Thời điểm phát hành token
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256), //Header định nghĩa thuật toán dùng HS256
                claimsSet
        );

        signedJWT.sign(signer);  //Ký JWT bằng HMAC-SHA256 với khóa bí mật

        return signedJWT.serialize();
    }

    public static String validateTokenAndGetUsername(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token); //Chuyển chuỗi JWT token thành đối tượng SignedJWT để xử lý
        JWSVerifier verifier = new MACVerifier(SECRET); //So sánh token với khóa bí mật

        if (signedJWT.verify(verifier)) { 
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime(); //Kiểm tra thời gian hết hạn
            if (expiration.after(new Date())) {
                return signedJWT.getJWTClaimsSet().getSubject();
            }
        }
        return null;
    }
}
