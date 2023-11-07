package com.ThankSens.Hentori.Payload.Response;

import org.aspectj.bridge.IMessage;

public class ResponseData {
    private boolean check;
    private String messenger;
    private Object object;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ResponseData() {
    }
}
