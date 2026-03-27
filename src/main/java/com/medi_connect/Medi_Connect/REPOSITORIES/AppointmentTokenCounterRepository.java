package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.AppointmentTokenCounter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AppointmentTokenCounterRepository extends JpaRepository<AppointmentTokenCounter,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT c FROM
           AppointmentTokenCounter c
           WHERE c.hospitalId=:hospitalId
           AND c.doctorId=:doctorId
           AND c.date=:date
            """)
    Optional<AppointmentTokenCounter> findForUpdate(@Param("hospitalId") Long hospitalId , @Param("doctorId") Long doctorId, @Param("date")LocalDate date);
}
