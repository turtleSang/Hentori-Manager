package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    ClientEntity findByPhoneNumber(String phoneNumber);
}
