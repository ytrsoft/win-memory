package com.ytrsoft.core;

import com.ytrsoft.util.Memory;
import com.ytrsoft.win32.JKernel32Api;

public class ProcessMemory {

    private int pid;
    private int address;

    private static final int MEMORY_INT_SIZE = 4;

    ProcessMemory(int pid, int address) {
        this.pid = pid;
        this.address = address;
    }

    public int readInt() {
        int hp = JKernel32Api.openProcess(pid);
        if (hp <= 0) {
            return -1;
        }
        byte[] bytes = JKernel32Api.readProcessMemory(hp, address, MEMORY_INT_SIZE);
        if (bytes == null) {
            return -1;
        }
        JKernel32Api.closeHandle(hp);
        return Memory.bytesToUnsignedInt(bytes);
    }

    public boolean writeInt(int value) {
        int hp = JKernel32Api.openProcess(pid);
        if (hp <= 0) {
            return false;
        }
        int[] lpBuffer = new int[] { value };
        boolean ret = JKernel32Api.writeProcessMemory(hp, address, lpBuffer);
        JKernel32Api.closeHandle(hp);
        return ret;
    }

}
