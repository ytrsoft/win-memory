package com.ytrsoft.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String name) {
        super(String.format("not found for %s", name));
    }
}
