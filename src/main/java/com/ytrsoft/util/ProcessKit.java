package com.ytrsoft.util;

import com.sun.jna.platform.win32.Tlhelp32;
import com.ytrsoft.entity.Process;
import com.ytrsoft.simplified.NTHandle;
import com.ytrsoft.util.api.Kernel32Api;
import com.ytrsoft.util.api.PsapiApi;
import com.ytrsoft.util.icon.IconExtract;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProcessKit {

    private static final int ICON_SIZE = 24;

    private ProcessKit() {
        throw new UnsupportedOperationException();
    }

    private static ImageIcon getIcon(int pid) {
        NTHandle handle = Kernel32Api.openProcess(pid);
        String fileName = PsapiApi.getModuleName(handle);
        BufferedImage img = IconExtract.getIconForFile(
            ICON_SIZE,
            ICON_SIZE,
            fileName
        );
        return img != null ? new ImageIcon(img) : null;
    }

    public static List<Process> forList() {
        Map<Integer, Process> processMap = new HashMap<>();
        List<Process> roots = new ArrayList<>();
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        long flags = Tlhelp32.TH32CS_SNAPPROCESS.longValue();
        NTHandle handle = Kernel32Api.createToolhelp32Snapshot(flags, 0);
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
