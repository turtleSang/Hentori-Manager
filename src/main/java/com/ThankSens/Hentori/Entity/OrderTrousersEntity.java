package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_trousers")
public class OrderTrousersEntity extends OrderItemEntity {
    @Column
    private String formQuan;

    @Column
    private String kieuLung;

    @Column
    private String kieuTuiTruoc;

    @Column
    private String kieuTuiSau;

    @Column
    private String soTui;

    @Column
    private String kieuLai;

    @Column(length = 10)
    private String fabric;

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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}
