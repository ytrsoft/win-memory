package com.ytrsoft.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AppGUI extends XFrame implements ActionListener {

    private XButton btnAdd, btnSub;

    public AppGUI() {
        super(250, 75);
    }

    @Override
    protected void start(JPanel root) {
        btnAdd = new XButton("Add");
        btnAdd.addActionListener(this);
        btnSub = new XButton("Sub");
        btnSub.addActionListener(this);
        root.add(btnAdd);
        root.add(btnSub);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        XButton btn = (XButton) e.getSource();
        if (btn.equals(btnAdd)) {
            update(1);
            return;
        }
        if (btn.equals(btnSub)) {
            update(-1);
        }
    }

    protected abstract void update(int val);

}

