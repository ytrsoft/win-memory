package com.ytrsoft.exceptions;

public class MemoryException extends Exception {

    public MemoryException(String tag, String msg) {
        super(String.format("Kernel32 %s failed: %s", tag, msg));
    }

}
