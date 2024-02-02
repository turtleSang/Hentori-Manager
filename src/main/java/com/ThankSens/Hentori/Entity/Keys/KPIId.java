package com.ThankSens.Hentori.Entity.Keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class KPIId implements Serializable {
    private int month;
    private int year;

    public KPIId(int month, int year) {
        if (month > 12 || month < 1 || year < 1900){
            return;
        }
        this.month = month;
        this.year = year;
    }

    public KPIId() {

    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month > 12 || month < 1 ){
            return;
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900){
            return;
        }
        this.year = year;
    }
}
