package com.ytrsoft.core;

public class Found<T> {
    private long address;
    private T value;

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

