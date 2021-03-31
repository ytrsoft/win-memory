package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.ytrsoft.win32.JKernel32Api;

public class ProcessMemoryImpl implements ProcessMemory {

    private int pid;
    private int address;

    public ProcessMemoryImpl(int pid, int address) {
        this.pid = pid;
        this.address = address;
    }

    @Override
    public int read() {
        HANDLE handle = JKernel32Api.openProcess(pid);
        int ret = JKernel32Api.readProcessMemory(handle, address);
        JKernel32Api.closeHandle(handle);
        return ret;
    }

    @Override
    public void write(int value) {
        HANDLE handle = JKernel32Api.openProcess(pid);
        JKernel32Api.writeProcessMemory(handle, address, value);
        JKernel32Api.closeHandle(handle);
    }

}
