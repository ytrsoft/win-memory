package com.ytrsoft;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainClass extends JXFrame
{
    public MainClass()
    {
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

        final JXTreeTable table = new MyTreeTable(new MyTreeTableModel(root));
        table.setFillsViewportHeight(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

        table.setRootVisible(true);
        table.expandAll();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MainClass();
    }

    private class MyTreeTable extends JXTreeTable
    {
        private MyTreeTable(TreeTableModel treeModel)
        {
            super(treeModel);
        }

        public String getToolTipText(MouseEvent event)
        {
            return "Tooltip";
        }
    }

    private class MyTreeTableModel extends DefaultTreeTableModel
    {
        private MyTreeTableModel(TreeTableNode root)
        {
            super(root);
        }

        public Object getValueAt(Object node, int column)
        {
            return "Hello " + column;
        }

        public int getColumnCount()
        {
            return 4;
        }
    }
}
