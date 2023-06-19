package com.ytrsoft.gui.controller;

import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.view.process.ProcessNode;
import org.apache.commons.lang3.ObjectUtils;

public class ProcessTableController {

    public Process getProcess(Object value) {
        if (value instanceof ProcessNode) {
            ProcessNode treeNode = (ProcessNode) value;
            Object userObject = treeNode.getUserObject();
            if(userObject instanceof Process) {
                return (Process) userObject;
            }
        }
        return null;
    }

    public Object getValueAt(Object node, int column) {
        Process process = getProcess(node);
        if (ObjectUtils.isNotEmpty(process)) {
            if (column == 1) {
                return process.getId();
            }
        }
        return null;
    }
}
