package com.ytrsoft.gui.view.process;

import com.ytrsoft.util.Constants;
import org.jdesktop.swingx.JXTreeTable;

public class ProcessTreeTable extends JXTreeTable {
    public ProcessTreeTable(ProcessTreeTableModel model) {
        super(model);
        setRowHeight(24);
        setFocusable(false);
        setDefaultRenderer(Object.class, new ProcessCellRenderer());
        setTreeCellRenderer(new ProcessTreeCellRenderer());
        int col = Constants.PROCESS_TABLE_BTN_LOAD_INDEX;
        getColumn(col).setCellEditor(new ProcessCellEditor());
    }
}
