package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;

import java.util.List;

public interface OrderServiceImp {
    boolean createOrder(OrderRequest orderRequest);
    List<OrderDto> getAllOrder();
}
