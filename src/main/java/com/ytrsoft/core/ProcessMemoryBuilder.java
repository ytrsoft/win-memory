package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.ytrsoft.win32.JUser32Api;


public class ProcessMemoryBuilder {

    private String className;

    private String windowName;

    private int address = 0X00000000;

    private int offset = 0X00000000;

    public ProcessMemoryBuilder address(int address) {
        this.address = address;
        return this;
    }

    public ProcessMemoryBuilder offset(int offset) {
        this.offset = offset;
        return this;
    }

    public ProcessMemoryBuilder className(String className) {
        this.className = className;
        return this;
    }

    public ProcessMemoryBuilder windowName(String windowName) {
        this.windowName = windowName;
        return this;
    }

    public ProcessMemory build() {
        return new ProcessMemoryImpl(getPId(), getAddress());
    }

    private int getPId() {
        HWND hWnd = JUser32Api.findWindow(className, windowName);
        return JUser32Api.getWindowThreadProcessId(hWnd);
    }

    private int getAddress() {
        return address + offset;
    }

}
