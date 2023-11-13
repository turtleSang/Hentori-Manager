package com.ThankSens.Hentori.Dto;


public class OrderAccessoryDto {
    private int id;
    private String nameAccessory;
    private int price;
    private int amount;
    private int total;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAccessory() {
        return nameAccessory;
    }

    public void setNameAccessory(String nameAccessory) {
        this.nameAccessory = nameAccessory;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
