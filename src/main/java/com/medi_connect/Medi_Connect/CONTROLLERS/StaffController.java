package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.*;
import com.medi_connect.Medi_Connect.SERVICE.Patient_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private Patient_Services services;

    @PostMapping("/create_patient")
    public ResponseEntity<?> create_patient(@RequestBody PatientRequestDTO requestDTO , Authentication authentication){

        JwtUserContext context = (JwtUserContext) authentication.getPrincipal();

        Long hid = context.getHospitalId();
        if (hid==null)throw new RuntimeException("Please Select Hospital...");

        return ResponseEntity.ok(services.createPatient(requestDTO,hid));

    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDtoStaff>> getHospitalContextDoctors(Authentication authentication) {

        JwtUserContext context = (JwtUserContext)authentication.getPrincipal();
        Long hid = context.getHospitalId();

        return ResponseEntity.ok(services.getDoctors(hid));

    }

    @PostMapping("/book-appointment")
    public ResponseEntity<AppointmentResponseDTO> book_appointment(@RequestBody AppointmentDTO appointmentDTO, Authentication authentication){
        JwtUserContext context = (JwtUserContext) authentication.getPrincipal();
        Long hid = context.getHospitalId();

        return ResponseEntity.ok(services.bookAppointment(appointmentDTO,hid));

    }
}
