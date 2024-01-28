package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_suit")
public class OrderSuitEntity extends OrderItemEntity {

    @Column
    private String kieuAo;

    @Column
    private String formAo;

    @Column
    private String kieuVeAo;

    @Column
    private String kieuXe;

    @Column
    private String lotAo;

    @Column
    private String kieuNut;

    @Column
    private String kieuTui;

    @Column(length = 10)
    private String fabric;

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

    public String getKieuXe() {
        return kieuXe;
    }

    public void setKieuXe(String kieuXe) {
        this.kieuXe = kieuXe;
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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}
