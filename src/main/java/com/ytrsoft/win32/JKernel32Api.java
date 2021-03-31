package com.ytrsoft.win32;

import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

public final class JKernel32Api {

    private static final int INT_SIZE = 4;

    private JKernel32Api() {
        throw new UnsupportedOperationException();
    }

    public static boolean closeHandle(HANDLE handle) {
        return JKernel32.SYNC_INSTANCE.CloseHandle(handle);
    }

    public static HANDLE openProcess(int pid) {
        HANDLE handle = JKernel32.SYNC_INSTANCE.OpenProcess(
                JKernel32.PROCESS_ALL_ACCESS, false, pid);
        if (handle == null || handle.getPointer() == null) {
            throw new Win32Exception(JKernel32.SYNC_INSTANCE.GetLastError());
        }
        return handle;
    }

    public static int readProcessMemory(HANDLE handle, int address) {
        IntByReference lpBuffer = new IntByReference(0);
        boolean status = JKernel32.SYNC_INSTANCE.ReadProcessMemory(handle, address,
                lpBuffer, INT_SIZE, null);
        if (!status) {
            throw new Win32Exception(JKernel32.SYNC_INSTANCE.GetLastError());
        }
        return lpBuffer.getValue();
    }

    public static void writeProcessMemory(HANDLE handle, int address, int value) {
        IntByReference lpBuffer = new IntByReference(value);
        boolean status = JKernel32.SYNC_INSTANCE.WriteProcessMemory(handle, address,
                lpBuffer, INT_SIZE, null);
        if (!status) {
            throw new Win32Exception(JKernel32.SYNC_INSTANCE.GetLastError());
        }
    }

}
