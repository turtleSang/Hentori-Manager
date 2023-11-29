package com.ThankSens.Hentori.Entity;

import com.ThankSens.Hentori.Entity.Keys.KPIId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity(name = "kpi")
public class KPIEntity {
    @EmbeddedId
    private KPIId id;

    @Column(name = "target")
    private int target;

    @Column(name = "complete")
    private int complete;

    public KPIId getId() {
        return id;
    }

    public void setId(KPIId id) {
        this.id = id;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }
}
