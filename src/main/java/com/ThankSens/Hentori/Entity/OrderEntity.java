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

    @Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createAt;

    @Column
    private int total;

    @Column
    private int payment;

    @Column(columnDefinition = "TIMESTAMP")
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


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
