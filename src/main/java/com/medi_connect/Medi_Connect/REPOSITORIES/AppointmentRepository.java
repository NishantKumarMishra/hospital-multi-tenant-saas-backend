package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    @Query("""
    SELECT a FROM Appointment a
    WHERE a.patient.id = :patientId
    AND a.doctor.id = :doctorId
    AND a.appointmentTime >= :fromDate
    ORDER BY a.appointmentTime DESC
""")
    List<Appointment> findRecentAppointment(
            @Param("patientId") Long patientId,
            @Param("doctorId") Long doctorId,
            @Param("fromDate") LocalDateTime fromDate
    );

    List<Appointment> findByDoctorId(Long doctorId);

}

