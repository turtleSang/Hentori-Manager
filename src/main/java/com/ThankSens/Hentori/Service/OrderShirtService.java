package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.OrderShirtDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Entity.OrderShirtEntity;
import com.ThankSens.Hentori.Payload.Request.Order.OrderShirtRequest;
import com.ThankSens.Hentori.Repository.OrderRepository;
import com.ThankSens.Hentori.Repository.OrderShirtRepository;
import com.ThankSens.Hentori.Service.Interface.OrderShirtServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderShirtService implements OrderShirtServiceImp {
    private OrderShirtRepository orderShirtRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    public OrderShirtService(OrderShirtRepository orderShirtRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderShirtRepository = orderShirtRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderShirtDto findOrderShirt(int id) {
        Optional<OrderShirtEntity> orderShirtEntityOptional = orderShirtRepository.findById(id);
        if (orderShirtEntityOptional.isPresent()){
            OrderShirtDto orderShirtDto = modelMapper.map(orderShirtEntityOptional.get(), OrderShirtDto.class);
            return orderShirtDto;
        }

        return null;
    }

    @Override
    public boolean updateShirt(int id, OrderShirtRequest orderShirtRequest) {
        Optional<OrderShirtEntity> orderShirtEntityOptional = orderShirtRepository.findById(id);
        if (!(orderShirtEntityOptional.isPresent())){
            return false;
        }
        try {
            OrderShirtEntity orderShirtEntity = orderShirtEntityOptional.get();
            OrderEntity orderEntity = orderShirtEntity.getOrderEntity();
            int shirtId = orderShirtEntity.getId();

            //create new Entity form request
            OrderShirtEntity newOrderShirtEntity = transferOrderShirtRequestToOrderShirtEntity(orderShirtRequest);
            //set id and OrderEntity for update
            newOrderShirtEntity.setId(shirtId);
            newOrderShirtEntity.setOrderEntity(orderEntity);
            //Update new total for orders

            int totalOrderEntity = orderEntity.getTotal() - orderShirtEntity.getTotal() + newOrderShirtEntity.getTotal();
            orderEntity.setTotal(totalOrderEntity);
            orderShirtRepository.save(newOrderShirtEntity);
            orderEntity.setTotal(totalOrderEntity);

            orderRepository.save(orderEntity);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean deleteShirt(int id) {
        Optional<OrderShirtEntity> orderShirtEntityOptional = orderShirtRepository.findById(id);
        if (orderShirtEntityOptional.isPresent()){
            OrderShirtEntity orderShirtEntity = orderShirtEntityOptional.get();
            OrderEntity orderEntity = orderShirtEntity.getOrderEntity();
            int newTotal = orderEntity.getTotal() - orderShirtEntity.getTotal();
            orderEntity.setTotal(newTotal);
            orderShirtRepository.delete(orderShirtEntityOptional.get());
            orderRepository.save(orderEntity);
            return true;
        }
        return false;
    }

    private OrderShirtEntity transferOrderShirtRequestToOrderShirtEntity(OrderShirtRequest orderShirtRequest){
        try {
            int total = orderShirtRequest.getPrice()*orderShirtRequest.getAmount();
            OrderShirtEntity orderShirtEntity =  modelMapper.map(orderShirtRequest, OrderShirtEntity.class);
            orderShirtEntity.setTotal(total);
            return orderShirtEntity;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
