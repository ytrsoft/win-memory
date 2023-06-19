package com.ytrsoft.gui.view.process;

import com.ytrsoft.entity.Process;

import javax.swing.*;
import java.awt.*;

public class ProcessNodeIcon extends JPanel {

    public ProcessNodeIcon(Process process) {
        setLayout(new BorderLayout());
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
        add(icon, BorderLayout.WEST);
        add(text, BorderLayout.CENTER);
    }

}
