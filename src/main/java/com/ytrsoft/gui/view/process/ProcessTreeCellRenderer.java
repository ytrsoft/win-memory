package com.ytrsoft.gui.view.process;

import com.ytrsoft.entity.Process;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ProcessTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (selected) {
            tree.clearSelection();
        }
        if (value instanceof DefaultMutableTreeTableNode) {
            ProcessNode treeNode = (ProcessNode) value;
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
