package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Doctor_Status;

public interface DoctorProjection {
    Long getId();
     String getName();
   String getSpecialization();
     String getPhone();
     Doctor_Status getStatus();
     String getEmail();



}
