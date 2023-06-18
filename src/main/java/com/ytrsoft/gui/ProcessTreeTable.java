package com.ytrsoft.gui;

import org.jdesktop.swingx.JXTreeTable;

public class ProcessTreeTable extends JXTreeTable {
    public ProcessTreeTable(ProcessTreeTableModel model) {
        super(model);
        setFillsViewportHeight(false);
    }
    @Override
    public String getToolTipText() {
        return "Tooltip";
    }
}