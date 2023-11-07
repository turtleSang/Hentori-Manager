package com.ThankSens.Hentori.Payload.Request.Order;

public class OrderSuitRequest {
    private String kieuAo;
    private String formAo;
    private String kieuVeAo;
    private String lotAo;
    private String kieuNut;
    private String kieuTui;
    private long price;
    private int amount;

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
