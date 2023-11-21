package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Payload.Request.AdminRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.AdminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminServiceImp adminServiceImp;

    public AdminController(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest adminRequest){
        boolean check = adminServiceImp.createAdmin(adminRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (check){
            responseData.setCheck(true);
            responseData.setMessenger("created");
            httpStatus = HttpStatus.OK;
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("Can't create");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AdminRequest adminRequest){
        String jwt = adminServiceImp.signIn(adminRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (jwt != null){
            responseData.setCheck(true);
            responseData.setMessenger("ok");
            responseData.setObject(jwt);
            httpStatus = HttpStatus.OK;
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("Can't sign in");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }
}
