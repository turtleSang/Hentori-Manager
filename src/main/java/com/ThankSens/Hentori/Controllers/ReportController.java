package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.ReportDto;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.ReportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/report")
public class ReportController {
    private ReportServiceImp reportServiceImp;

    @Autowired
    public ReportController(ReportServiceImp reportServiceImp) {
        this.reportServiceImp = reportServiceImp;
    }

    @GetMapping("/month")
    public ResponseEntity<?> reportByMonth(@RequestParam int month,@RequestParam int year){
        ReportDto reportDto= reportServiceImp.reportOrderByMonth(month,year);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if(reportDto == null){
            responseData.setCheck(false);
            responseData.setMessenger("not found");
            httpStatus = HttpStatus.NOT_FOUND;
        }else {
            responseData.setObject(reportDto);
            responseData.setCheck(true);
            responseData.setMessenger("OK");
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseData,httpStatus);
    }

    @GetMapping("/quarter")
    public ResponseEntity<?> reportByQuarter(@RequestParam int quarter,@RequestParam int year){
        ReportDto reportDto= reportServiceImp.reportOrderByQuarter(quarter, year);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if(reportDto == null){
            responseData.setCheck(false);
            responseData.setMessenger("not found");
            httpStatus = HttpStatus.NOT_FOUND;
        }else {
            responseData.setObject(reportDto);
            responseData.setCheck(true);
            responseData.setMessenger("OK");
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseData,httpStatus);
    }

    @GetMapping("/day")
    public ResponseEntity<?> reportByDay(@RequestParam String day){
        ReportDto reportDto = reportServiceImp.reportOrderByDate(day);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if(reportDto == null){
            responseData.setCheck(false);
            responseData.setMessenger("not found");
            httpStatus = HttpStatus.NOT_FOUND;
        }else {
            responseData.setObject(reportDto);
            responseData.setCheck(true);
            responseData.setMessenger("OK");
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseData,httpStatus);
    }

}
