package com.ThankSens.Hentori.Payload.Request;

import com.ThankSens.Hentori.Payload.Request.Order.OrderStatusRequest;

import java.util.UUID;

public class OrderUpdateRequest {
    private String appointmentDay;
    private OrderStatusRequest orderStatusRequest;
    private int payment;

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public OrderStatusRequest getOrderStatusRequest() {
        return orderStatusRequest;
    }

    public void setOrderStatusRequest(OrderStatusRequest orderStatusRequest) {
        this.orderStatusRequest = orderStatusRequest;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
