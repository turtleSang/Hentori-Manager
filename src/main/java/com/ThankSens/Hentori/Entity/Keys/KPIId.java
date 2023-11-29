package com.ThankSens.Hentori.Entity.Keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class KPIId implements Serializable {
    private int month;
    private int year;

    public KPIId(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
