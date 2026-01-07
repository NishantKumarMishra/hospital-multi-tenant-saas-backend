package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtUserContext {
    private Long userId;
    private Long hospitalId;
    private String role;


}
