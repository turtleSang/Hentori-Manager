package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.OrderShirtDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderShirtRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.OrderShirtServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shirt")
public class ShirtController {
    private OrderShirtServiceImp orderShirtServiceImp;

    public ShirtController(OrderShirtServiceImp orderShirtServiceImp) {
        this.orderShirtServiceImp = orderShirtServiceImp;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailShirt(@PathVariable int id){
        OrderShirtDto orderShirtDto = orderShirtServiceImp.findOrderShirt(id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (orderShirtDto!=null){
            responseData.setCheck(true);
            responseData.setObject(orderShirtDto);
            responseData.setMessenger("founded");
            httpStatus = HttpStatus.OK;
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("not found");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShirt(@PathVariable int id, @RequestBody OrderShirtRequest orderShirtRequest){
        boolean check = orderShirtServiceImp.updateShirt(id, orderShirtRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (check){
            responseData.setCheck(true);
            responseData.setMessenger("updated");
            httpStatus = HttpStatus.OK;
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("cant not update");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShirt(@PathVariable int id){
        boolean check = orderShirtServiceImp.deleteShirt(id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (check){
            responseData.setCheck(true);
            responseData.setMessenger("deleted");
            httpStatus = HttpStatus.OK;
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("cant not delete");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }
}