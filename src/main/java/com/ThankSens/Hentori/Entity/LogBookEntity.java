package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "log_book")
public class LogBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "visit_date")
    private Date visitDate;

    @Column(name = "demand")
    private String demand;

    @Column(name = "approach")
    private String approach;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
