package com.medi_connect.Medi_Connect.DTO;

import lombok.*;

@Getter @Setter @Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class StaffResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String JoinedAt;
    private String role;
}
