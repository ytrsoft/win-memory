package com.ytrsoft.gui;

import com.github.weisj.darklaf.components.treetable.JTreeTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProcessCellRenderer extends DefaultTableCellRenderer {
    public ProcessCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            component.setBackground(table.getBackground());
        }
        if (column == 2) {
            setValue("加载");
            setBorder(BorderFactory.createRaisedBevelBorder());
        }
        return component;
    }
}