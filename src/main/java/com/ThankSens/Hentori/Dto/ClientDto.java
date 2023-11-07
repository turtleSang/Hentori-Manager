package com.ThankSens.Hentori.Dto;

import java.util.UUID;

public class ClientDto {

    private UUID id;

    private String username;

    private String phoneNumber;

    private ClientSuitDto clientSuitDto;

    private ClientTrousersDto clientTrousersDto;

    public ClientDto(UUID id, String username, String phoneNumber, ClientSuitDto clientSuitDto, ClientTrousersDto clientTrousersDto) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.clientSuitDto = clientSuitDto;
        this.clientTrousersDto = clientTrousersDto;
    }

    public ClientDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ClientSuitDto getClientSuitDto() {
        return clientSuitDto;
    }

    public void setClientSuitDto(ClientSuitDto clientSuitDto) {
        this.clientSuitDto = clientSuitDto;
    }

    public ClientTrousersDto getClientTrousersDto() {
        return clientTrousersDto;
    }

    public void setClientTrousersDto(ClientTrousersDto clientTrousersDto) {
        this.clientTrousersDto = clientTrousersDto;
    }
}
