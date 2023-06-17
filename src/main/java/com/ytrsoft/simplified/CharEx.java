package com.ytrsoft.simplified;

import com.sun.jna.platform.win32.Kernel32;

public class CharEx {

    private final char[] base;

    public CharEx() {
        this(Kernel32.MAX_PATH);
    }

    public CharEx(int size) {
        base = new char[size];
    }

    public int size() {
        return base.length;
    }

    public char[] get() {
        return base;
    }

    @Override
    public String toString() {
        return new String(base).trim();
    }
}
