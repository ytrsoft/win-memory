package com.ytrsoft.simplified;

import com.sun.jna.platform.win32.Kernel32;

public class ByteEx {

    private final byte[] base;

    public ByteEx() {
        this(Kernel32.MAX_PATH);
    }

    public ByteEx(int size) {
        base = new byte[size];
    }

    public int size() {
        return base.length;
    }

    public byte[] get() {
        return base;
    }

    @Override
    public String toString() {
        return new String(base).trim();
    }
}
