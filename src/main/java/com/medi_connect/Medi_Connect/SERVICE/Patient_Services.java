package com.medi_connect.Medi_Connect.SERVICE;

import com.medi_connect.Medi_Connect.AppointmentSource;
import com.medi_connect.Medi_Connect.AppointmentStatus;
import com.medi_connect.Medi_Connect.DTO.*;
import com.medi_connect.Medi_Connect.Entity.*;
import com.medi_connect.Medi_Connect.REPOSITORIES.*;
import com.medi_connect.Medi_Connect.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Patient_Services {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private Hospital_Repository hospital_repository;

    @Autowired
    private UserHospitalRepository userHospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentTokenCounterRepository appointmentTokenCounterRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Object createPatient(PatientRequestDTO requestDTO, Long hid) {

        Patient patient;

        try{

            patient = Patient.builder()
                    .name(requestDTO.getName())
                    .phone(requestDTO.getPhone())
                    .gender(requestDTO.getGender())
                    .hospital_id(hid)
                    .age(requestDTO.getAge())
                    .build();

            repository.save(patient);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

        PatientResponseDto responseDto= PatientResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .build();

        return responseDto;


    }

    public List<DoctorDtoStaff> getDoctors(Long hid) {

        List<String> doctorList = userHospitalRepository.findDoctorNamesByHospital(hid , Role.DOCTOR);


        return doctorList.stream().map(doctor -> new DoctorDtoStaff(doctor)).toList();


    }

    @Transactional
    public AppointmentResponseDTO bookAppointment(AppointmentDTO appointmentDTO, Long hid) {
        LocalDate date = LocalDate.now();

        Optional<AppointmentTokenCounter> counter = appointmentTokenCounterRepository.findForUpdate(hid,appointmentDTO.getDoctorId(),date);

        AppointmentTokenCounter appointmentTokenCounter;
        if(counter.isEmpty()){
            appointmentTokenCounter = new AppointmentTokenCounter();
            appointmentTokenCounter.setLastToken(0L);
            appointmentTokenCounter.setDate(date);
            appointmentTokenCounter.setDoctorId(appointmentDTO.getDoctorId());
            appointmentTokenCounter.setHospitalId(hid);
        }else {
            appointmentTokenCounter = counter.get();
        }

        Long nextToken = appointmentTokenCounter.getLastToken()+1;
        appointmentTokenCounter.setLastToken(nextToken);
        appointmentTokenCounterRepository.save(appointmentTokenCounter);

        // Extract the hospital and handle the case where it's not found
        Hospital hospital = hospital_repository.findById(hid)
                .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + hid));

// Similarly for Doctor and Patient if they return Optional
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = repository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .doctor(doctor)
                .hospital(hospital)
                .patient(patient)
                .source(AppointmentSource.valueOf(appointmentDTO.getSource()))
                .status(AppointmentStatus.BOOKED)
                .token_Number(nextToken)
                .visitType(appointmentDTO.getVisitType())
                .build();

        appointmentRepository.save(appointment);

         AppointmentResponseDTO appointmentResponseDTO =new AppointmentResponseDTO();
         appointmentResponseDTO.setTokenNumber(nextToken);

         return appointmentResponseDTO;





    }
}
