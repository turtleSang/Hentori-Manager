package com.ThankSens.Hentori.Entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class WaitingAdminEntity {

    @Id
    private String username;

    @Column
    private String password;

    @Column
    private Date timeDelete;

    @Column
    private int otp;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeDelete() {
        return timeDelete;
    }

    public void setTimeDelete(Date timeDelete) {
        this.timeDelete = timeDelete;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }
}
