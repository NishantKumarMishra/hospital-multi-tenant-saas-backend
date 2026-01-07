package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);

}

