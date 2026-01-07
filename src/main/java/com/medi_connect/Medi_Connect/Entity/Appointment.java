package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.AppointmentSource;
import com.medi_connect.Medi_Connect.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime appointmentTime;

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private AppointmentSource source;
}

