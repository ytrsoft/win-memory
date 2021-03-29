package com.ytrsoft.win32;

import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;

public final class JKernel32Api {

    private JKernel32Api() {
        throw new UnsupportedOperationException();
    }

    public static int openProcess(int dwProcessId) {
        return JKernel32.SYNC_INSTANCE.OpenProcess(JKernel32.PROCESS_ALL_ACCESS,
                0, dwProcessId);
    }

    public static boolean closeHandle(int hObject) {
        return JKernel32.SYNC_INSTANCE.CloseHandle(hObject);
    }

    public static boolean writeProcessMemory(int hProcess, int lpBaseAddress, int lpBuffer[]) {
        boolean status = true;
        int offset = 0x00000001;
        for (int i = 0; i < lpBuffer.length; i++) {
            int[] singleValue = new int[] { lpBuffer[i] };
            int valueLength = Integer.toHexString(singleValue[0]).length();
            int ret = JKernel32.SYNC_INSTANCE.WriteProcessMemory(hProcess,
                     lpBaseAddress + i * offset, singleValue,
                      valueLength, null);
            if (ret <= 0) {
                status = false;
            }
        }
        return status;
    }

    public static byte[] readProcessMemory(int hProcess, int lpBaseAddress, int nSize) {
        IntByReference baseAddress = new IntByReference();
        baseAddress.setValue(lpBaseAddress);
        Memory lpBuffer = new Memory(nSize);
        boolean status = JKernel32.SYNC_INSTANCE.ReadProcessMemory(hProcess,
                         lpBaseAddress, lpBuffer, nSize, null);
        if (status) {
            return lpBuffer.getByteArray(0, nSize);
        }
        return null;
    }

}
