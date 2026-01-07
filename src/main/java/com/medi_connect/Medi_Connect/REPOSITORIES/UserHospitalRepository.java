package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.UserHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHospitalRepository extends JpaRepository<UserHospital,Long> {

    @Query("""
        SELECT 
            h.id AS hospitalId,
            h.name AS name,
            h.city AS city,
            uh.role AS role
        FROM UserHospital uh
        JOIN uh.hospital h
        WHERE uh.user.id = :userId
    """)
    List<HospitalProjection> findHospitalsByUserId(@Param("userId") Long userId);

    boolean existsByHospitalIdAndUserId(Long hospitalId , Long userId);
}
