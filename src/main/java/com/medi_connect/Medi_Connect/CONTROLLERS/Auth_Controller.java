package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.*;
import com.medi_connect.Medi_Connect.SERVICE.Auth_Service;
import com.medi_connect.Medi_Connect.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Auth_Controller {
    @Autowired
    private Auth_Service service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        service.registerAdmin(registerRequest);
        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Admin registered successfully"
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }







}
