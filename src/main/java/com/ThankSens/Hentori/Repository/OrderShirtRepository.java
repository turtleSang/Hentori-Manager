package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShirtRepository extends JpaRepository<OrderShirtEntity, Integer> {

}
