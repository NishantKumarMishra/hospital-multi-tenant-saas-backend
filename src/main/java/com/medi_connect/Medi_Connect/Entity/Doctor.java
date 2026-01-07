package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.Doctor_Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;




    private String name;
    private String specialization;
    private String email;
    private String phone;

    private Doctor_Status doctor_status;

}
