package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.ClientSuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientSuitRepository extends JpaRepository<ClientSuitEntity, UUID> {

}
