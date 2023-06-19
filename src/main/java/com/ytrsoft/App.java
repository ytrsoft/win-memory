package com.ytrsoft;

import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.base.AppUI;
import com.ytrsoft.gui.controller.AppController;
import com.ytrsoft.gui.view.process.ProcessNode;
import com.ytrsoft.gui.view.process.ProcessTreeTable;
import com.ytrsoft.gui.view.process.ProcessTreeTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class App extends AppUI implements ActionListener, KeyListener {

    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    private ProcessTreeTableModel model;
    private ProcessTreeTable treeTable;
    private AppController appController;

    @Override
    protected Dimension getSized() {
        return new Dimension(650, 700);
    }

    @Override
    protected void initUI() {
        appController = new AppController();
        List<Process> processes = appController.getProcesses();
        ProcessNode nodes = createTreeNodes(processes);
        treeTable = createProcessTreeTable(nodes);
        addCenter(new JScrollPane(treeTable));

        searchField = new JTextField();
        searchField.setToolTipText("请输入PID");
        searchField.addKeyListener(this);

        searchButton = new JButton("搜索");
        searchButton.addActionListener(this);

        resetButton = new JButton("重置");
        resetButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(searchButton, BorderLayout.WEST);
        buttonPanel.add(resetButton, BorderLayout.CENTER);

        JLabel label = new JLabel("PID:");
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        searchPanel.add(label, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.EAST);

        addNorth(searchPanel);

        treeTable.expandAll();
    }

    private void buildTree(ProcessNode parent, List<Process> processes) {
        for (Process process : processes) {
            ProcessNode node = new ProcessNode(process);
            parent.add(node);
            List<Process> children = process.getChildren();
            if (children != null && !children.isEmpty()) {
                buildTree(node, children);
            }
        }
    }

    private ProcessNode createTreeNodes(List<Process> processes) {
        ProcessNode root = new ProcessNode();
        buildTree(root, processes);
        return root;
    }

    private ProcessTreeTable createProcessTreeTable(ProcessNode nodes) {
        model = new ProcessTreeTableModel(nodes);
        return new ProcessTreeTable(model);
    }

    private void filterTree(String pid) {
        List<Process> processList = appController.filterProcesses(pid);
        ProcessNode nodes = createTreeNodes(processList);
        model.setRoot(nodes);
        treeTable.expandAll();
    }

    private void resetTree() {
        List<Process> processes = appController.getProcesses();
        ProcessNode nodes = createTreeNodes(processes);
        model.setRoot(nodes);
        treeTable.expandAll();
    }

    private void searchHandle() {
        String pid = searchField.getText().trim();
        if (!pid.isEmpty()) {
            filterTree(pid);
        } else {
            resetTree();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            searchHandle();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.equals(searchButton)) {
            searchHandle();
        }
        if(button.equals(resetButton)) {
            resetTree();
        }
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
