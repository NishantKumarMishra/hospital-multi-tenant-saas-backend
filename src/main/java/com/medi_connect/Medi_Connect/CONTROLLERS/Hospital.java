package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.DTO.HospitalResponse;
import com.medi_connect.Medi_Connect.DTO.JwtUserContext;
import com.medi_connect.Medi_Connect.SERVICE.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class Hospital {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/all")
    public ResponseEntity<List<HospitalResponse>> getAllHospital(Authentication authentication){
        JwtUserContext context = (JwtUserContext) authentication.getPrincipal();
        Long admin_id = context.getUserId();

        return ResponseEntity.ok(hospitalService.getAllHospital(admin_id));

    }

}
