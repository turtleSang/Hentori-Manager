package com.ThankSens.Hentori.Dto;


public class OrderSuitDto {
    private int id;
    private String kieuAo;
    private String formAo;
    private String kieuVeAo;
    private String lotAo;
    private String kieuNut;
    private String kieuTui;
    private int price;
    private int amount;
    private int total;

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
}
