package com.medi_connect.Medi_Connect.REPOSITORIES;

import com.medi_connect.Medi_Connect.Entity.User;
import com.medi_connect.Medi_Connect.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsById(Long id);
    boolean existsByPhone(String phone);

    User findByEmail(String email);

    Optional<User> findByPhone(String phone);
}
