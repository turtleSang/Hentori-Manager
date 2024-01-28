package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_accessory")
public class OrderAccessoryEntity extends OrderItemEntity {
    @Column
    private String nameAccessory;

    public String getNameAccessory() {
        return nameAccessory;
    }

    public void setNameAccessory(String nameAccessory) {
        this.nameAccessory = nameAccessory;
    }
}
