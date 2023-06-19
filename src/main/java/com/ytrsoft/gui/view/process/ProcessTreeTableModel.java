package com.ytrsoft.gui.view.process;

import com.ytrsoft.gui.controller.ProcessTableController;
import com.ytrsoft.util.Constants;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class ProcessTreeTableModel extends DefaultTreeTableModel {

    private final ProcessTableController controller = new ProcessTableController();

    public ProcessTreeTableModel(TreeTableNode root) {
        super(root);
    }

    @Override
    public Object getValueAt(Object node, int column) {
        return controller.getValueAt(node, column);
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return column == Constants.PROCESS_TABLE_BTN_LOAD_INDEX;
    }

    @Override
    public String getColumnName(int column) {
        return Constants.PROCESS_TABLE_COLUMN_NAMES[column];
    }

    @Override
    public int getColumnCount() {
        return Constants.PROCESS_TABLE_COLUMN_NAMES.length;
    }
}
