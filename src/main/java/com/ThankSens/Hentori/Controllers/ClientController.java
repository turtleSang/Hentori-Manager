package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Dto.ClientSuitDto;
import com.ThankSens.Hentori.Dto.ClientTrousersDto;
import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Payload.Request.ClientRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.ClientServiceImp;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import com.ThankSens.Hentori.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientServiceImp clientServiceImp;
    private OrderServiceImp orderServiceImp;
    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientServiceImp clientServiceImp, ModelMapper modelMapper, OrderServiceImp orderServiceImp) {
        this.modelMapper = modelMapper;
        this.clientServiceImp = clientServiceImp;
        this.orderServiceImp = orderServiceImp;
    }
    @GetMapping("/getclient")
    public ResponseEntity<?> findClientByPhone(@RequestParam String phoneNumber){
        ClientDto clientDto = clientServiceImp.findClientByPhone(phoneNumber);
        ResponseData responseData = new ResponseData();
        if (clientDto != null){
            responseData.setObject(clientDto);
            responseData.setCheck(true);
            responseData.setMessenger("founded");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("not found");
            return new ResponseEntity<>(responseData,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        List<String> listName = clientServiceImp.searchByName(name);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        responseData.setCheck(false);
        responseData.setMessenger("Not found");

        if (listName.size() >0){
            responseData.setMessenger("OK");
            responseData.setObject(listName);
            responseData.setCheck(true);
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping("/createclient")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest){
        ClientEntity clientEntity = clientServiceImp.createClient(clientRequest);
        ResponseData responseData = new ResponseData();
        if (clientEntity != null){
            ClientDto clientDto = modelMapper.map(clientEntity, ClientDto.class);
            if(clientEntity.getClientSuitEntity() != null){
                ClientSuitDto clientSuitDto = modelMapper.map(clientEntity.getClientSuitEntity(), ClientSuitDto.class);
                clientDto.setClientSuitDto(clientSuitDto);
            }
            if(clientEntity.getClientTrousersEntity() != null){
                ClientTrousersDto clientTrousersDto = modelMapper.map(clientEntity.getClientTrousersEntity(), ClientTrousersDto.class);
                clientDto.setClientTrousersDto(clientTrousersDto);
            }

            responseData.setMessenger("Create Successful");
            responseData.setCheck(true);
            responseData.setObject(clientDto);
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        }else {
            responseData.setMessenger("Create fail");
            responseData.setCheck(false);
            return new ResponseEntity<>(responseData, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody ClientRequest clientRequest, @RequestParam UUID client_id){
        ResponseData responseData = new ResponseData();
        try {
            boolean check = clientServiceImp.updateClient(clientRequest, client_id);

            if (check){
                responseData.setCheck(true);
                responseData.setMessenger("updated");
                return new ResponseEntity<>(responseData, HttpStatus.OK);
            }else {
                responseData.setCheck(false);
                responseData.setMessenger("Can not update");
                return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseData.setCheck(false);
            responseData.setMessenger("Phone number was using for other client");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/find/phone")
    public ResponseEntity<?> findAllByPhoneNumber(@RequestParam String phoneNumber, @RequestParam(defaultValue = "0") int pageNumber){
        List<ClientDto> clientDtoList = clientServiceImp.findAddByPhoneNumber(phoneNumber, pageNumber);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        responseData.setCheck(false);
        responseData.setMessenger("Not found");

        if (clientDtoList.size() >0){
            responseData.setMessenger("OK");
            responseData.setObject(clientDtoList);
            responseData.setCheck(true);
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findAllByName(@RequestParam String name, @RequestParam(defaultValue = "0") int pageNumber){
        List<ClientDto> clientDtoList = clientServiceImp.findAllByName(name, pageNumber);
        ResponseData responseData = new ResponseData();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        responseData.setCheck(false);
        responseData.setMessenger("Not found");

        if (clientDtoList != null){
            responseData.setMessenger("OK");
            responseData.setCheck(true);
            httpStatus = HttpStatus.OK;
        }
        responseData.setObject(clientDtoList);
        return new ResponseEntity<>(responseData, httpStatus);
    }

    @GetMapping("/order/processing")
    public ResponseEntity<?> findAllProcessingOrderByClient(@RequestParam String phoneNumber, @RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.findOrderProcessingByClient(phoneNumber, pageNumber);
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

    @GetMapping("/order/processing/page")
    public ResponseEntity<?> findAllProcessingOrderByClientPage(@RequestParam String phoneNumber){
        int numberPage = orderServiceImp.findOrderProcessingPage(phoneNumber);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/order/complete")
    public ResponseEntity<?> findAllCompleteOrderByClient(@RequestParam String phoneNumber, @RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.findOrderCompleteByClient(phoneNumber, pageNumber);
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

    @GetMapping("/order/complete/page")
    public ResponseEntity<?> findAllCompleteOrderByClientPage(@RequestParam String phoneNumber){
        int numberPage = orderServiceImp.findOrderCompletePage(phoneNumber);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/order/all")
    public ResponseEntity<?> findAllOrderByClient(@RequestParam String phoneNumber, @RequestParam(defaultValue = "0") int pageNumber){
        List<OrderDto> orderDtoList = orderServiceImp.findAllByClient(phoneNumber, pageNumber);
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

    @GetMapping("/order/all/page")
    public ResponseEntity<?> findAllOrderByClientPage(@RequestParam String phoneNumber){
        int numberPage = orderServiceImp.findAllPage(phoneNumber);
        ResponseData responseData = new ResponseData();
        responseData.setCheck(true);
        responseData.setObject(numberPage);
        responseData.setMessenger("OK");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}
