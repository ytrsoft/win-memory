package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.ytrsoft.exceptions.MemoryException;
import com.ytrsoft.exceptions.NotFoundException;
import com.ytrsoft.interfaces.JKernel32;
import com.ytrsoft.interfaces.JUser32;

public class ProcessMemory implements Active, Readable, Writeable {

    private int pid;

    public ProcessMemory(int pid) {
        this.pid = pid;
    }

    public ProcessMemory(String name) {
        try {
            this.pid = getPIdByName(name);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getPIdByName(String name) throws NotFoundException {
        HWND hWnd = JUser32.findWindow(name, null);
        if (hWnd == null) {
            throw new NotFoundException(name);
        }
        return JUser32.getWindowThreadProcessId(hWnd);
    }

    private void throwMemoryException(String tag) throws MemoryException {
        throw new MemoryException(tag, JKernel32.getLastError());
    }

    @Override
    public byte[] read(int address, int size) {
        byte[] ret = null;
        try {
            int hp = JKernel32.openProcess(pid);
            if (hp <= 0) {
                throwMemoryException("OpenProcess");
            }
            ret = JKernel32.readProcessMemory(hp, address, size);
            if (ret == null) {
                throwMemoryException("ReadProcessMemory");
            }
            JKernel32.closeHandle(hp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public boolean write(int address, int[] buffer) {
        boolean status = false;
        try {
            int hp = JKernel32.openProcess(pid);
            if (hp <= 0) {
                throwMemoryException("OpenProcess");
            }
            status = JKernel32.writeProcessMemory(hp, address, buffer);
            if (!status) {
                throwMemoryException("WriteProcessMemory");
            }
            JKernel32.closeHandle(hp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean isActive() {
        return JKernel32.openProcess(pid) > 0;
    }
}
