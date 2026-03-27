package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "persons" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phone"})
    }
)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , length = 15)
    private String phone;

    private String age;

    private String gender;

}
