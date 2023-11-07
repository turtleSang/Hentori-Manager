package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderTrousersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrousersRepository extends JpaRepository<OrderTrousersEntity, Integer> {
}
