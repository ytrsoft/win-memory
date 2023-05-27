package com.ytrsoft.win32;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private final WinNT.HANDLE processHandle;

    public Memory(int pid) {
        this.processHandle = openProcess(pid);
    }

    private WinNT.HANDLE openProcess(int pid) {
        WinNT.HANDLE processSnapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinNT.DWORD(0));
        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

        if (Kernel32.INSTANCE.Process32First(processSnapshot, processEntry)) {
            do {
                if (processEntry.th32ProcessID.intValue() == pid) {
                    return Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, true, pid);
                }
            } while (Kernel32.INSTANCE.Process32Next(processSnapshot, processEntry));
        }

        Kernel32.INSTANCE.CloseHandle(processSnapshot);
        throw new IllegalArgumentException("Unable to open process with PID: " + pid);
    }

    public int read(long address) {
        IntByReference readBuffer = new IntByReference(0);
        Pointer baseAddress = new Pointer(address);

        if (!Kernel32.INSTANCE.ReadProcessMemory(this.processHandle, baseAddress, readBuffer.getPointer(), Integer.BYTES, null)) {
            throw new RuntimeException("ReadProcessMemory failed. Error: " + Kernel32.INSTANCE.GetLastError());
        }

        return readBuffer.getValue();
    }

    public void write(long address, int value) {
        IntByReference writeBuffer = new IntByReference(value);
        Pointer baseAddress = new Pointer(address);

        if (!Kernel32.INSTANCE.WriteProcessMemory(this.processHandle, baseAddress, writeBuffer.getPointer(), Integer.BYTES, null)) {
            throw new RuntimeException("WriteProcessMemory failed. Error: " + Kernel32.INSTANCE.GetLastError());
        }
    }

    public List<Long> search(int value) {
        long address = 0;
        List<Long> addresses = new ArrayList<>();

        while (address < Integer.MAX_VALUE) {
            try {
                if (read(address) == value) {
                    addresses.add(address);
                }

                address += Integer.BYTES;
            } catch (RuntimeException e) {
                break;
            }
        }

        return addresses;
    }

    public void close() {
        if (this.processHandle != null) {
            Kernel32.INSTANCE.CloseHandle(this.processHandle);
        }
    }
}
