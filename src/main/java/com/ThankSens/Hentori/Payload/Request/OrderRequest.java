package com.ThankSens.Hentori.Payload.Request;

import com.ThankSens.Hentori.Payload.Request.Order.OrderAccessoryRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderStatusRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;

import java.util.List;
import java.util.UUID;

public class OrderRequest {
    private List<OrderSuitRequest> orderSuitRequestList;
    private List<OrderTrousersRequest> orderTrousersRequestList;
    private List<OrderAccessoryRequest> orderAccessoryRequestList;
    private String appointmentDay;
    private UUID client_id;
    private int payment;
    private OrderStatusRequest orderStatusRequest;

    public List<OrderSuitRequest> getOrderSuitRequestList() {
        return orderSuitRequestList;
    }

    public void setOrderSuitRequestList(List<OrderSuitRequest> orderSuitRequestList) {
        this.orderSuitRequestList = orderSuitRequestList;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public List<OrderTrousersRequest> getOrderTrousersRequestList() {
        return orderTrousersRequestList;
    }

    public void setOrderTrousersRequestList(List<OrderTrousersRequest> orderTrousersRequestList) {
        this.orderTrousersRequestList = orderTrousersRequestList;
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
