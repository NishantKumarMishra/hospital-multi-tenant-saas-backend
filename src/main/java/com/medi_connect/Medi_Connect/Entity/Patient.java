package com.medi_connect.Medi_Connect.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient" ,
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hospital_id" , "uhid"})
        }
)
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_id" , nullable = false)
//    private Person person;

    private String name;
    private String gender;

    private String phone;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hospital_id" , nullable = false)
//    private Hospital hospital;

    private String age;


    @Column(nullable = false)
    private Long hospital_id;



    private LocalDate lastVisitDate;
    @PrePersist
    protected void onCreate() {
        lastVisitDate = LocalDate.now();
    }

}

