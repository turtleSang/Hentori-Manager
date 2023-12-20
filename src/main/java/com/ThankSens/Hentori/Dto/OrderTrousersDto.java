package com.ThankSens.Hentori.Dto;

public class OrderTrousersDto {
    private int id;
    private String formQuan;
    private String kieuLung;
    private String kieuTuiTruoc;
    private String kieuTuiSau;
    private String soTui;
    private String kieuLai;
    private String fabric;
    private int price;
    private int amount;
    private String note;
    private int total;

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
