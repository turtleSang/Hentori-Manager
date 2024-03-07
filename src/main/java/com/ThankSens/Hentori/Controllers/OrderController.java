package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Payload.Request.OrderUpdateRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderServiceImp orderServiceImp;

    public OrderController(OrderServiceImp orderServiceImp, ModelMapper modelMapper) {
        this.orderServiceImp = orderServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        int check = orderServiceImp.createOrder(orderRequest);
        ResponseData responseData = new ResponseData();
        if (check != 0) {
            responseData.setCheck(true);
            responseData.setMessenger("created");
            responseData.setObject(check);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.setCheck(false);
            responseData.setMessenger("can not create");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(@RequestParam(defaultValue = "0") int pageNumber) {
        List<OrderDto> orderDtoList = orderServiceImp.getAllOrder(pageNumber);
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null) {
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/all/page")
    public ResponseEntity<?> getPageAll(){
        int numberPage = orderServiceImp.getPageAll();
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/detail/{order_id}")
    public ResponseEntity<?> getDetailOrder(@PathVariable int order_id) {
        OrderDto orderDto = orderServiceImp.getDetailOrder(order_id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (orderDto != null) {
            responseData.setMessenger("OK");
            responseData.setCheck(true);
            responseData.setObject(orderDto);
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setMessenger("Not Found");
            responseData.setCheck(true);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        ResponseEntity responseEntity = new ResponseEntity<ResponseData>(responseData, httpStatus);
        return responseEntity;
    }

    @GetMapping("/due")
    public ResponseEntity<?> getDueOrder(@RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.getDueOrder(pageNumber);
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null) {
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/due/page")
    public ResponseEntity<?> getPageDue(){
        int numberPage = orderServiceImp.getPageDue();
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/processing")
    public ResponseEntity<?> getProcessingOrder(@RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.getProcessingOrder(pageNumber);
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null) {
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/processing/page")
    public ResponseEntity<?> getPageProcessing(){
        int numberPage = orderServiceImp.getPageProcessing();
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> getCompleteOrder(@RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.getCompleteOrder(pageNumber);
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null) {
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/complete/page")
    public ResponseEntity<?> getPageComplete(){
        int numberPage = orderServiceImp.getPageComplete();
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<?> getOrderByDate(@RequestParam String startDate, @RequestParam String endDate, @RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.getOrderByDate(startDate,endDate,pageNumber);
        ResponseData responseData = new ResponseData();
        if (orderDtoList == null) {
            responseData.setCheck(true);
            responseData.setMessenger("Not Found");
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
        responseData.setCheck(true);
        responseData.setMessenger("Ok");
        responseData.setObject(orderDtoList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/date/page")
    public ResponseEntity<?> getPageDate(@RequestParam String startDate, @RequestParam String endDate){
        int numberPage = orderServiceImp.getPageDate(startDate, endDate);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/update/{order_id}")
    public ResponseEntity<?> updateOrder(@PathVariable int order_id, @RequestBody OrderUpdateRequest orderUpdateRequest) {
        boolean check = false;
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        try {
            check = orderServiceImp.updateOrder(order_id, orderUpdateRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (check) {
            responseData.setMessenger("updated");
            responseData.setCheck(true);
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setMessenger("Can not update");
            responseData.setCheck(false);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }


}