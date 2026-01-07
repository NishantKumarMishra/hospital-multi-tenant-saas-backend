package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.AppointmentStatus;
import com.medi_connect.Medi_Connect.Entity.Appointment;
import com.medi_connect.Medi_Connect.REPOSITORIES.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    public Appointment book(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.BOOKED);
        return repo.save(appointment);
    }
}

