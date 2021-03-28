package com.ytrsoft.interfaces;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public class JKernel32 {

    public interface Kernel32 extends StdCallLibrary {

        int ACCESS_FLAGS = 0X0439;

        int PROCESS_QUERY_INFORMATION = 0X0400;

        int PROCESS_VM_READ = 0X0010;

        int PROCESS_VM_WRITE = 0X0020;

        int PROCESS_VM_OPERATION = 0X0008;

        int PROCESS_ALL_ACCESS = 0X001F0FFF;

        int FORMAT_MESSAGE_FORM_SYSTEM = 4096;

        int FORMAT_MESSAGE_IGNORE_INSERTS = 512;

        Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class);
        Kernel32 SYNC_INSTANCE = (Kernel32)Native.synchronizedLibrary(INSTANCE);

        int GetLastError();

        int FormatMessageW(
            int dwFlags,
            Pointer lpSource,
            int dwMessageId,
            int dwLanguageId,
            char[] lpBuffer,
            int nSize,
            Pointer arguments
        );

        int OpenProcess(
            int dwDesiredAccess,
            int bInheritHandle,
            int dwProcessId
        );

        boolean CloseHandle(int hObject);

        int WriteProcessMemory(
            int hProcess,
            int lpBaseAddress,
            int[] lpBuffer,
            int nSize,
            int[] lpNumberOfBytesWritten
        );

        boolean ReadProcessMemory(
            int hProcess,
            int baseAddress,
            Pointer outputBuffer,
            int nSize,
            IntByReference outNumberOfBytesRead
        );

    }


    public static String getLastError() {
        char[] lpBuffer = new char[1024];
        int dwMessageId = Kernel32.SYNC_INSTANCE.GetLastError();
        int dwFlags = Kernel32.FORMAT_MESSAGE_FORM_SYSTEM |
                      Kernel32.FORMAT_MESSAGE_IGNORE_INSERTS;
        int lenW = Kernel32.SYNC_INSTANCE.FormatMessageW(
            dwFlags,
            null,
            dwMessageId,
            0,
            lpBuffer,
            lpBuffer.length,
            null
        );
        return new String(lpBuffer, 0, lenW);
    }

    public static int openProcess(int dwProcessId) {
        int dwDesiredAccess = Kernel32.PROCESS_VM_OPERATION |
                              Kernel32.PROCESS_VM_READ |
                              Kernel32.PROCESS_VM_WRITE;
        return Kernel32.SYNC_INSTANCE.OpenProcess(
            dwDesiredAccess,
            0,
            dwProcessId
        );
    }

    public static boolean closeHandle(int hObject) {
        return Kernel32.SYNC_INSTANCE.CloseHandle(hObject);
    }

    public static boolean writeProcessMemory(int hProcess, int lpBaseAddress, int lpBuffer[]) {
        boolean status = true;
        for (int i = 0; i < lpBuffer.length; i++) {
            int[] singleValue = new int[] { lpBuffer[i] };
            int valueLen = Integer.toHexString(singleValue[0]).length();
            int ret = Kernel32.SYNC_INSTANCE.WriteProcessMemory(
                hProcess,
                lpBaseAddress + i,
                singleValue,
                valueLen,
                null
            );
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
        boolean success = Kernel32.SYNC_INSTANCE.ReadProcessMemory(
            hProcess,
            lpBaseAddress,
            lpBuffer,
            nSize,
            null
        );
        if (success) {
            return lpBuffer.getByteArray(0, nSize);
        }
        return null;
    }
}
