package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_suit")
public class OrderSuitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String kieuAo;

    @Column
    private String formAo;

    @Column
    private String kieuVeAo;

    @Column
    private String lotAo;

    @Column
    private String kieuNut;

    @Column
    private String kieuTui;

    @Column
    private int price;

    @Column
    private int amount;

    @Column
    private int total;

    @Column(length = 1000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKieuAo() {
        return kieuAo;
    }

    public void setKieuAo(String kieuAo) {
        this.kieuAo = kieuAo;
    }

    public String getFormAo() {
        return formAo;
    }

    public void setFormAo(String formAo) {
        this.formAo = formAo;
    }

    public String getKieuVeAo() {
        return kieuVeAo;
    }

    public void setKieuVeAo(String kieuVeAo) {
        this.kieuVeAo = kieuVeAo;
    }

    public String getLotAo() {
        return lotAo;
    }

    public void setLotAo(String lotAo) {
        this.lotAo = lotAo;
    }

    public String getKieuNut() {
        return kieuNut;
    }

    public void setKieuNut(String kieuNut) {
        this.kieuNut = kieuNut;
    }

    public String getKieuTui() {
        return kieuTui;
    }

    public void setKieuTui(String kieuTui) {
        this.kieuTui = kieuTui;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
