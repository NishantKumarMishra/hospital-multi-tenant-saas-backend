package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.LoginResponse;
import com.medi_connect.Medi_Connect.DTO.OtpResponse;
import com.medi_connect.Medi_Connect.Entity.Otp;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.REPOSITORIES.OtpRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserHospitalRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserRepository;
import com.medi_connect.Medi_Connect.Role;
import com.medi_connect.Medi_Connect.Utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    private UserHospitalRepository userHospitalRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public String generateOtp(String phone){
        User user = userRepository.findByPhone(phone).orElseThrow(()->new RuntimeException("User Not found with number."));

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        Otp otpEntity = Otp.builder()
                .otp(otp)
                .used(false)
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .phone(phone)
                .build();

        otpRepository.save(otpEntity);

        System.out.println("OTP = "+ otp);

        return phone;

    }

    @Transactional
    public LoginResponse verifyOtp(String phone, String otp, String appType){
        Otp otpEntity = otpRepository.findByPhoneAndOtpAndUsedFalse(phone,otp).orElseThrow(()->new RuntimeException("Invalid OTP.."));
        if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now())) throw new RuntimeException("OTP expired..");

        otpEntity.setUsed(true);
        otpRepository.save(otpEntity);

        User user = userRepository.findByPhone(phone).get();
        Role assignedRole = (appType.equals("DOCTOR"))?Role.DOCTOR:Role.STAFF;

        if (!userHospitalRepository.existsByUserIdAndRole(user.getId(), assignedRole)){
            throw new RuntimeException("System Not found as"+appType);
        }
        String token = jwtUtil.generateToken(user.getName(),user.getId(),assignedRole,null);

        return LoginResponse.builder()
                .token(token)
                .role(assignedRole.name())
                .build();

    }

}
