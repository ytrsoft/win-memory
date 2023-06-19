package com.ytrsoft.gui.view.process;

import com.ytrsoft.util.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ProcessCellRenderer extends DefaultTableCellRenderer {
    public ProcessCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            table.clearSelection();
        }
        if (column == 2) {
            return new JButton(Constants.PROCESS_TABLE_BTN_LOAD);
        }
        return component;
    }
}