package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.DoctorResponseDto;
import com.medi_connect.Medi_Connect.DTO.StaffDto;
import com.medi_connect.Medi_Connect.Doctor_Status;
import com.medi_connect.Medi_Connect.Entity.Doctor;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.REPOSITORIES.DoctorRepository;
import com.medi_connect.Medi_Connect.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class DoctorProfileStrategy implements StaffProfileStrategy{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Role getSupportedRole() {
        return Role.DOCTOR;
    }

    @Override
    public DoctorResponseDto createProfile(StaffDto staffDto, User user) {
        Doctor doctor = doctorRepository.findByUserId(user.getId()).orElseGet(()->Doctor.builder()
                .user(user)
                .email(staffDto.getEmail())
                .doctor_status(Doctor_Status.active)
                .name(staffDto.getName())
                .phone(staffDto.getPhone())
                .build());

        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .email(doctor.getEmail())
                .name(doctor.getName())
                .phone(doctor.getPhone())
                .role(Role.DOCTOR.name())
                .build();
    }
}
