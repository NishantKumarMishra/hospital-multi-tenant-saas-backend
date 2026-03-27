package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Role;

public interface StaffProjection {
    Long getId();
    String getName();
    String getPhone();
    String getEmail();
    String getSpecialization();
    String getStatus(); // This must match the alias 'status' in your query
    Role getRole();
}
