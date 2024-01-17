package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.ClientTrousersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientTrousersRepository extends JpaRepository<ClientTrousersEntity, UUID> {

}
