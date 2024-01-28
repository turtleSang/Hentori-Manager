package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_shirt")
public class OrderShirtEntity extends OrderItemEntity {

    @Column
    private String kieuCo;

    @Column
    private String mangSec;

    @Column
    private String kieuDinh;

    @Column
    private String fabric;

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

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}
