package com.ytrsoft.gui.view.process;

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
        if (node instanceof ProcessNode) {
            System.out.println(node);
            ProcessNode treeNode = (ProcessNode) node;
            Object userObject = treeNode.getUserObject();
            System.out.println(userObject.getClass().getPackageName());
//            if(userObject instanceof Process) {
//                System.out.println(userObject.toString());
//                Process process = (Process) userObject;
//                if (column == 1) {
//                    return process.getId();
//                }
//                return null;
//            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return column == 2;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
}
