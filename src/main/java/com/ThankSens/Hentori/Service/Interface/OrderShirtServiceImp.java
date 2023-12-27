package com.ThankSens.Hentori.Service.Interface;


import com.ThankSens.Hentori.Dto.OrderShirtDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderShirtRequest;

public interface OrderShirtServiceImp {
    OrderShirtDto findOrderShirt(int id);
    boolean updateShirt(int id, OrderShirtRequest orderShirtRequest);
    boolean deleteShirt(int id);
}
