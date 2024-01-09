package com.ThankSens.Hentori.Repository;

import com.ThankSens.Hentori.Entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query( value = "select*from hentori.orders where hentori.orders.status_id != 3", nativeQuery = true)
    List<OrderEntity> findProcessingOrder();

    @Query(
            value = "SELECT * FROM hentori.orders WHERE hentori.orders.create_at > ?1 and hentori.orders.create_at < ?2",
            nativeQuery = true)
    List<OrderEntity> findOrderByDate(Date startDate, Date endDate);

    @Query(
            value = "SELECT * FROM hentori.orders where hentori.orders.appointment_day < ?1 and hentori.orders.status_id != 3 order by hentori.orders.appointment_day asc",
            nativeQuery = true
    )
    List<OrderEntity> findDueOrder(Date now, Pageable pageable);

    @Query(
            value = "SELECT * FROM hentori.orders where hentori.orders.appointment_day < ?1 and hentori.orders.status_id != 3 order by hentori.orders.appointment_day asc",
            nativeQuery = true
    )
    Page<OrderEntity> findDuePage(Date now, Pageable pageable);
}
