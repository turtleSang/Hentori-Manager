package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.KPIEntity;
import com.ThankSens.Hentori.Entity.Keys.KPIId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPIRepository extends JpaRepository<KPIEntity, KPIId> {
}
