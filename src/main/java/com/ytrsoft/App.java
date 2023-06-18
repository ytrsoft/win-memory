package com.ytrsoft;

import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.AppUI;
import com.ytrsoft.gui.ProcessCellRenderer;
import com.ytrsoft.gui.ProcessTreeTable;
import com.ytrsoft.gui.ProcessTreeTableModel;
import com.ytrsoft.util.ProcessKit;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App extends AppUI implements ActionListener {

    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    private ProcessTreeTableModel model;
    private List<Process> processes;

    public App() {
        super(650, 700);
    }

    private void buildTree(DefaultMutableTreeTableNode parent, List<Process> processes) {
        for (Process process : processes) {
            DefaultMutableTreeTableNode node = new DefaultMutableTreeTableNode(process);
            parent.add(node);
            List<Process> children = process.getChildren();
            if (children != null && !children.isEmpty()) {
                buildTree(node, children);
            }
        }
    }

    private DefaultMutableTreeTableNode createTreeNodes(List<Process> processes) {
        DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode();
        buildTree(root, processes);
        return root;
    }

    private ProcessTreeTable createProcessTreeTable(DefaultMutableTreeTableNode nodes) {
        model = new ProcessTreeTableModel(nodes);
        ProcessTreeTable table = new ProcessTreeTable(model);
        return table;
    }

    private List<Process> filterProcesses(List<Process> processes, String pid) {
        List<Process> filteredProcesses = new ArrayList<>();
        if (processes != null) {
            for (Process process : processes) {
                if (String.valueOf(process.getId()).equals(pid)) {
                    filteredProcesses.add(process);
                } else {
                    List<Process> filteredChildren = filterProcesses(process.getChildren(), pid);
                    if (!filteredChildren.isEmpty()) {
                        Process filteredProcess = process.copy();
                        filteredProcess.setChildren(filteredChildren);
                        filteredProcesses.add(filteredProcess);
                    }
                }
            }
        }
        return filteredProcesses;
    }


    private void filterTree(String pid) {
        List<Process> processList = filterProcesses(processes, pid);
        DefaultMutableTreeTableNode nodes = createTreeNodes(processList);
        model.setRoot(nodes);
    }

    private void resetTree() {
        this.processes = processes();
        DefaultMutableTreeTableNode nodes = createTreeNodes(processes);
        model.setRoot(nodes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.equals(searchButton)) {
            String pid = searchField.getText().trim();
            if (!pid.isEmpty()) {
                filterTree(pid);
            }
        }
        if(button.equals(resetButton)) {
            resetTree();
        }
    }

    @Override
    protected void initUI() {
        this.processes = processes();
        DefaultMutableTreeTableNode nodes = createTreeNodes(processes);
        ProcessTreeTable treeTable = createProcessTreeTable(nodes);
        JScrollPane scrollPane = new JScrollPane(treeTable);
        addCenter(scrollPane);

        searchField = new JTextField();
        searchField.setToolTipText("请输入PID");

        searchButton = new JButton("搜索");
        searchButton.addActionListener(this);

        resetButton = new JButton("重置");
        resetButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(searchButton, BorderLayout.WEST);
        buttonPanel.add(resetButton, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JLabel label = new JLabel("PID:");

        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));

        searchPanel.add(label, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.EAST);

        addNorth(searchPanel);

        treeTable.expandAll();
    }

    private List<Process> processes() {
        return ProcessKit.forList();
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
