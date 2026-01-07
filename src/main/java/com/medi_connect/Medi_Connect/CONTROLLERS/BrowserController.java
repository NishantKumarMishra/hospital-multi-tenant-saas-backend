package com.medi_connect.Medi_Connect.CONTROLLERS;

import com.medi_connect.Medi_Connect.Entity.Enquiry;
import com.medi_connect.Medi_Connect.REPOSITORIES.EnquiryRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enquiry")
public class BrowserController {
    @Autowired
    private EnquiryRepository repository;


    @PostMapping("/submit")
    public ResponseEntity<?> submitEnquiry(@RequestBody Enquiry enquiry){
        return ResponseEntity.ok(repository.save(enquiry));
    }

    @GetMapping("/health")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("Good...");
    }
}
