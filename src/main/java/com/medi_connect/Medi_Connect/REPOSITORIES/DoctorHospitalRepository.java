package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.DoctorHospital;
import com.medi_connect.Medi_Connect.Entity.UserHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorHospitalRepository extends JpaRepository<DoctorHospital,Long> {

    @Query("""
        SELECT 
            d.id AS id,
            d.name AS name,
            d.specialization AS specialization,
            d.phone AS phone,
            d.doctor_status AS doctor_status,
            d.email AS email
        FROM DoctorHospital dh
        JOIN dh.doctor d
        WHERE dh.hospital.id = :hospitalId
    """)
    List<DoctorProjection> findDoctorsByHospitalId(@Param("hospitalId") Long hospitalId);


    @Query("""
    SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END
    FROM Doctor d
    JOIN DoctorHospital dh ON d.id = dh.doctor.id
    WHERE d.phone = :mobileNumber
      AND dh.hospital.id = :hospitalId
""")
    boolean existsByMobileAndHospital(
            String mobileNumber,
            Long hospitalId
    );




}
