package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.ClientDto;
import com.ThankSens.Hentori.Dto.ClientSuitDto;
import com.ThankSens.Hentori.Dto.ClientTrousersDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.ClientSuitEntity;
import com.ThankSens.Hentori.Entity.ClientTrousersEntity;
import com.ThankSens.Hentori.Payload.Request.ClientRequest;
import com.ThankSens.Hentori.Repository.ClientRepository;
import com.ThankSens.Hentori.Repository.ClientSuitRepository;
import com.ThankSens.Hentori.Repository.ClientTrousersRepository;
import com.ThankSens.Hentori.Service.Interface.ClientServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService implements ClientServiceImp {

    private ClientRepository clientRepository;
    private ClientSuitRepository clientSuitRepository;
    private ClientTrousersRepository clientTrousersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper, ClientSuitRepository clientSuitRepository,
                         ClientTrousersRepository clientTrousersRepository) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.clientSuitRepository = clientSuitRepository;
        this.clientTrousersRepository = clientTrousersRepository;
    }

    @Override
    public ClientDto findClientByPhone(String phoneNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);

            ClientDto clientDto = modelMapper.map(clientEntity, ClientDto.class);

            if (clientEntity.getClientSuitEntity() != null){
                ClientSuitDto clientSuitDto = modelMapper.map(clientEntity.getClientSuitEntity(), ClientSuitDto.class);
                clientDto.setClientSuitDto(clientSuitDto);
            }

            if (clientEntity.getClientTrousersEntity() != null){
                ClientTrousersDto clientTrousersDto = modelMapper.map(clientEntity.getClientTrousersEntity(), ClientTrousersDto.class);
                clientDto.setClientTrousersDto(clientTrousersDto);
            }

            return clientDto;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ClientEntity createClient(ClientRequest clientRequest) {
        try {
            ClientEntity clientEntity = modelMapper.map(clientRequest, ClientEntity.class);

            clientEntity = clientRepository.save(clientEntity);

            if (clientRequest.getClientSuitRequest() != null){
                ClientSuitEntity clientSuitEntity = modelMapper.map(clientRequest.getClientSuitRequest(), ClientSuitEntity.class);
                clientSuitEntity.setClientEntity(clientEntity);
                clientSuitEntity = clientSuitRepository.save(clientSuitEntity);
                clientEntity.setClientSuitEntity(clientSuitEntity);
            }

            if (clientRequest.getClientTrousersRequest() != null){
                ClientTrousersEntity clientTrousersEntity = modelMapper.map(clientRequest.getClientTrousersRequest(), ClientTrousersEntity.class);
                clientTrousersEntity.setClientEntity(clientEntity);
                clientTrousersEntity = clientTrousersRepository.save(clientTrousersEntity);
                clientEntity.setClientTrousersEntity(clientTrousersEntity);
            }

            clientEntity = clientRepository.save(clientEntity);

            return clientEntity;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean updateClient(ClientRequest clientRequest, UUID client_id) {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(client_id);

        if (clientEntityOptional.isPresent()){
            ClientEntity clientEntity = clientEntityOptional.get();
            if(clientRequest.getPhoneNumber() != null && clientRequest.getPhoneNumber() != clientEntity.getPhoneNumber()){
                clientEntity.setPhoneNumber(clientRequest.getPhoneNumber());
            };
            if(clientRequest.getUsername() != null && clientRequest.getUsername() != clientEntity.getUsername()){
                clientEntity.setPhoneNumber(clientRequest.getPhoneNumber());
            }

            if (clientRequest.getClientSuitRequest() != null){
                Optional<ClientSuitEntity> clientSuitEntityOptional = clientSuitRepository.findById(client_id);
                ClientSuitEntity clientSuitEntity = modelMapper.map(clientRequest.getClientSuitRequest(), ClientSuitEntity.class);
                if (clientSuitEntityOptional.isPresent()){
                    clientSuitEntity.setId(client_id);
                }else{
                    clientSuitEntity.setClientEntity(clientEntity);
                }
                 clientSuitRepository.save(clientSuitEntity);

            }
            if (clientRequest.getClientTrousersRequest() != null){
                Optional<ClientTrousersEntity> clientTrousersEntityOptional = clientTrousersRepository.findById(client_id);
                ClientTrousersEntity clientTrousersEntity = modelMapper.map(clientRequest.getClientTrousersRequest(), ClientTrousersEntity.class);
                if (clientTrousersEntityOptional.isPresent()){
                    clientTrousersEntity.setId(client_id);
                }else {
                    clientTrousersEntity.setClientEntity(clientEntity);
                }
                clientTrousersRepository.save(clientTrousersEntity);
            }
            clientRepository.save(clientEntity);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<String> searchByName(String name) {
        String nameQuery = "%" + name + "%";
        return clientRepository.findAllName(nameQuery);
    }

    @Override
    public List<ClientDto> findAllByName(String name) {
        String nameQuery = "%" + name + "%";
        List<ClientEntity> clientEntityList = clientRepository.findClientEntityByName(nameQuery);
        List<ClientDto> clientDtoList = new ArrayList<>();
        if (clientEntityList.size() >0){
            for (ClientEntity clientEntity: clientEntityList
                 ) {
                ClientDto clientDto = modelMapper.map(clientEntity, ClientDto.class);
                clientDtoList.add(clientDto);
            }
        }
        return clientDtoList;
    }


}
