package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinDef;
import com.ytrsoft.win32.JUser32Api;

import java.util.LinkedList;
import java.util.List;

public class ProcessMemoryBuilder {

    private String appName;

    private String titleName;

    private List<Integer> addressList = new LinkedList<>();

    public ProcessMemoryBuilder appName(String appName) {
        this.appName = appName;
        return this;
    }

    public ProcessMemoryBuilder titleName(String titleName) {
        this.titleName = titleName;
        return this;
    }

    public ProcessMemoryBuilder appendAddress(Integer address) {
        addressList.add(address);
        return this;
    }

    private int getAddress() {
        int address = 0;
        for (int i = 0; i < addressList.size() ; i++) {
            address = address + addressList.get(i);
        }
        return address;
    }

    private int getPid() {
        WinDef.HWND hWnd = JUser32Api.findWindow(appName, titleName);
        return JUser32Api.getWindowThreadProcessId(hWnd);
    }

    public ProcessMemory build() {
        return new ProcessMemory(getPid(), getAddress());
    }

}
