package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "total_money", columnDefinition = "bigint default 0")
    private long totalMoney ;

    @OneToMany(mappedBy = "clientEntity")
    private List<OrderEntity> orderEntityList;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ClientSuitEntity clientSuitEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ClientTrousersEntity clientTrousersEntity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public ClientSuitEntity getClientSuitEntity() {
        return clientSuitEntity;
    }

    public void setClientSuitEntity(ClientSuitEntity clientSuitEntity) {
        this.clientSuitEntity = clientSuitEntity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ClientTrousersEntity getClientTrousersEntity() {
        return clientTrousersEntity;
    }

    public void setClientTrousersEntity(ClientTrousersEntity clientTrousersEntity) {
        this.clientTrousersEntity = clientTrousersEntity;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
