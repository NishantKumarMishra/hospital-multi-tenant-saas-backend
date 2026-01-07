package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtResponse {

    private String token;
    private Role role;
}
