package com.medi_connect.Medi_Connect.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientRequestDTO {
    private String name;
    private String phone;
    private String gender;
    private String age;
}
