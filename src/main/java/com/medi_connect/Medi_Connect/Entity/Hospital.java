package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospitals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic hospital info
    @Column(nullable = false)
    private String name;

    private String address;
    private String city;
    private String state;
    private String country;

    // SaaS related fields
    @Column(nullable = false)
    private Boolean active = true;
}
