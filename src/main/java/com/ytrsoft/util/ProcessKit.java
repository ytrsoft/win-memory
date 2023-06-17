package com.ytrsoft.util;

import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.entity.Process;

import java.util.ArrayList;
import java.util.List;

public final class ProcessKit {

    private ProcessKit() {
        throw new UnsupportedOperationException();
    }

    public static List<Process> forList() {
        List<Process> list = new ArrayList<>();
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD flag = Tlhelp32.TH32CS_SNAPPROCESS;
        WinNT.HANDLE handle = Kernel32Api.createToolhelp32Snapshot(flag, 0);
        if (Kernel32Api.process32First(handle, processEntry)) {
            while (Kernel32Api.process32Next(handle, processEntry)) {
                Process process = new Process();
                process.setName(new String((processEntry.szExeFile)).trim());
                process.setId(processEntry.th32ProcessID.intValue());
                list.add(process);
            }
        }
        return list;
    }
}
