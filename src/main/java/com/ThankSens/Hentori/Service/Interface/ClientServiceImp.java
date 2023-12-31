package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Payload.Request.ClientRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ClientServiceImp {
    ClientDto findClientByPhone(String phoneNumber);
    ClientEntity createClient(ClientRequest clientRequest);
    boolean updateClient(ClientRequest clientRequest, UUID id);
}
