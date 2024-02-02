package com.ThankSens.Hentori.Entity;

import com.ThankSens.Hentori.Entity.Keys.KPIId;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "kpi")
public class KPIEntity {
    @EmbeddedId
    private KPIId id;

    @Column(name = "target")
    private double target;

    public KPIId getId() {
        return id;
    }

    public void setId(KPIId id) {
        this.id = id;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}
