/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.filters;

import com.kmt.utils.JwtUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author kieum
 */
@Component
public class JwtFilter implements Filter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request; //Chuyển từ ServletRequest sang HttpServletRequest để có thể đọc Header HTTP

        String header = httpRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) { //Kiểm tra request có gửi kèm header Authorization không.
            String token = header.substring(7); //Lấy phần token bỏ phần Bearer
            try {
                String username = JwtUtils.validateTokenAndGetUsername(token); //Kiểm tra và xác thực token
                if (username != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth); //Spring Security lưu thông tin người dùng đang đăng nhập trong suốt vòng đời của request hiện tại
                }
            } catch (Exception e) {
                System.err.println("JWT validation failed: " + e.getMessage()); 
            }
        }

        chain.doFilter(request, response);
    }
}
