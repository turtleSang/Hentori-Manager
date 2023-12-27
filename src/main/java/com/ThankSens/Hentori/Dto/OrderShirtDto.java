package com.ThankSens.Hentori.Dto;

public class OrderShirtDto {
    private int id;
    private String kieuCo;
    private String mangSec;
    private String kieuDinh;
    private String note;
    private String fabric;
    private int price;
    private int amount;

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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
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
}
