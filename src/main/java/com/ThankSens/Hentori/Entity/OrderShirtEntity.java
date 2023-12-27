package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_shirt")
public class OrderShirtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String kieuCo;

    @Column
    private String mangSec;

    @Column
    private String kieuDinh;

    @Column
    private String note;

    @Column
    private String fabric;

    @Column
    private int price;

    @Column
    private int amount;

    @Column
    private int total;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKieuCo() {
        return kieuCo;
    }

    public void setKieuCo(String kieuCo) {
        this.kieuCo = kieuCo;
    }

    public String getMangSec() {
        return mangSec;
    }

    public void setMangSec(String mangSec) {
        this.mangSec = mangSec;
    }

    public String getKieuDinh() {
        return kieuDinh;
    }

    public void setKieuDinh(String kieuDinh) {
        this.kieuDinh = kieuDinh;
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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}
