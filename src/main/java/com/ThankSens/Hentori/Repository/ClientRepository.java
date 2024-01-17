package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {
    ClientEntity findByPhoneNumber(String phoneNumber);

    @Query(
            value = "SELECT hentori.client.username from hentori.client where hentori.client.username like ?1 ",
    nativeQuery = true)
    List<String> findAllName(String nameSearch);

    @Query(
            value = "SELECT * from hentori.client where hentori.client.username like ?1 ",
            nativeQuery = true
    )
    List<ClientEntity> findClientEntityByName(String clientName);

}
