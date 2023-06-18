package com.ytrsoft.gui;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;


import com.ytrsoft.entity.Process;

public class ProcessTreeTableModel extends DefaultTreeTableModel {

    private static final String[] COLUMN_NAMES = {"名称", "PID", "操作"};

    public ProcessTreeTableModel(TreeTableNode root) {
        super(root);
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof DefaultMutableTreeTableNode) {
            DefaultMutableTreeTableNode treeNode = (DefaultMutableTreeTableNode) node;
            Object userObject = treeNode.getUserObject();
            if(userObject instanceof Process) {
                Process process = (Process) userObject;
                if (column == 1) {
                    return process.getId();
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }
}
