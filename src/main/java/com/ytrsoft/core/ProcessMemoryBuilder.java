package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.ytrsoft.win32.JUser32Api;

import java.util.LinkedList;
import java.util.List;

public class ProcessMemoryBuilder {

    private String className;

    private String windowName;

    private List<Integer> addrList = new LinkedList<>();

    public ProcessMemoryBuilder className(String className) {
        this.className = className;
        return this;
    }

    public ProcessMemoryBuilder windowName(String windowName) {
        this.windowName = windowName;
        return this;
    }

    public ProcessMemoryBuilder offset(int address) {
        this.addrList.add(address);
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
        int ret = 0;
        for (int offset: addrList) {
            ret = ret + offset;
        }
        return ret;
    }

}
