package com.ThankSens.Hentori.Payload.Request;

import com.ThankSens.Hentori.Payload.Request.Client.ClientSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Client.ClientTrousersRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderAccessoryRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderStatusRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderRequest {
    private List<OrderSuitRequest> clientSuitRequestList;
    private List<OrderTrousersRequest> clientTrousersRequestsList;
    private List<OrderAccessoryRequest> orderAccessoryRequestList;
    private String appointmentDay;
    private UUID client_id;
    private OrderStatusRequest orderStatusRequest;

    public List<OrderSuitRequest> getClientSuitRequestList() {
        return clientSuitRequestList;
    }

    public void setClientSuitRequestList(List<OrderSuitRequest> clientSuitRequestList) {
        this.clientSuitRequestList = clientSuitRequestList;
    }

    public List<OrderTrousersRequest> getClientTrousersRequestsList() {
        return clientTrousersRequestsList;
    }

    public void setClientTrousersRequestsList(List<OrderTrousersRequest> clientTrousersRequestsList) {
        this.clientTrousersRequestsList = clientTrousersRequestsList;
    }

    public List<OrderAccessoryRequest> getOrderAccessoryRequestList() {
        return orderAccessoryRequestList;
    }

    public void setOrderAccessoryRequestList(List<OrderAccessoryRequest> orderAccessoryRequestList) {
        this.orderAccessoryRequestList = orderAccessoryRequestList;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }

    public OrderStatusRequest getOrderStatusRequest() {
        return orderStatusRequest;
    }

    public void setOrderStatusRequest(OrderStatusRequest orderStatusRequest) {
        this.orderStatusRequest = orderStatusRequest;
    }
}
