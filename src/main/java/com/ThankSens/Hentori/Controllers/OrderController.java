package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import com.ThankSens.Hentori.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImp orderServiceImp;
    private ModelMapper modelMapper;

    public OrderController(OrderServiceImp orderServiceImp, ModelMapper modelMapper) {
        this.orderServiceImp = orderServiceImp;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        boolean check = orderServiceImp.createOrder(orderRequest);
        ResponseData responseData = new ResponseData();
        if (check){
            responseData.setCheck(true);
            responseData.setMessenger("created");
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("can not create");
        }
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder(){
        List<OrderDto> orderDtoList = orderServiceImp.getAllOrder();
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null){
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.FOUND);
    }
}
