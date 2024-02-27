package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.WaitingAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WaitingAdminRepository extends JpaRepository<WaitingAdminEntity, String> {
    List<WaitingAdminEntity> findAllByTimeDeleteBefore(Date now);
}
