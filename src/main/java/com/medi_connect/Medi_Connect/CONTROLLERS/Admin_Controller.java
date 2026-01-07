package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.*;
import com.medi_connect.Medi_Connect.Entity.Hospital;
import com.medi_connect.Medi_Connect.SERVICE.Admin_Service;

import com.medi_connect.Medi_Connect.SERVICE.Auth_Service;
import com.medi_connect.Medi_Connect.SERVICE.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin_Controller {

    @Autowired
    private Admin_Service admin_service;

    @Autowired
    private Auth_Service service;

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/hospital/create_hospital")
    public ResponseEntity<?> create_Hospital(@RequestBody Hospital hospital , Authentication authentication){
        JwtUserContext context = (JwtUserContext) authentication.getPrincipal();
        Long admin_id = context.getUserId();
        LoginResponse response =admin_service.create_hospital(hospital , admin_id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/select-hospital")
    public ResponseEntity<JwtResponse> selectedHospital(@RequestBody SelectHospitalRequest request , Authentication authentication) throws AccessDeniedException {
        Long hospital_id =  request.getHospitalId();
        JwtUserContext context = (JwtUserContext) authentication.getPrincipal();
        Long admin_id = context.getUserId();

        System.out.println("hospitalId:"+hospital_id);
        JwtResponse response = service.selectedHospital(hospital_id,admin_id);
        System.out.println("Jwt response hai ye....."+response.toString());
        return ResponseEntity.ok(response);

    }

    @PostMapping("/Register")
    public ResponseEntity<?> book(@RequestBody DoctorDto doctorDto , Authentication authentication) {

        JwtUserContext userContext = (JwtUserContext) authentication.getPrincipal();
        Long hid =userContext.getHospitalId();

        return ResponseEntity.ok(doctorService.register(doctorDto,hid));
    }
//    @DeleteMapping("/deleteByDoctorId/{id}")
//    public ResponseEntity<?> deleteByDoctorId(@PathVariable Long id){
//        doctorService.(id);
//        System.out.println("Called");
//        return ResponseEntity.noContent().build();
//    }


    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getDoctors(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        JwtUserContext userContext  = (JwtUserContext) authentication.getPrincipal();
        Long userId = userContext.getUserId();
        Long hid = userContext.getHospitalId();
        String role = userContext.getRole();

        return ResponseEntity.ok(doctorService.findAllDoctors(userId,hid,role));
    }

}
