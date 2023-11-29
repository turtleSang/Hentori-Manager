package com.ThankSens.Hentori.Dto;

import com.ThankSens.Hentori.Entity.Keys.KPIId;

public class KPIDto {
    private KPIId id;
    private int target;
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
