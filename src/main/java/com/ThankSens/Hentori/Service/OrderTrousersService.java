package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Dto.OrderTrousersDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Entity.OrderSuitEntity;
import com.ThankSens.Hentori.Entity.OrderTrousersEntity;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;
import com.ThankSens.Hentori.Repository.OrderRepository;
import com.ThankSens.Hentori.Repository.OrderTrousersRepository;
import com.ThankSens.Hentori.Service.Interface.OrderTrousersImp;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderTrousersService implements OrderTrousersImp {
    private OrderRepository orderRepository;
    private OrderTrousersRepository orderTrousersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderTrousersService(OrderRepository orderRepository, OrderTrousersRepository orderTrousersRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderTrousersRepository = orderTrousersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderTrousersDto findOrderTrousers(int id) {
        Optional<OrderTrousersEntity> orderTrousersEntityOptional = orderTrousersRepository.findById(id);
        if (orderTrousersEntityOptional.isPresent()){
            OrderTrousersDto orderTrousersDto = modelMapper.map(orderTrousersEntityOptional.get(), OrderTrousersDto.class);
            return orderTrousersDto;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean updateTrousers(int id, OrderTrousersRequest orderTrousersRequest) {
        Optional<OrderTrousersEntity> orderTrousersEntityOptional = orderTrousersRepository.findById(id);
        if (!(orderTrousersEntityOptional.isPresent())) {
            return false;
        }
        try {
            OrderTrousersEntity orderTrousersEntity = orderTrousersEntityOptional.get();
            OrderEntity orderEntity = orderTrousersEntity.getOrderEntity();
            int trousersId = orderTrousersEntity.getId();

            //create new Entity form request
            OrderTrousersEntity newOrderTrousersEntity = transferOrderTrousersRequestToOrderTrouserEntity(orderTrousersRequest);
            //set id for update trousers
            newOrderTrousersEntity.setId(trousersId);
            newOrderTrousersEntity.setOrderEntity(orderEntity);
            //Save update trousers
            orderTrousersRepository.save(newOrderTrousersEntity);
            //Update orderEntity;
            int totalOrderEntity = orderEntity.getTotal() + newOrderTrousersEntity.getTotal() - orderTrousersEntity.getTotal();
            orderEntity.setTotal(totalOrderEntity);
            orderRepository.save(orderEntity);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteTrousers(int id) {
        Optional<OrderTrousersEntity> orderTrousersEntityOptional = orderTrousersRepository.findById(id);
        if (orderTrousersEntityOptional.isPresent()) {
            OrderTrousersEntity orderTrousersEntity = orderTrousersEntityOptional.get();
            OrderEntity orderEntity = orderTrousersEntity.getOrderEntity();
            int newTotal = orderEntity.getTotal() - orderTrousersEntity.getTotal();
            orderEntity.setTotal(newTotal);
            orderRepository.save(orderEntity);
            orderTrousersRepository.delete(orderTrousersEntity);
            return true;
        }
        return false;
    }

    public OrderTrousersEntity transferOrderTrousersRequestToOrderTrouserEntity(OrderTrousersRequest orderTrousersRequest){
        try {
            int total = orderTrousersRequest.getAmount()*orderTrousersRequest.getPrice();
            OrderTrousersEntity orderTrousersEntity = modelMapper.map(orderTrousersRequest, OrderTrousersEntity.class);
            orderTrousersEntity.setTotal(total);
            return orderTrousersEntity;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
