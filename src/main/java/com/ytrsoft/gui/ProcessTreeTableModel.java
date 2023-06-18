package com.ytrsoft.gui;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class ProcessTreeTableModel extends DefaultTreeTableModel {
    @Override
    public Object getValueAt(Object node, int column) {
        return "Column-" + column;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }
}
