package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @Column
    private Long total;

    @Column
    private Date appointmentDay;

    @ManyToOne
    @JoinColumn(name = "status_id" )
    private OrderStatusEntity orderStatusEntity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderSuitEntity> orderSuitEntityList;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderTrousersEntity> orderTrousersEntityList;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderAccessoryEntity> orderAccessoryEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public List<OrderSuitEntity> getOrderSuitEntityList() {
        return orderSuitEntityList;
    }

    public void setOrderSuitEntityList(List<OrderSuitEntity> orderSuitEntityList) {
        this.orderSuitEntityList = orderSuitEntityList;
    }

    public List<OrderTrousersEntity> getOrderTrousersEntityList() {
        return orderTrousersEntityList;
    }

    public void setOrderTrousersEntityList(List<OrderTrousersEntity> orderTrousersEntityList) {
        this.orderTrousersEntityList = orderTrousersEntityList;
    }

    public List<OrderAccessoryEntity> getOrderAccessoryEntityList() {
        return orderAccessoryEntityList;
    }

    public void setOrderAccessoryEntityList(List<OrderAccessoryEntity> orderAccessoryEntityList) {
        this.orderAccessoryEntityList = orderAccessoryEntityList;
    }

    public Date getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(Date appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public OrderStatusEntity getOrderStatusEntity() {
        return orderStatusEntity;
    }

    public void setOrderStatusEntity(OrderStatusEntity orderStatusEntity) {
        this.orderStatusEntity = orderStatusEntity;
    }
}
