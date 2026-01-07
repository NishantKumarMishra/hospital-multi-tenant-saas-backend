package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean active = true;

    private LocalDateTime localDateTime;
}
