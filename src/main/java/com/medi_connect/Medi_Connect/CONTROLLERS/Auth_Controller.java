package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.*;
import com.medi_connect.Medi_Connect.SERVICE.Auth_Service;
import com.medi_connect.Medi_Connect.SERVICE.OtpService;
import com.medi_connect.Medi_Connect.SERVICE.SmsService;
import com.medi_connect.Medi_Connect.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Auth_Controller {
    @Autowired
    private Auth_Service service;

    @Autowired
    private OtpService otpService;

    @Autowired
    private SmsService smsService;

    @GetMapping("send-otp")
    public ResponseEntity<?> sendOtp(){
        smsService.sendSms("7992238085","your otp is 22019");
        return ResponseEntity.ok("otp send...");
    }

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

    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOtp(@RequestBody OtpGenerateRequest request){

        return ResponseEntity.ok(otpService.generateOtp(request.getPhone()));

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<LoginResponse> verifyOtp(@RequestBody OtpVerifyRequest request , @RequestHeader(value = "X-App-Type", required = false) String appType){
        return ResponseEntity.ok(otpService.verifyOtp(request.getPhone(), request.getOtp(),appType));
    }









}
