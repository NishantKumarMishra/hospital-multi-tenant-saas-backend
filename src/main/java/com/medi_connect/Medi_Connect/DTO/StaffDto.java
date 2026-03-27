package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Role;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class StaffDto {

    private String name;
    private String role;
    private String email;
    private String phone;
}
