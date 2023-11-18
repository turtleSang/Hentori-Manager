package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query( value = "select*from hentori.orders where hentori.orders.status_id != 3", nativeQuery = true)
    List<OrderEntity> findProcessingOrder();
}
