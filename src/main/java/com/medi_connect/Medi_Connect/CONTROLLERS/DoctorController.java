package com.medi_connect.Medi_Connect.CONTROLLERS;

import org.springframework.web.bind.annotation.*;

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
