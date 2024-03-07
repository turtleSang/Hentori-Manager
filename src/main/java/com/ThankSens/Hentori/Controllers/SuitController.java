package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.OrderSuitServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/suit")
public class SuitController {
    private OrderSuitServiceImp orderSuitServiceImp;

    public SuitController(OrderSuitServiceImp orderSuitServiceImp) {
        this.orderSuitServiceImp = orderSuitServiceImp;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getSuitDetail(@PathVariable int id){
        OrderSuitDto orderSuitDto = orderSuitServiceImp.findOrderSuit(id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (orderSuitDto != null) {
            responseData.setMessenger("OK");
            responseData.setCheck(true);
            responseData.setObject(orderSuitDto);
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setMessenger("Not Found");
            responseData.setCheck(true);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>( responseData, httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putSuit(@PathVariable int id, @RequestBody OrderSuitRequest orderSuitRequest){
        boolean check = orderSuitServiceImp.updateSuit(id, orderSuitRequest);
        ResponseData responseData = new ResponseData();
        if (check){
            responseData.setMessenger("updated");
            responseData.setCheck(true);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setMessenger("can not update");
        responseData.setCheck(false);
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuit(@PathVariable int id){
        boolean check = orderSuitServiceImp.deleteSuit(id);
        ResponseData responseData = new ResponseData();
        if (check){
            responseData.setMessenger("deleted");
            responseData.setCheck(true);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
        responseData.setMessenger("can not delete");
        responseData.setCheck(false);
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }
}
