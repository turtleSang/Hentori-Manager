package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {
}
