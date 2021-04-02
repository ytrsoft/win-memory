package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.ytrsoft.win32.JUser32Api;

import java.util.LinkedList;
import java.util.List;

public class ProcessMemoryBuilder {

    private String className;

    private String windowName;

    private int address;

    public ProcessMemoryBuilder address(int address) {
        this.address = address;
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
        return new ProcessMemoryImpl(getPId(), address);
    }

    private int getPId() {
        HWND hWnd = JUser32Api.findWindow(className, windowName);
        return JUser32Api.getWindowThreadProcessId(hWnd);
    }

}
