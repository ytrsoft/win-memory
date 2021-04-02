package com.ytrsoft.ui;

import javax.swing.*;

public class XSlider extends JSlider {

    public XSlider() {
        this(1);
    }

    public XSlider(int value) {
        setMaximum(10);
        setMinimum(1);
        setValue(value);
    }

}
