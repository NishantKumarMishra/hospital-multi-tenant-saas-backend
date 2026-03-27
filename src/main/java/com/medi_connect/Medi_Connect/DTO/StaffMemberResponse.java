package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StaffMemberResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Role role;
    private String specialization; // Null for non-doctors
    private String designation;    // Null for doctors
    private boolean active;
}