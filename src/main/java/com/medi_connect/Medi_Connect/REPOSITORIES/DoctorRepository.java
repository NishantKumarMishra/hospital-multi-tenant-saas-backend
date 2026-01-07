package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {


    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);



}
