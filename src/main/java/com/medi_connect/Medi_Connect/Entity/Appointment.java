package com.medi_connect.Medi_Connect.Entity;

import com.medi_connect.Medi_Connect.AppointmentSource;
import com.medi_connect.Medi_Connect.AppointmentStatus;
import com.medi_connect.Medi_Connect.VisitType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long token_Number;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Hospital hospital;

    private LocalDateTime appointmentTime;

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private AppointmentSource source;

    @Enumerated(EnumType.STRING)
    private VisitType visitType;
}

