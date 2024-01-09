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
    int createOrder(OrderRequest orderRequest);
    List<OrderDto> getAllOrder();
    OrderDto getDetailOrder(int id);
    boolean updateOrder(int order_id, OrderUpdateRequest orderUpdateRequest) throws ParseException;
    List<OrderDto> getProcessingOrder();
    List<OrderDto> getOrderByDate(String startDate, String endDate);
    List<OrderDto> getDueOrder(int PageNumber);
    int getPageDue();
}
