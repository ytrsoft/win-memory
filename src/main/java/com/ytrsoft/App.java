package com.ytrsoft;

import com.ytrsoft.gui.AppUI;
import com.ytrsoft.gui.ProcessTreeTable;
import com.ytrsoft.gui.ProcessTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import javax.swing.*;


public class App extends AppUI {

    public App() {
        super(450, 600);
    }

    private ProcessTreeTable createProcessTreeTable() {
        DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode();
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        root.add(new DefaultMutableTreeTableNode());
        ProcessTreeTableModel model = new ProcessTreeTableModel();
        JScrollPane scrollPane = new JScrollPane();
        ProcessTreeTable table = new ProcessTreeTable(model);
        scrollPane.setViewportView(table);
        return table;
    }

    @Override
    protected void initUI() {
        addCenter(createProcessTreeTable());
    }

    public static void main(String[] args) {
        launch(App.class);
    }

}
