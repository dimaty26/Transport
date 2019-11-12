package org.ncstudy.authentication.repository;

import org.ncstudy.authentication.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);
    UserData findByActivationCode(UUID uuid);
    UserData findByResetPasswordCode(UUID uuid);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
