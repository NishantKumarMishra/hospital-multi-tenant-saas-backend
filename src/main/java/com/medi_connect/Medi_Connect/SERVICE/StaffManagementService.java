package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.StaffDto;
import com.medi_connect.Medi_Connect.Entity.*;
import com.medi_connect.Medi_Connect.REPOSITORIES.*;
import com.medi_connect.Medi_Connect.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class StaffManagementService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorHospitalRepository doctorHospitalRepository;

    @Autowired
    Hospital_Repository hospital_repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserHospitalRepository userHospitalRepository;



    private  Map<Role, StaffProfileStrategy> strategyMap;

    // Spring automatically injects all implementations of StaffProfileStrategy into this map
    @Autowired
    public StaffManagementService(List<StaffProfileStrategy> strategies) {

        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(StaffProfileStrategy::getSupportedRole, s -> s));
    }

    @Transactional
    public Object register(StaffDto staffDto, Long hid)
    {

        User user = userRepository.findByPhone(staffDto.getPhone())
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .name(staffDto.getName())
                                .email(staffDto.getEmail())
                                .phone(staffDto.getPhone())
                                .password(passwordEncoder.encode(staffDto.getPhone()))
                                .active(true)
                                .localDateTime(LocalDateTime.now())
                                .build()
                ));

        if (userHospitalRepository.existsByUser_IdAndHospital_IdAndRole(
                user.getId(), hid, Role.valueOf(staffDto.getRole()))) {
            throw new RuntimeException("Staff already registered in this hospital");
        }

        StaffProfileStrategy strategy = strategyMap.get(Role.valueOf(staffDto.getRole()));

        if (strategy == null) {
            // Fallback for general staff if no specific strategy exists
            strategy = strategyMap.get(Role.STAFF);
        }

        Object responseDto = strategy.createProfile(staffDto, user);




        Hospital hospital = hospital_repository.findById(hid)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        userHospitalRepository.save(
                UserHospital.builder()
                        .user(user)
                        .hospital(hospital)
                        .role(Role.valueOf(staffDto.getRole()))
                        .build()
        );

        return responseDto;
    }

    public List<StaffProjection> getAllStaffMembers(Long hospitalId) {
        // Just call the optimized repository method
        return userHospitalRepository.findAllStaff(hospitalId);
    }




}
