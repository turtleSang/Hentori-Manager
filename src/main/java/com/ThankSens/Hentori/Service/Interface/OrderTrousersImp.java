package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Dto.OrderTrousersDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;

public interface OrderTrousersImp {
    OrderTrousersDto findOrderTrousers(int id);
    boolean updateTrousers(int id, OrderTrousersRequest orderTrousersRequest);
    boolean deleteTrousers(int id);
}
