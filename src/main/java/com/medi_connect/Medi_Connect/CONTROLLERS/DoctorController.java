package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.DoctorDto;
import com.medi_connect.Medi_Connect.DTO.DoctorResponseDto;
import com.medi_connect.Medi_Connect.DTO.JwtUserContext;
import com.medi_connect.Medi_Connect.Entity.Appointment;
import com.medi_connect.Medi_Connect.Entity.Doctor;
import com.medi_connect.Medi_Connect.Role;
import com.medi_connect.Medi_Connect.SERVICE.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS}
)
@RestController
@RequestMapping("/doctors")

public class DoctorController {


//    @Autowired
//    private JwtUserContext userContext;



}
