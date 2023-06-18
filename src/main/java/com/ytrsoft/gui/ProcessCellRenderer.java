package com.ytrsoft.gui;

import com.ytrsoft.entity.Process;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

public class ProcessCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

//        JLabel cell = new JLabel(value.toString());
//        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
//        TreePath path = tree.getPathForRow(row);
//        Class<?> valueClass = value.getClass();
//        System.out.println(valueClass);
//        return cell;
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeTableNode) {
            DefaultMutableTreeTableNode treeNode = (DefaultMutableTreeTableNode) value;
            Object userObject = treeNode.getUserObject();
            if(userObject instanceof Process) {
                Process process = (Process) userObject;
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                JLabel icon = new JLabel();
                icon.setIcon(process.getIcon());
                JLabel text = new JLabel();
                icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
                if (process.getChildren() != null && !process.getChildren().isEmpty()) {
                    String name = process.getName();
                    int size = process.getChildren().size();
                    String content = String.format("%s (%d)", name, size);
                    text.setText(content);
                } else {
                    text.setText(process.getName());
                }
                panel.add(icon, BorderLayout.WEST);
                panel.add(text, BorderLayout.CENTER);
                return panel;
            }
        }
        return component;
    }
}
