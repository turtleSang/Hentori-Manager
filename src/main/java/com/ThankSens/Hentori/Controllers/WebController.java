package com.ThankSens.Hentori.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/createorder")
    public String createOrder(){return "createorder";}

    @GetMapping("/forgotpassword")
    public String forgotPassword(){
        return "forgotpassword";    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/confirmadmin")
    public String confirmAdmin(){
        return "confirmadmin";
    }

    @GetMapping("/printOrder")
    public String printOrder(){
        return "printOrder";
    }

    @GetMapping("/vieworder")
    public String viewOrder(){
        return "vieworder";
    }

    @GetMapping("/client")
    public String client(){
        return "client";
    }

    @GetMapping("/report")
    public String report(){
        return "report";
    }

    @GetMapping("/vieworderdetail")
    public String viewOrderDetail(){
        return "vieworderdetail";
    }

    @GetMapping("/detailclient")
    public String detailClient(){
        return "detailclient";
    }


}
