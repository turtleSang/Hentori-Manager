package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Entity.OrderSuitEntity;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Repository.OrderRepository;
import com.ThankSens.Hentori.Repository.OrderSuitRepository;
import com.ThankSens.Hentori.Service.Interface.OrderSuitServiceImp;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderSuitService implements OrderSuitServiceImp {
    private OrderSuitRepository orderSuitRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderSuitService(OrderSuitRepository orderSuitRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderSuitRepository = orderSuitRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderSuitDto findOrderSuit(int id) {
        Optional<OrderSuitEntity> orderSuitEntityOptional = orderSuitRepository.findById(id);
        if (orderSuitEntityOptional.isPresent()){
            OrderSuitDto orderSuitDto = modelMapper.map(orderSuitEntityOptional.get(), OrderSuitDto.class);
            return orderSuitDto;
        }

        return null;
    }

    @Override
    @Transactional
    public boolean updateSuit(int id, OrderSuitRequest orderSuitRequest) {
        Optional<OrderSuitEntity> orderSuitEntityOptional = orderSuitRepository.findById(id);
        if (!(orderSuitEntityOptional.isPresent())) {
            return false;
        }
        try {
            OrderSuitEntity orderSuitEntity = orderSuitEntityOptional.get();
            OrderEntity orderEntity = orderSuitEntity.getOrderEntity();
            //set get suitID and old total saved
            int suitId = orderSuitEntity.getId();
            //create new OrderEntity form request
            OrderSuitEntity newOrderSuitEntity = transferOrderSuitRequestToOrderSuitEntity(orderSuitRequest);
            //set id for update Suit
            newOrderSuitEntity.setId(suitId);
            //Save update Suit
            orderSuitRepository.save(newOrderSuitEntity);
            //Update orderEntity;
            int totalOrderEntity = orderEntity.getTotal() - orderSuitEntity.getTotal() + newOrderSuitEntity.getTotal();
            orderEntity.setTotal(totalOrderEntity);
            orderRepository.save(orderEntity);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean deleteSuit(int id) {
        Optional<OrderSuitEntity> orderSuitEntityOptional = orderSuitRepository.findById(id);
        if (orderSuitEntityOptional.isPresent()){
            OrderSuitEntity orderSuitEntity = orderSuitEntityOptional.get();
            OrderEntity orderEntity =orderSuitEntity.getOrderEntity();
            int newTotal = orderEntity.getTotal() - orderSuitEntity.getTotal();
            orderEntity.setTotal(newTotal);
            orderRepository.save(orderEntity);
            orderSuitRepository.delete(orderSuitEntityOptional.get());
            return true;
        }
        return false;
    }

    private OrderSuitEntity transferOrderSuitRequestToOrderSuitEntity(OrderSuitRequest orderSuitRequest){
        int total = orderSuitRequest.getPrice()*orderSuitRequest.getAmount();
        OrderSuitEntity orderSuitEntity = modelMapper.map(orderSuitRequest, OrderSuitEntity.class);
        orderSuitEntity.setTotal(total);
        return orderSuitEntity;
    }
}
