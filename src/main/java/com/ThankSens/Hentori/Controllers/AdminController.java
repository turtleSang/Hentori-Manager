package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Payload.Request.AdminRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.AdminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminServiceImp adminServiceImp;

    public AdminController(AdminServiceImp adminServiceImp) {
        this.adminServiceImp = adminServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest adminRequest) {
        boolean check = adminServiceImp.createAdmin(adminRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (check) {
            responseData.setCheck(true);
            responseData.setMessenger("created");
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setCheck(false);
            responseData.setMessenger("Can't create");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmAdmin(@RequestBody AdminRequest adminRequest) {
        boolean check = adminServiceImp.confirmAdmin(adminRequest);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;
        if (check) {
            responseData.setCheck(true);
            responseData.setMessenger("confirmed");
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setCheck(false);
            responseData.setMessenger("Can't confirmed");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AdminRequest adminRequest) {
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus;

        boolean checkConfirms = adminServiceImp.checkConfirm(adminRequest);
        if (checkConfirms){
            responseData.setCheck(false);
            responseData.setMessenger("Need confirms");
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(responseData, httpStatus);
        }
        String jwt = adminServiceImp.signIn(adminRequest);

        if (jwt != null) {
            responseData.setCheck(true);
            responseData.setMessenger("ok");
            responseData.setObject(jwt);
            httpStatus = HttpStatus.OK;
        } else {
            responseData.setCheck(false);
            responseData.setMessenger("Can't sign in");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkAdmin(@RequestBody AdminRequest adminRequest){
        boolean check = adminServiceImp.checkAdminName(adminRequest.getUsername());
        ResponseData responseData = new ResponseData();
        responseData.setCheck(check);
        HttpStatus httpStatus;
        if (check){
            httpStatus = HttpStatus.OK;
            responseData.setMessenger("Can create");
        }else {
            httpStatus = HttpStatus.CONFLICT;
            responseData.setMessenger("User already exist");
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/update/check")
    public ResponseEntity<?> checkUpdateAdmin(@RequestBody AdminRequest adminRequest){
        boolean check = adminServiceImp.checkUpdatePassword(adminRequest.getUsername());
        ResponseData responseData = new ResponseData();
        responseData.setCheck(check);
        HttpStatus httpStatus;
        if (check){
            httpStatus = HttpStatus.OK;
            responseData.setMessenger("Found Users");
        }else {
            httpStatus = HttpStatus.FOUND;
            responseData.setMessenger("Not Found");
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminRequest adminRequest){
        boolean check = adminServiceImp.updatePassword(adminRequest);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(check);
        HttpStatus httpStatus;
        if (check){
            httpStatus = HttpStatus.OK;
            responseData.setMessenger("updated");
        }else {
            httpStatus = HttpStatus.FOUND;
            responseData.setMessenger("can not update");
        }
        return new ResponseEntity<>(responseData, httpStatus);
    }
}
