package com.ytrsoft.gui.view.process;

import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.controller.ProcessTableController;
import org.apache.commons.lang3.ObjectUtils;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ProcessTreeCellRenderer extends DefaultTreeCellRenderer {

    private final ProcessTableController controller = new ProcessTableController();

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (selected) {
            tree.clearSelection();
        }
        Process process = controller.getProcess(value);
        if (ObjectUtils.isNotEmpty(process)) {
            return new ProcessNodeIcon(process);
        }
        return component;
    }
}
