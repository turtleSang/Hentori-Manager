package com.ThankSens.Hentori.Dto;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private int id;
    private Date createAt;
    private int total;
    private int payment;
    private Date appointmentDay;
    private OrderStatusDto orderStatusDto;
    private OrderClientDto orderClientDto;
    private List<OrderSuitDto> orderSuitDtoList;
    private List<OrderTrousersDto> orderTrousersDtoList;
    private List<OrderAccessoryDto> orderAccessoryDtoList;
    private List<OrderShirtDto> orderShirtDtoList;

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public OrderClientDto getOrderClientDto() {
        return orderClientDto;
    }

    public void setOrderClientDto(OrderClientDto orderClientDto) {
        this.orderClientDto = orderClientDto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(Date appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public OrderStatusDto getOrderStatusDto() {
        return orderStatusDto;
    }

    public void setOrderStatusDto(OrderStatusDto orderStatusDto) {
        this.orderStatusDto = orderStatusDto;
    }

    public List<OrderSuitDto> getOrderSuitDtoList() {
        return orderSuitDtoList;
    }

    public void setOrderSuitDtoList(List<OrderSuitDto> orderSuitDtoList) {
        this.orderSuitDtoList = orderSuitDtoList;
    }

    public List<OrderTrousersDto> getOrderTrousersDtoList() {
        return orderTrousersDtoList;
    }

    public void setOrderTrousersDtoList(List<OrderTrousersDto> orderTrousersDtoList) {
        this.orderTrousersDtoList = orderTrousersDtoList;
    }

    public List<OrderAccessoryDto> getOrderAccessoryDtoList() {
        return orderAccessoryDtoList;
    }

    public void setOrderAccessoryDtoList(List<OrderAccessoryDto> orderAccessoryDtoList) {
        this.orderAccessoryDtoList = orderAccessoryDtoList;
    }

    public List<OrderShirtDto> getOrderShirtDtoList() {
        return orderShirtDtoList;
    }

    public void setOrderShirtDtoList(List<OrderShirtDto> orderShirtDtoList) {
        this.orderShirtDtoList = orderShirtDtoList;
    }
}
