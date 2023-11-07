package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderSuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSuitRepository extends JpaRepository<OrderSuitEntity, Integer> {
}
