package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.StaffDto;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.Role;

public interface StaffProfileStrategy {
    Role getSupportedRole();
    Object createProfile(StaffDto staffDto, User user);
}
