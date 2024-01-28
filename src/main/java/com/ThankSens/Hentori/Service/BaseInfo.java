package com.ThankSens.Hentori.Service;

public class BaseInfo<T> {
    private T item;

    public BaseInfo(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
