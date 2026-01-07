package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "doctor_hospital",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_id", "hospital_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Doctor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // Doctor status per hospital
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Doctor_Status status;
}
