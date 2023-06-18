package com.ytrsoft.gui;

import org.jdesktop.swingx.JXTreeTable;

import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProcessTreeTable extends JXTreeTable {
    public ProcessTreeTable(ProcessTreeTableModel model) {
        super(model);
        setRowHeight(24);
        setFocusable(false);
        setDefaultRenderer(Object.class, new ProcessCellRenderer());
        setTreeCellRenderer(new ProcessTreeCellRenderer());
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        component.setBackground(Color.decode("#3C3F41"));
        return component;
    }
}
