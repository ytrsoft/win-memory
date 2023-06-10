package com.ytrsoft.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AppGUI extends XFrame implements ChangeListener, ActionListener {

    private XSlider mXSlider;
    private JButton mJButton;

    public AppGUI() {
        super(250, 100);
    }

    @Override
    protected void start(JPanel root) {
        mXSlider = new XSlider();
        mJButton = new JButton("扫描");
        mJButton.addActionListener(this);
        mXSlider.addChangeListener(this);
        root.add(mXSlider);
        root.add(mJButton);
        onInit();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        XSlider slider = (XSlider) e.getSource();
        if (mXSlider.equals(slider)) {
            onUpdate(slider.getValue());
        }
    }

    protected void setValue(int value) {
        this.mXSlider.setValue(value);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mJButton)) {
            scan();
        }
    }

    protected abstract void scan();
    protected abstract void onInit();
    protected abstract void onUpdate(int value);

}

