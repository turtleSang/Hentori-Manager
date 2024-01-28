package com.ThankSens.Hentori.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;

public class KPIEntity {
    @EmbeddedId
    private KPIEntity kpiEntity;

    private int KPI;

    public KPIEntity getKpiEntity() {
        return kpiEntity;
    }

    public void setKpiEntity(KPIEntity kpiEntity) {
        this.kpiEntity = kpiEntity;
    }

    public int getKPI() {
        return KPI;
    }

    public void setKPI(int KPI) {
        this.KPI = KPI;
    }
}
