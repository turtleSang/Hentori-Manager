package com.ThankSens.Hentori.Controllers;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Dto.ClientSuitDto;
import com.ThankSens.Hentori.Dto.ClientTrousersDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Payload.Request.ClientRequest;
import com.ThankSens.Hentori.Payload.Response.ResponseData;
import com.ThankSens.Hentori.Service.Interface.ClientServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientServiceImp clientServiceImp;
    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientServiceImp clientServiceImp, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.clientServiceImp = clientServiceImp;
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
        boolean check = clientServiceImp.updateClient(clientRequest, client_id);
        ResponseData responseData = new ResponseData();
        if (check){
            responseData.setCheck(true);
            responseData.setMessenger("updated");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else {
            responseData.setCheck(false);
            responseData.setMessenger("Can not update");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

}
