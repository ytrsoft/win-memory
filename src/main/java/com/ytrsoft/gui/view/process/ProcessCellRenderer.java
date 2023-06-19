package com.ytrsoft.gui.view.process;

import com.ytrsoft.util.Constants;
import org.apache.bcel.generic.RETURN;

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
        if (column == Constants.PROCESS_TABLE_BTN_LOAD_INDEX) {
            JButton btnLoad = new JButton(Constants.PROCESS_TABLE_BTN_LOAD);
            btnLoad.setFocusable(false);
            btnLoad.setFocusPainted(false);
            return btnLoad;
        }
        return component;
    }
}