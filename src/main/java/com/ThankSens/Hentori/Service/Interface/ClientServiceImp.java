package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Dto.OrderDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Payload.Request.ClientRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ClientServiceImp {
    ClientDto findClientByPhone(String phoneNumber);
    ClientEntity createClient(ClientRequest clientRequest);
    boolean updateClient(ClientRequest clientRequest, UUID id);
    List<String> searchByName(String name);
    List<ClientDto> findAllByName(String name, int pageNumber);
    List<ClientDto> findAddByPhoneNumber(String phoneNumber, int pageNumber);
}
