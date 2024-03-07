package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Payload.Request.KPIRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.KPIServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kpi")
public class KPIController {
    private KPIServiceImp kpiServiceImp;

    @Autowired
    public KPIController(KPIServiceImp kpiServiceImp) {
        this.kpiServiceImp = kpiServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createKPI(@RequestBody KPIRequest kpiRequest){
        boolean check =   kpiServiceImp.createKPI(kpiRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
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
