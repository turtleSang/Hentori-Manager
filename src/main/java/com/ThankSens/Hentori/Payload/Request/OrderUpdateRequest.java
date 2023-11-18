package com.ThankSens.Hentori.Payload.Request;

import com.ThankSens.Hentori.Payload.Request.Order.OrderStatusRequest;

import java.util.UUID;

public class OrderUpdateRequest {
    private String appointmentDay;
    private UUID client_id;
    private OrderStatusRequest orderStatusRequest;

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
