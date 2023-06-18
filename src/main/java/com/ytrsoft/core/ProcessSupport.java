package com.ytrsoft.core;

import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.entity.Process;
import com.ytrsoft.util.Constants;
import com.ytrsoft.util.api.Kernel32Api;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class ProcessSupport {

    private final Map<Integer, Process> processMap = new HashMap<>();
    private final Map<String, ImageIcon> iconCache = new HashMap<>();

    private ImageIcon getIcon(int pid, String processName) {
        if (iconCache.containsKey(processName)) {
            return iconCache.get(processName);
        }
        int size = Constants.PROCESS_TABLE_ICON_SIZE;
        HandleManager hm = new HandleManager(pid);
        BufferedImage icon = hm.getIcon(size, size);
        ImageIcon imageIcon = null;
        if (ObjectUtils.isNotEmpty(icon)) {
          imageIcon = new ImageIcon(icon);
          iconCache.put(processName, imageIcon);
        }
        return imageIcon;
    }

    private Process createProcess(Tlhelp32.PROCESSENTRY32 processEntry) {
        Process process = new Process();
        String processName = new String((processEntry.szExeFile)).trim();
        process.setName(processName);
        process.setId(processEntry.th32ProcessID.intValue());
        process.setParentId(processEntry.th32ParentProcessID.intValue());
        ImageIcon icon = getIcon(process.getId(), processName);
        process.setIcon(icon);
        return process;
    }

    private void loadProcessMap() {
        Tlhelp32.PROCESSENTRY32 processEntry = new Tlhelp32.PROCESSENTRY32();
        long flags = Tlhelp32.TH32CS_SNAPPROCESS.longValue();
        WinNT.HANDLE handle = Kernel32Api.createToolhelp32Snapshot(flags, 0);
        if (Kernel32Api.process32First(handle, processEntry)) {
            do {
                Process process = createProcess(processEntry);
                processMap.put(process.getId(), process);
            } while (Kernel32Api.process32Next(handle, processEntry));
        }
    }

    public List<Process> getProcessTree() {
        loadProcessMap();
        List<Process> roots = new ArrayList<>();
        for (Process process : processMap.values()) {
            Process parentProcess = processMap.get(process.getParentId());
            if (ObjectUtils.isNotEmpty(parentProcess)) {
                if (ObjectUtils.isEmpty(parentProcess.getChildren())) {
                    parentProcess.setChildren(new ArrayList<>());
                }
                parentProcess.getChildren().add(process);
            } else {
                roots.add(process);
            }
        }
        processMap.clear();
        return roots;
    }
}
