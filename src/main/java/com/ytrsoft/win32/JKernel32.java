package com.ytrsoft.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;

public interface JKernel32 extends Kernel32 {

    int PROCESS_ALL_ACCESS = 0x001F0FFF;

    int FORMAT_MESSAGE_FROM_SYSTEM = 4096;

    int FORMAT_MESSAGE_IGNORE_INSERTS = 512;

    JKernel32 INSTANCE = Native.load("kernel32", JKernel32.class);

    JKernel32 SYNC_INSTANCE = (JKernel32) Native.synchronizedLibrary(INSTANCE);

    int GetLastError();

    int FormatMessageW(int dwFlags, Pointer lpSource, int dwMessageId, int dwLanguageId, char[] lpBuffer, int nSize, Pointer Arguments);

    int OpenProcess(int dwDesiredAccess, int bInheritHandle, int dwProcessId);

    boolean CloseHandle(int hObject);

    int WriteProcessMemory(int hProcess, int lpBaseAddress, int[] lpBuffer, int nSize, int[] lpNumberOfBytesWritten);

    boolean ReadProcessMemory(int hProcess, int baseAddress, Pointer outputBuffer, int nSize, IntByReference outNumberOfBytesRead);

}
