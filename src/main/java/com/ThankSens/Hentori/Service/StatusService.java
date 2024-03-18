package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.OrderStatusEntity;
import com.ThankSens.Hentori.Repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusService {
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public StatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Bean
    public void createStatus(){
        for (int i = 1; i < 4; i++) {
           Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(i);
           if (!orderStatusEntity.isPresent()){
               OrderStatusEntity orderStatusEntityNew = new OrderStatusEntity();
               switch (i) {
                   case 1:
                       orderStatusEntityNew.setName("Hẹn lần 1");
                       break;
                   case 2:
                       orderStatusEntityNew.setName("Hẹn lần 2");
                       break;
                   case 3:
                       orderStatusEntityNew.setName("Hoàn thành");
                       break;
                   default:
                       break;
               }
               orderStatusRepository.save(orderStatusEntityNew);
           }
        }
    }
}
