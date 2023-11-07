package com.ThankSens.Hentori.Payload.Request.Order;

import jakarta.persistence.Column;

public class OrderTrousersRequest {
    
    private String formQuan;
    private String kieuLung;
    private String lotAo;
    private String kieuTuiTruoc;
    private String kieuTuiSau;
    private String soTui;
    private String kieuLai;
    private String price;
    private String amount;
    private String note;

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
}
