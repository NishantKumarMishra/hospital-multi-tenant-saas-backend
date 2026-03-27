package com.medi_connect.Medi_Connect.DTO;

import lombok.*;

@Getter @Setter @Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class OtpVerifyRequest {
    private String phone;
    private String otp;
}
