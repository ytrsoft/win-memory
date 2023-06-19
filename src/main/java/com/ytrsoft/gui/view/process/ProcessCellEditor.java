package com.ytrsoft.gui.view.process;

import com.ytrsoft.entity.Process;
import com.ytrsoft.gui.controller.ProcessTableController;
import com.ytrsoft.util.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableNode;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private final JButton btnLoad;
    private JXTreeTable treeTable;
    private int editRow;
    private final ProcessTableController controller = new ProcessTableController();

    public ProcessCellEditor() {
        btnLoad = new JButton(Constants.PROCESS_TABLE_BTN_LOAD);
        btnLoad.setFocusable(false);
        btnLoad.setFocusPainted(false);
        btnLoad.addActionListener(this);
    }

    private TreeTableNode getNodeAt(int row) {
        return (TreeTableNode) treeTable.getPathForRow(row).getLastPathComponent();
    }

    @Override
    public Object getCellEditorValue() {
        return btnLoad.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.treeTable = (JXTreeTable) table;
        this.editRow = row;
        return btnLoad;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Process process = controller.getProcess(getNodeAt(editRow));
        if (ObjectUtils.isNotEmpty(process)) {
            doActionRequest(process);
        }
    }

    private void doActionRequest(Process process) {
        System.out.println(process);
    }
}
