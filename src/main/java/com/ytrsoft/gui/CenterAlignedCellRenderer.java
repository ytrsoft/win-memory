package com.ytrsoft.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CenterAlignedCellRenderer extends DefaultTableCellRenderer {
    public CenterAlignedCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}