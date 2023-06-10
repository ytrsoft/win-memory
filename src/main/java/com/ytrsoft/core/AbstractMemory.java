package com.ytrsoft.core;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;

public abstract class AbstractMemory implements AutoCloseable {

    protected final WinNT.HANDLE processHandle;
    protected final long START_HEX = 0x0000000000000000L;
    protected final long STOP_HEX = 0x00007FFFFFFFFFFFL;

    protected AbstractMemory(int pid) {
        this.processHandle = openProcess(pid);
    }

    private WinNT.HANDLE openProcess(int pid) {
        return Kernel32.INSTANCE.OpenProcess(
            WinNT.PROCESS_ALL_ACCESS,
            true,
            pid
        );
    }

    public WinNT.HANDLE getProcessHandle() {
        return processHandle;
    }

    @Override
    public void close()  {
        Kernel32.INSTANCE.CloseHandle(this.processHandle);
    }
}
