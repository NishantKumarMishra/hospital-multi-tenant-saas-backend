package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.DTO.DoctorDto;
import com.medi_connect.Medi_Connect.DTO.DoctorResponseDto;
import com.medi_connect.Medi_Connect.Entity.Doctor;
import com.medi_connect.Medi_Connect.Entity.DoctorHospital;
import com.medi_connect.Medi_Connect.Entity.Hospital;
import com.medi_connect.Medi_Connect.Exception.DoctorAlreadyExistsException;
import com.medi_connect.Medi_Connect.Exception.NotFoundException;
import com.medi_connect.Medi_Connect.REPOSITORIES.DoctorHospitalRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.DoctorProjection;
import com.medi_connect.Medi_Connect.REPOSITORIES.DoctorRepository;
import com.medi_connect.Medi_Connect.REPOSITORIES.Hospital_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorHospitalRepository doctorHospitalRepository;

    @Autowired
    Hospital_Repository hospital_repository;


    public DoctorResponseDto register(DoctorDto doctorDto , Long hid){


        if (doctorHospitalRepository.existsByMobileAndHospital(doctorDto.getPhone(),hid) ) {
            throw new DoctorAlreadyExistsException(
                    "Doctor already registered with email: " + doctorDto.getEmail()
            );
        }


        try{

            Optional<Hospital> hospital = hospital_repository.findById(hid);

            Doctor doctor = Doctor.builder()

                    .name(doctorDto.getName())
                    .specialization(doctorDto.getSpecialization())
                    .phone(doctorDto.getPhone())
                    .doctor_status(doctorDto.getStatus())
                    .email(doctorDto.getEmail())
                    .build();

            Doctor savedDoctor = doctorRepository.save(doctor);

            DoctorHospital doctorHospital = DoctorHospital.builder()
                    .doctor(doctor)
                    .hospital(hospital.get())
                    .build();

            doctorHospitalRepository.save(doctorHospital);

            return new DoctorResponseDto(
                    savedDoctor.getId(),
                    savedDoctor.getName(),
                    savedDoctor.getPhone(),
                    savedDoctor.getSpecialization(),
                    savedDoctor.getDoctor_status(),
                    savedDoctor.getEmail()
            );

        }catch (Exception e){
            throw new RuntimeException("Doctor registration failed", e);
        }


    }

//    public List<DoctorResponseDto> findAllDoctors(Long id){
//        try{
//
//            List<Doctor> doctors = doctorRepository.findAll();
//            return doctors.stream().map(d -> new DoctorResponseDto(
//                    d.getId(),
//                    d.getName(),
//                    d.getSpecialization(),
//                    d.getPhone(),
//                    d.getDoctor_status(),
//                    d.getEmail()
//            ))
//                    .toList();
//
//        }catch (Exception e){
//            throw new RuntimeException("Failed to fetch doctors", e);
//        }
//    }
//
//    public void  deleteService(Long id){
//        if(!doctorRepository.existsById(id)){
//            throw  new NotFoundException("Mismatch...", id);
//        }
//       doctorRepository.deleteById(id);
//    }

    public List<DoctorResponseDto> findAllDoctors(Long userId, Long hid, String role) {

        List<DoctorProjection> list = doctorHospitalRepository.findDoctorsByHospitalId(hid);

        return list
                .stream()
                .map(d->new DoctorResponseDto(
                     d.getId(),
                        d.getName(),
                        d.getSpecialization(),
                        d.getPhone(),
                        d.getStatus(),
                        d.getEmail()


                ))
                .toList();

    }
}
