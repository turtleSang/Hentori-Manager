package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import com.ThankSens.Hentori.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImp orderServiceImp;
    private ModelMapper modelMapper;

    public OrderController(OrderServiceImp orderServiceImp, ModelMapper modelMapper) {
        this.orderServiceImp = orderServiceImp;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        boolean check = orderServiceImp.createOrder(orderRequest);
        return null;
    }

}
