package com.ytrsoft.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class AppGUI extends XFrame implements ChangeListener {

    private XSlider mXSlider;

    public AppGUI() {
        super(250, 75);
    }

    @Override
    protected void start(JPanel root) {
        mXSlider = new XSlider();
        mXSlider.addChangeListener(this);
        root.add(mXSlider);
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

    protected abstract void onInit();
    protected abstract void onUpdate(int value);

}

