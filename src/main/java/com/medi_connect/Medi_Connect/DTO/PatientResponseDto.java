package com.medi_connect.Medi_Connect.DTO;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String name;
    private String age;
    private String gender;

}
