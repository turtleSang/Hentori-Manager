package com.ThankSens.Hentori.Payload.Request.Order;

public class OrderSuitRequest {
    private String kieuAo;
    private String formAo;
    private String kieuVeAo;
    private String kieuXe;
    private String lotAo;
    private String kieuNut;
    private String kieuTui;
    private int price;
    private int amount;
    private String fabric;
    private String note;

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKieuXe() {
        return kieuXe;
    }

    public void setKieuXe(String kieuXe) {
        this.kieuXe = kieuXe;
    }
}
