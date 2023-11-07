package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "client_trousers")
public class ClientTrousersEntity {
    @Id
    @Column(name = "client_id")
    private UUID id;

    @Column
    private String vongLung;
    @Column
    private String ngangBung;
    @Column
    private String vongDay;
    @Column
    private String duiGiua;
    @Column
    private String vongMong;
    @Column
    private String daiQuan;
    @Column
    private String vongDui;
    @Column
    private String vongGoi;
    @Column
    private String vongBapChan;
    @Column
    private String ongQuan;

    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVongLung() {
        return vongLung;
    }

    public void setVongLung(String vongLung) {
        this.vongLung = vongLung;
    }

    public String getNgangBung() {
        return ngangBung;
    }

    public void setNgangBung(String ngangBung) {
        this.ngangBung = ngangBung;
    }

    public String getVongDay() {
        return vongDay;
    }

    public void setVongDay(String vongDay) {
        this.vongDay = vongDay;
    }

    public String getDuiGiua() {
        return duiGiua;
    }

    public void setDuiGiua(String duiGiua) {
        this.duiGiua = duiGiua;
    }

    public String getVongMong() {
        return this.vongMong;
    }

    public void setVongMong(String vongMong) {
        this.vongMong = vongMong;
    }

    public String getDaiQuan() {
        return daiQuan;
    }

    public void setDaiQuan(String daiQuan) {
        this.daiQuan = daiQuan;
    }

    public String getVongDui() {
        return vongDui;
    }

    public void setVongDui(String vongDui) {
        this.vongDui = vongDui;
    }

    public String getVongGoi() {
        return vongGoi;
    }

    public void setVongGoi(String vongGoi) {
        this.vongGoi = vongGoi;
    }

    public String getVongBapChan() {
        return vongBapChan;
    }

    public void setVongBapChan(String vongBapChan) {
        this.vongBapChan = vongBapChan;
    }

    public String getOngQuan() {
        return ongQuan;
    }

    public void setOngQuan(String ongQuan) {
        this.ongQuan = ongQuan;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
