package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.JwtResponse;
import com.medi_connect.Medi_Connect.DTO.LoginRequest;
import com.medi_connect.Medi_Connect.DTO.LoginResponse;
import com.medi_connect.Medi_Connect.DTO.RegisterRequest;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserHospitalRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserRepository;
import com.medi_connect.Medi_Connect.Role;
import com.medi_connect.Medi_Connect.Utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Auth_Service {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserHospitalRepository userHospitalRepository;

    public void  registerAdmin(RegisterRequest registerRequest)
    {
        Optional<User> user = userRepository.findByPhone(registerRequest.getPhone());

        if(user.isPresent()){

        }else{
            User admin = new User();
            admin.setName(registerRequest.getName());
            admin.setEmail(registerRequest.getEmail());
            admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            admin.setLocalDateTime(LocalDateTime.now());
            admin.setActive(true);
            userRepository.save(admin);
        }


    }

    public LoginResponse login(LoginRequest request) {
        if(!userRepository.existsByEmail(request.getEmail()) ){
            throw new RuntimeException("Admin Not Found");
        }
        User user = userRepository.findByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword() )){
            throw new RuntimeException("Invalid credentials");
        }



        try{
            String token = jwtUtil.generateToken(user.getName(),user.getId(),Role.ADMIN,null);
            LoginResponse response = LoginResponse.builder()

                    .token(token)
                    .role(Role.ADMIN.name())

                    .build();
            return response;

        }catch (Exception e){
            throw new RuntimeException("Failed...",e);
        }
    }

    public JwtResponse selectedHospital(Long hospitalId , Long adminId) throws AccessDeniedException {

        boolean allowed = userHospitalRepository.existsByHospitalIdAndUserId(hospitalId,adminId);
        if(!allowed){
            throw new AccessDeniedException("Hospital Not Linked to Admin..");
        }

        String token = jwtUtil.generateToken(String.valueOf(adminId),adminId,Role.ADMIN,hospitalId);
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setRole(Role.ADMIN);
        return response;
    }
}
