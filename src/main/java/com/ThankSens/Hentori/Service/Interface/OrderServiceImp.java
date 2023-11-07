package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;

public interface OrderServiceImp {
    boolean createOrder(OrderRequest orderRequest);
}
