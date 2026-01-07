package com.medi_connect.Medi_Connect.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    private String email;
    private String password;
}
