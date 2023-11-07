package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_trousers")
public class OrderTrousersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String formQuan;

    @Column
    private String kieuLung;

    @Column
    private String lotAo;

    @Column
    private String kieuTuiTruoc;

    @Column
    private String kieuTuiSau;

    @Column
    private String soTui;

    @Column
    private String kieuLai;

    @Column
    private String price;

    @Column
    private String amount;

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

    public String getFormQuan() {
        return formQuan;
    }

    public void setFormQuan(String formQuan) {
        this.formQuan = formQuan;
    }

    public String getKieuLung() {
        return kieuLung;
    }

    public void setKieuLung(String kieuLung) {
        this.kieuLung = kieuLung;
    }

    public String getLotAo() {
        return lotAo;
    }

    public void setLotAo(String lotAo) {
        this.lotAo = lotAo;
    }

    public String getKieuTuiTruoc() {
        return kieuTuiTruoc;
    }

    public void setKieuTuiTruoc(String kieuTuiTruoc) {
        this.kieuTuiTruoc = kieuTuiTruoc;
    }

    public String getKieuTuiSau() {
        return kieuTuiSau;
    }

    public void setKieuTuiSau(String kieuTuiSau) {
        this.kieuTuiSau = kieuTuiSau;
    }

    public String getSoTui() {
        return soTui;
    }

    public void setSoTui(String soTui) {
        this.soTui = soTui;
    }

    public String getKieuLai() {
        return kieuLai;
    }

    public void setKieuLai(String kieuLai) {
        this.kieuLai = kieuLai;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
