package com.ytrsoft.gui.controller;

import com.ytrsoft.core.ProcessSupport;
import com.ytrsoft.entity.Process;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private final List<Process> processes;

    public AppController() {
        processes = new ProcessSupport().getProcessTree();
    }

    public List<Process> filterProcesses(String pid) {
        return filterProcesses(pid, processes);
    }

    public List<Process> filterProcesses(String pid, List<Process> processList) {
        List<Process> filteredProcesses = new ArrayList<>();
        if (ObjectUtils.isEmpty(processList)) {
            return filteredProcesses;
        }
        for(Process process : processList) {
            if(String.valueOf(process.getId()).contains(pid)) {
                filteredProcesses.add(process);
            }
            List<Process> children = process.getChildren();
            if(ObjectUtils.isNotEmpty(children)) {
                List<Process> filteredChildren = filterProcesses(pid, children);
                filteredProcesses.addAll(filteredChildren);
            }
        }
        return filteredProcesses;
    }

    public List<Process> getProcesses() {
        return processes;
    }
}
