package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Professional details
    private String name;
    private String phone;
    private String email;

    // Helps track specific designations within the "STAFF" role
    // e.g., "Senior Nurse", "Front Desk", "Accountant"
    private String designation;

    private boolean active = true;

    // The bridge to Authentication and Roles
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Audit fields (Highly recommended for SaaS products)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
