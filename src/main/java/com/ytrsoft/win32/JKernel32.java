package com.ytrsoft.win32;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;

public interface JKernel32 extends Kernel32 {

    JKernel32 SYNC_INSTANCE = (JKernel32) Win32Native.synchronizedLibrary("kernel32", JKernel32.class);

    boolean ReadProcessMemory(HANDLE hProcess, int lpBaseAddress, IntByReference lpBuffer, int nSize, IntByReference lpNumberOfBytesRead);

    boolean WriteProcessMemory(HANDLE hProcess, int lpBaseAddress, IntByReference lpBuffer, int nSize, IntByReference lpNumberOfBytesWritten);

}
