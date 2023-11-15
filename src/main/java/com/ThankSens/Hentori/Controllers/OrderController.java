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
    public ResponseEntity<?> getAllOrders(){
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

    @GetMapping("/detail/{order_id}")
    public ResponseEntity<?> getDetailOrder(@PathVariable int order_id){
        OrderDto orderDto = orderServiceImp.getDetailOrder(order_id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (orderDto != null){
            responseData.setMessenger("OK");
            responseData.setCheck(true);
            responseData.setObject(orderDto);
            httpStatus = HttpStatus.FOUND;
        }
        else {
            responseData.setMessenger("Not Found");
            responseData.setCheck(true);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        ResponseEntity responseEntity = new ResponseEntity<ResponseData>(responseData, httpStatus);
        return responseEntity;
    }

}
