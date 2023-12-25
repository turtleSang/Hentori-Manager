package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.OrderSuitDto;
import com.ThankSens.Hentori.Dto.OrderTrousersDto;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.OrderTrousersImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/trousers")
public class TrousersController {
    private OrderTrousersImp orderTrousersImp;

    @Autowired
    public TrousersController(OrderTrousersImp orderTrousersImp) {
        this.orderTrousersImp = orderTrousersImp;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getSuitDetail(@PathVariable int id){
        OrderTrousersDto orderTrousersDto = orderTrousersImp.findOrderTrousers(id);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (orderTrousersDto != null) {
            responseData.setMessenger("OK");
            responseData.setCheck(true);
            responseData.setObject(orderTrousersDto);
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setMessenger("Not Found");
            responseData.setCheck(true);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>( responseData, httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putSuit(@PathVariable int id, @RequestBody OrderTrousersRequest orderTrousersRequest){
        boolean check = orderTrousersImp.updateTrousers(id, orderTrousersRequest);
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
        boolean check = orderTrousersImp.deleteTrousers(id);
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
