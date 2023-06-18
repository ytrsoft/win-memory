package com.ytrsoft.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            setValue("加载");
            setBorder(BorderFactory.createRaisedBevelBorder());
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = table.columnAtPoint(e.getPoint());
                    System.out.println(column);
                }
            });
        }
        return component;
    }
}