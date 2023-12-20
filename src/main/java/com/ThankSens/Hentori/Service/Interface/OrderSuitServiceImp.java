package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;

public interface OrderSuitServiceImp {
    OrderSuitDto findOrderSuit(int id);
    boolean updateSuit(int id, OrderSuitRequest orderSuitRequest);
    boolean deleteSuit(int id);
}
