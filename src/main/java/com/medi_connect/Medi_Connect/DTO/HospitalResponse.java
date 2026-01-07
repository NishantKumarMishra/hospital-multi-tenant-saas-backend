package com.medi_connect.Medi_Connect.DTO;

import com.medi_connect.Medi_Connect.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class HospitalResponse {
    private Long id;
    private String name;
    private String city;
    private String role;

}
