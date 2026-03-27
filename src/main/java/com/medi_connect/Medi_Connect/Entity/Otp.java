package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String phone;
    private String otp;
    private LocalDateTime expiresAt;
    private boolean used;
}
