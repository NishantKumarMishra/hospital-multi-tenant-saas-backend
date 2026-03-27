package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.StaffDto;
import com.medi_connect.Medi_Connect.DTO.StaffResponse;
import com.medi_connect.Medi_Connect.Entity.Staff;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.REPOSITORIES.StaffRepository;
import com.medi_connect.Medi_Connect.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneralStaffProfileStrategy implements StaffProfileStrategy{

    @Autowired
    StaffRepository staffRepository;



    @Override
    public Role getSupportedRole() {
        return Role.STAFF;
    }

    @Override
    public StaffResponse createProfile(StaffDto staffDto, User user) {
        Staff staff = staffRepository.findByUserId(user.getId()).orElseGet(()->staffRepository.save(Staff.builder()
                        .phone(staffDto.getPhone())
                        .active(true)
                        .email(staffDto.getEmail())
                        .name(staffDto.getName())
                        .user(user)
                        .designation(Role.STAFF.name())

                .build()));
        return StaffResponse.builder()
                .id(staff.getId())
                .JoinedAt(staff.getCreatedAt().toString())
                .email(staff.getEmail())
                .role(staff.getDesignation())
                .name(staff.getName())
                .phone(staff.getPhone())

                .build();
    }
}
