package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Hospital_Repository extends JpaRepository<Hospital,Long> {
    boolean existsById(Long id);
    Optional<Hospital> findById(Long id);
}
