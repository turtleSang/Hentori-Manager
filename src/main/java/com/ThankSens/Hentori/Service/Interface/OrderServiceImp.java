package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Payload.Request.OrderUpdateRequest;

import java.text.ParseException;
import java.util.List;

public interface OrderServiceImp {
    boolean createOrder(OrderRequest orderRequest);
    List<OrderDto> getAllOrder();
    OrderDto getDetailOrder(int id);
    boolean updateOrder(int order_id, OrderUpdateRequest orderUpdateRequest) throws ParseException;
    boolean updateOrderSuit(int order_suit_id, OrderSuitRequest orderSuitRequest);
    boolean updateOrderTrousers(int order_trousers_id, OrderTrousersRequest orderTrousersRequest);
}
