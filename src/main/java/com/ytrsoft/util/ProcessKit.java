package com.ytrsoft.util;

import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.entity.Process;
import com.ytrsoft.util.icon.JIconExtract;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

public final class ProcessKit {

    private static final int ICON_SIZE = 24;

    private ProcessKit() {
        throw new UnsupportedOperationException();
    }

    private static ImageIcon getIcon(int pid) {
        WinNT.HANDLE pHandle = Kernel32Api.openProcess(pid);
        String fileName = PsapiApi.getModuleFileName(pHandle);
        BufferedImage img = JIconExtract.getIconForFile(ICON_SIZE, ICON_SIZE, fileName);
        return img != null ? new ImageIcon(img) : null;
    }

    public static List<Process> forList() {
        Map<Integer, Process> processMap = new HashMap<>();
        List<Process> roots = new ArrayList<>();
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        WinDef.DWORD flag = Tlhelp32.TH32CS_SNAPPROCESS;
        WinNT.HANDLE handle = Kernel32Api.createToolhelp32Snapshot(flag, 0);
        if (Kernel32Api.process32First(handle, processEntry)) {
            do {
                Process process = new Process();
                process.setName(new String((processEntry.szExeFile)).trim());
                process.setId(processEntry.th32ProcessID.intValue());
                ImageIcon icon = getIcon(process.getId());
                process.setIcon(icon);
                processMap.put(process.getId(), process);
            } while (Kernel32Api.process32Next(handle, processEntry));
        }
        Kernel32Api.process32First(handle, processEntry);
        do {
            int currentProcessId = processEntry.th32ProcessID.intValue();
            int parentId = processEntry.th32ParentProcessID.intValue();
            Process currentProcess = processMap.get(currentProcessId);
            Process parentProcess = processMap.get(parentId);
            if (parentProcess != null) {
                if (parentProcess.getChildren() == null) {
                    parentProcess.setChildren(new ArrayList<>());
                }
                parentProcess.getChildren().add(currentProcess);
            } else {
                roots.add(currentProcess);
            }
        } while (Kernel32Api.process32Next(handle, processEntry));
        return roots;
    }

}
