package com.ytrsoft.gui.base;

import javax.swing.*;

public interface PositionedComponent {
    void addCenter(JComponent component);
    void addNorth(JComponent component);
    void addEast(JComponent component);
    void addWest(JComponent component);
    void addSouth(JComponent component);
}
