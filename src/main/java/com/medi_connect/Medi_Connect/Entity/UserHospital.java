package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "user_hospital",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"hospital_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Admin user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Hospital managed by admin
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN only (future-proof)
}


