package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.LoginResponse;
import com.medi_connect.Medi_Connect.Entity.Hospital;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.Entity.UserHospital;
import com.medi_connect.Medi_Connect.REPOSITORIES.Hospital_Repository;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserHospitalRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserRepository;
import com.medi_connect.Medi_Connect.Role;
import com.medi_connect.Medi_Connect.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Admin_Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Hospital_Repository hospital_repository;

    @Autowired
    private UserHospitalRepository userHospitalRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse create_hospital(Hospital hospitalRequest ,  Long admin_Id){

        User admin = userRepository.findById(admin_Id)
                .orElseThrow(()->new RuntimeException("Admin not found"));

       try{

           Hospital hospital = Hospital.builder()
                   .active(hospitalRequest.getActive())
                   .address(hospitalRequest.getAddress())
                   .city(hospitalRequest.getCity())
                   .country(hospitalRequest.getCountry())
                   .name(hospitalRequest.getName())
                   .state(hospitalRequest.getState())
                   .build();

           hospital_repository.save(hospital);

           // User - Hospital Mapping

           UserHospital userHospitalMapping = UserHospital.builder()
                   .user(admin)
                   .hospital(hospital)
                   .role(Role.ADMIN)
                   .build();

           userHospitalRepository.save(userHospitalMapping);

           String token = jwtUtil.generateToken(admin.getName(),admin.getId(),userHospitalMapping.getRole(),hospital.getId());

           return new LoginResponse(token,Role.ADMIN.name());



       }catch (Exception e){
           throw new RuntimeException("Failed...",e);

       }

    }
}
