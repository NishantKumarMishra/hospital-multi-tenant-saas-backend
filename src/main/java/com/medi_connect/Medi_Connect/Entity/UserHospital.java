package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.Role;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(
        name = "user_hospital",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "hospital_id", "role"})
        },
        indexes = {
                @Index(name = "idx_user_hospital_user", columnList = "user_id"),
                @Index(name = "idx_user_hospital_hospital", columnList = "hospital_id")
        }
)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User (admin / doctor / staff)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
