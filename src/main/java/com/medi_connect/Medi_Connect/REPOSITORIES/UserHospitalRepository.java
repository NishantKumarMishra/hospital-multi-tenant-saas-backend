package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.DTO.StaffMemberResponse;
import com.medi_connect.Medi_Connect.Entity.Doctor;
import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.Entity.UserHospital;
import com.medi_connect.Medi_Connect.Role;
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
      AND uh.role = :role
""")
    List<HospitalProjection> findHospitalsByUserIdAndRole(
            @Param("userId") Long userId,
            @Param("role") Role role
    );


    boolean existsByHospitalIdAndUserId(Long hospitalId , Long userId);
    boolean existsByUser_IdAndHospital_IdAndRole(
            Long userId,
            Long hospitalId,
            Role role
    );


    @Query("""
        select 
            d.id as id,
            d.name as name,
            d.phone as phone,
            d.email as email,
            d.specialization as specialization,
            d.doctor_status as doctor_status
        from UserHospital uh
        join uh.user u
        join Doctor d on d.user.id = u.id
        where uh.hospital.id = :hospitalId
          and uh.role = :role
    """)
    List<DoctorProjection> findDoctorsByHospitalAndRole(
            @Param("hospitalId") Long hospitalId,
            @Param("role") Role role
    );

    // In your UserHospitalRepository
    @Query(value = """
                select u.id , u.name , u.email, u.phone , COALESCE(d.specialization , uh.role) as specialization , uh.hospital_id , uh.role  from user_hospital uh
                            left join users u on
                            uh.user_id = u.id
                            left join doctor d on
                            uh.user_id = d.user_id
                            where uh.hospital_id = :hospitalId
                   
            """, nativeQuery = true)
    List<StaffProjection> findAllStaff(@Param("hospitalId") Long hospitalId);

    boolean existsByUserIdAndRole(@Param("userId") Long userId , Role role);

    @Query(value = """
    SELECT s.name 
    FROM user_hospital uh
    INNER JOIN staff s ON s.user_id = uh.user_id
    WHERE uh.hospital_id = :hospitalId 
    AND uh.role = :#{#role.name()}
    """, nativeQuery = true)
    List<String> findDoctorNamesByHospital(@Param("hospitalId") Long hospitalId, @Param("role") Role role);

}
