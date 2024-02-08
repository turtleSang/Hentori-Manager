package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.LogBookDto;
import com.ThankSens.Hentori.Payload.Request.Client.LogBookRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.LogBookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/logbook")
public class LogBookController {
    private LogBookServiceImp logBookServiceImp;

    @Autowired
    public LogBookController(LogBookServiceImp logBookServiceImp) {
        this.logBookServiceImp = logBookServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLogBook(@RequestBody LogBookRequest logBookRequest){
        boolean check = logBookServiceImp.createLogBook(logBookRequest);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(check);
        HttpStatus httpStatus;
        if (check){
            responseData.setMessenger("Created");
            httpStatus = HttpStatus.CREATED;
        }else {
            responseData.setMessenger("Can't found");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseData,httpStatus);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllLogBook(@RequestParam String phoneNumber ,@RequestParam(defaultValue = "0") int pageNumber){
        List<LogBookDto> logBookDtoList = logBookServiceImp.getAllLogBookByPhoneNumber(phoneNumber,pageNumber);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ResponseData responseData = new ResponseData();
        responseData.setCheck(false);
        responseData.setMessenger("not found");
        if (logBookDtoList != null){
            responseData.setCheck(true);
            responseData.setMessenger("ok");
            responseData.setObject(logBookDtoList);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @GetMapping("/get/page")
    public ResponseEntity<?> getAllPageLogBook(@RequestParam String phoneNumber){
        int pageTotalPage = logBookServiceImp.getAllPageLogBook(phoneNumber);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setMessenger("OK");
        responseData.setObject(pageTotalPage);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(responseData, httpStatus);
    }
}
