package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Entity.OrderStatusEntity;
import com.ThankSens.Hentori.Payload.Request.Order.OrderAccessoryRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderStatusRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Repository.*;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImp {
    private ClientRepository clientRepository;
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderSuitRepository orderSuitRepository;
    private OrderTrousersRepository orderTrousersRepository;
    private OrderAccessoryRepository orderAccessoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderService(ClientRepository clientRepository, OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, OrderSuitRepository orderSuitRepository, OrderTrousersRepository orderTrousersRepository, OrderAccessoryRepository orderAccessoryRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderSuitRepository = orderSuitRepository;
        this.orderTrousersRepository = orderTrousersRepository;
        this.orderAccessoryRepository = orderAccessoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createOrder(OrderRequest orderRequest) {
        try {
            OrderStatusRequest orderStatusRequest = orderRequest.getOrderStatusRequest();
            List<OrderSuitRequest> orderSuitRequestList = orderRequest.getClientSuitRequestList();
            List<OrderTrousersRequest> orderTrousersRequestList = orderRequest.getClientTrousersRequestsList();
            List<OrderAccessoryRequest> orderAccessoryRequestList = orderRequest.getOrderAccessoryRequestList();
            Optional<ClientEntity> clientEntity = clientRepository.findById(orderRequest.getClient_id());
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(orderRequest.getOrderStatusRequest().getId());
            if (clientEntity.isPresent() && orderStatusEntity.isPresent()){
                OrderEntity orderEntity = modelMapper.map(orderRequest, OrderEntity.class);
                orderEntity.setClientEntity(clientEntity.get());
                orderEntity.setOrderStatusEntity(orderStatusEntity.get());
                orderRepository.save(orderEntity);
                return  true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
