package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.HospitalResponse;
import com.medi_connect.Medi_Connect.REPOSITORIES.UserHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private  UserHospitalRepository userHospitalRepository;

    public List<HospitalResponse> getAllHospital(Long userId) {

        return userHospitalRepository
                .findHospitalsByUserId(userId)
                .stream()
                .map(p -> new HospitalResponse(
                        p.getHospitalId(),
                        p.getName(),
                        p.getCity(),
                        p.getRole()
                ))
                .toList();
    }
}
