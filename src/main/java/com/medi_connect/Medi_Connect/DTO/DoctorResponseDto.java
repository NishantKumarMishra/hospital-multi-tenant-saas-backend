package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Doctor_Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String specialization;
    private String phone;
    private Doctor_Status status;
    private String email;
}
