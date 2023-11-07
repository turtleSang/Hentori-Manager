package com.ThankSens.Hentori.Payload.Request;

import com.ThankSens.Hentori.Payload.Request.Client.ClientSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Client.ClientTrousersRequest;

public class ClientRequest {
    private String username;
    private String phoneNumber;

    private ClientSuitRequest clientSuitRequest;
    private ClientTrousersRequest clientTrousersRequest;

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

    public ClientSuitRequest getClientSuitRequest() {
        return clientSuitRequest;
    }

    public void setClientSuitRequest(ClientSuitRequest clientSuitRequest) {
        this.clientSuitRequest = clientSuitRequest;
    }

    public ClientTrousersRequest getClientTrousersRequest() {
        return clientTrousersRequest;
    }

    public void setClientTrousersRequest(ClientTrousersRequest clientTrousersRequest) {
        this.clientTrousersRequest = clientTrousersRequest;
    }
}
