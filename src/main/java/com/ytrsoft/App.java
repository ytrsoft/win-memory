package com.ytrsoft;

import com.ytrsoft.core.ProcessSupport;
import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.*;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class App extends AppUI implements ActionListener {

    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    private ProcessTreeTableModel model;
    private List<Process> processes;
    private ProcessTreeTable treeTable;

    public App() {
        super(650, 700);
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

    private List<Process> filterProcesses(List<Process> processes, String pid) {
        List<Process> filteredProcesses = new ArrayList<>();
        if (processes == null) {
            return filteredProcesses;
        }
        for(Process process : processes) {
            if(String.valueOf(process.getId()).contains(pid)) {
                filteredProcesses.add(process);
            }
            List<Process> children = process.getChildren();
            if(children != null) {
                List<Process> filteredChildren = filterProcesses(children, pid);
                filteredProcesses.addAll(filteredChildren);
            }
        }
        return filteredProcesses;
    }


    private void filterTree(String pid) {
        List<Process> processList = filterProcesses(processes, pid);
        DefaultMutableTreeTableNode nodes = createTreeNodes(processList);
        model.setRoot(nodes);
        treeTable.expandAll();
    }

    private void resetTree() {
        this.processes = processes();
        DefaultMutableTreeTableNode nodes = createTreeNodes(processes);
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
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.equals(searchButton)) {
            searchHandle();
        }
        if(button.equals(resetButton)) {
            resetTree();
        }
    }

    @Override
    protected void initUI() {
        this.processes = processes();
        ProcessNode nodes = createTreeNodes(processes);
        treeTable = createProcessTreeTable(nodes);
        JScrollPane scrollPane = new JScrollPane(treeTable);
        addCenter(scrollPane);

        searchField = new JTextField();
        searchField.setToolTipText("请输入PID");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchHandle();
                }
            }
        });

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
        return new ProcessSupport().getProcessTree();
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
