package com.ytrsoft.gui;

import org.jdesktop.swingx.JXTreeTable;

import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ProcessTreeTable extends JXTreeTable {
    public ProcessTreeTable(ProcessTreeTableModel model) {
        super(model);
        setRowHeight(24);
        setFocusable(false);
        setDefaultRenderer(Object.class, new ProcessCellRenderer());
        setTreeCellRenderer(new ProcessTreeCellRenderer());
        getColumn(2).setCellEditor(new ProcessCellEditor());
    }
}
