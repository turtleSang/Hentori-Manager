package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    AdminEntity findByUsername(String username);
}
