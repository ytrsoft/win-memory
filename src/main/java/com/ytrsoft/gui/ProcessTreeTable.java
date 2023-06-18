package com.ytrsoft.gui;

import org.jdesktop.swingx.JXTreeTable;

public class ProcessTreeTable extends JXTreeTable {
    public ProcessTreeTable(ProcessTreeTableModel model) {
        super(model);
        setRowHeight(24);
        setFocusable(false);
        setEditable(false);
        setDefaultRenderer(Object.class, new ProcessCellRenderer());
        setTreeCellRenderer(new ProcessTreeCellRenderer());
    }
}
