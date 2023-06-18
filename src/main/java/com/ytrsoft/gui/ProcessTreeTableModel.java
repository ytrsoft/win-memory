package com.ytrsoft.gui;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class ProcessTreeTableModel extends DefaultTreeTableModel {

    public ProcessTreeTableModel(TreeTableNode root) {
        super(root);
    }

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
