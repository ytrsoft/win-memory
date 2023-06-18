package com.ytrsoft.gui;

import com.ytrsoft.entity.Process;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

public class ProcessNode extends DefaultMutableTreeTableNode {

    public ProcessNode() {}

    public ProcessNode(Process process) {
        super(process);
    }

}
